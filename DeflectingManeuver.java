import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DeflectingManeuver extends CharacterAbilityCard {

    public DeflectingManeuver(Character c) {
        super(c, 15, 1);
    }

    @Override
    public void topAction() {
        Queue<Runnable> queue = new LinkedList<>();
        boolean[] actionPerformed = {false};
        Methods.enqueue(queue, () -> {
            characterStandee.attack(true, 2, 1, 1, new ArrayList<>(Arrays.asList("Consume Wind: +1 Attack, Advantage, 1XP")), () -> Methods.finishQueue(queue));
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
        return "Deflecting Maneuver";
    }
}
