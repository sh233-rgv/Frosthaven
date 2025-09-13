import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

public class AtAllCosts extends CharacterAbilityCard {

    public AtAllCosts(Character c) {
        super(c, 60, 1);
    }

    @Override
    public void topAction() {
        Queue<Runnable> queue = new LinkedList<>();
        int[] num = {0};
        Methods.enqueue(queue, () -> {
            try {
                num[0] = (int) characterStandee.heal(3, -1, -1, new ArrayList<>(Arrays.asList("Target Ally", "Return figures increased heal")), () -> Methods.finishQueue(queue)).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            Methods.enqueue(queue, () -> {
                characterStandee.changeHealth(-num[0]);
                Methods.finishQueue(queue);
                Methods.enqueue(queue, () -> {
                    characterStandee.xp++;
                    discardPlayedCard();
                    Methods.finishQueue(characterStandee.scenario.mainQueue);
                });
            });
        });
    }

    @Override
    public void bottomAction() {
        Queue<Runnable> queue = new LinkedList<>();
        boolean[] actionPerformed = {false};
        Methods.enqueue(queue, () -> {
            try {
                actionPerformed[0] = characterStandee.summon(Reinforcement.class, () -> Methods.finishQueue(queue)).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            Methods.enqueue(queue, () -> {
                        if (actionPerformed[0]) {
                            characterStandee.xp++;
                            activePlayedCard("Persistent Discard");
                        }
                        Methods.finishQueue(characterStandee.scenario.mainQueue);
                    }
            );
        });

    }

    @Override
    public String toString() {
        return "At All Costs";
    }


}
