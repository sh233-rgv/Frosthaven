import java.util.ArrayList;
import java.util.Arrays;

public class AlgoxPriest extends MonsterType {
    public AlgoxPriest(int level) {
        HEALTH = new int[]{5, 7, 6, 7, 7, 9, 9, 12, 12, 14, 15, 19, 20, 25, 27, 34};
        MOVE = new int[]{2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3};
        ATTACK = new int[]{2, 3, 2, 3, 3, 3, 3, 3, 3, 4, 3, 4, 4, 5, 4, 5};
        ArrayList<String>[] modifiers = new ArrayList[] {new ArrayList<>(), new ArrayList<>(Arrays.asList("Pierce 2")), new ArrayList<>(Arrays.asList("Shield 1")), new ArrayList<>(Arrays.asList("Shield 2", "Pierce 2")),
                new ArrayList<>(Arrays.asList("Shield 1")), new ArrayList<>(Arrays.asList("Shield 2", "Pierce 2")), new ArrayList<>(Arrays.asList("Shield 1")), new ArrayList<>(Arrays.asList("Shield 2", "Pierce 2")),
                new ArrayList<>(Arrays.asList("Shield 1")), new ArrayList<>(Arrays.asList("Shield 2", "Pierce 2")), new ArrayList<>(Arrays.asList("Shield 1")), new ArrayList<>(Arrays.asList("Shield 2", "Pierce 2")),
                new ArrayList<>(Arrays.asList("Shield 1")), new ArrayList<>(Arrays.asList("Shield 2", "Pierce 3")), new ArrayList<>(Arrays.asList("Shield 1")), new ArrayList<>(Arrays.asList("Shield 2", "Pierce 3"))};
        createMonsterType(6, level, AlgoxPriestStandee.class, modifiers);
    }

    @Override
    public String toString() {
        return "Algox Priest";
    }
}
