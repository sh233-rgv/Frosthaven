import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

public class CombinedEffort extends CharacterAbilityCard {

    public CombinedEffort(Character c) {
        super(c, 32, 1);
    }

    @Override
    public void topAction() {
        Queue<Runnable> queue = new LinkedList<>();
        boolean[] actionPerformed = {false};
        Methods.enqueue(queue, () -> {
            try {
                actionPerformed[0] = (boolean) characterStandee.attack(true, 3, 1, 0, new ArrayList<>(Arrays.asList("Area of Effect:\nRed: 1, 1\nGrey: 2, 1\nBlue: 2, 2", "Return Action Performed", "Condition: Wound")), () -> Methods.finishQueue(queue)).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            Methods.enqueue(queue, () -> {
                if (actionPerformed[0]) {
                    characterStandee.xp++;
                }
                discardPlayedCard();
                Methods.finishQueue(characterStandee.scenario.mainQueue);
            });
        });

    }

    @Override
    public void bottomAction() {
        Queue<Runnable> queue = new LinkedList<>();
        ArrayList<Figure> figuresGranted = new ArrayList<>();
        Methods.enqueue(queue, () -> {
            characterStandee.move(2, () -> Methods.finishQueue(queue));
            Methods.enqueue(queue, () ->  {
                characterStandee.chooseFigures(2, 2, figuresGranted, 2, () -> Methods.finishQueue(queue));
                Methods.enqueue(queue, () -> {
                    for (Figure f : figuresGranted) {
                        Methods.enqueue(queue, () -> f.move(2, () -> Methods.finishQueue(queue)));
                    }
                    Methods.finishQueue(queue);
                    Methods.enqueue(queue, () -> {
                        discardPlayedCard();
                        Methods.finishQueue(characterStandee.scenario.mainQueue);
                    });
                });
            });
        });
    }

    @Override
    public String toString() {
        return "Combined Effort";
    }
}
