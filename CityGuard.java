import java.util.ArrayList;
import java.util.Arrays;

public class CityGuard extends MonsterType {
    public CityGuard(int level) {
        HEALTH = new int[]{5, 6, 5, 6, 7, 9, 8, 9, 9, 10, 10, 13, 13, 15, 17, 20};
        MOVE = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3};
        ATTACK = new int[]{2, 3, 2, 3, 2, 3, 3, 4, 3, 4, 3, 4, 4, 4, 4, 5};
        ArrayList<String>[] modifiers = new ArrayList[] {new ArrayList<>(), new ArrayList<>(Arrays.asList("Shield 1")), new ArrayList<>(Arrays.asList("Shield 1")), new ArrayList<>(Arrays.asList("Shield 2")),
                new ArrayList<>(Arrays.asList("Shield 1")), new ArrayList<>(Arrays.asList("Shield 2")), new ArrayList<>(Arrays.asList("Shield 1")), new ArrayList<>(Arrays.asList("Shield 2", "Retaliate 1")),
                new ArrayList<>(Arrays.asList("Shield 1")), new ArrayList<>(Arrays.asList("Shield 2", "Retaliate 2")), new ArrayList<>(Arrays.asList("Shield 2")), new ArrayList<>(Arrays.asList("Shield 2", "Retaliate 2")),
                new ArrayList<>(Arrays.asList("Shield 2")), new ArrayList<>(Arrays.asList("Shield 3", "Retaliate 3")), new ArrayList<>(Arrays.asList("Shield 2")), new ArrayList<>(Arrays.asList("Shield 3", "Retaliate 3"))};
        createMonsterType(6, level, CityGuardStandee.class, modifiers);
        abilityCardsDeck.add(new ParryAndThrust());
    }

    @Override
    public String toString() {
        return "City Guard";
    }
}
