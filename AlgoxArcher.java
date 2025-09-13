public class AlgoxArcher extends MonsterType {
    public AlgoxArcher(int level) {
        HEALTH = new int[]{4, 6, 5, 8, 7, 11, 7, 12, 10, 16, 13, 18, 19, 28, 22, 33};
        MOVE = new int[]{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        ATTACK = new int[]{3, 4, 3, 4, 3, 4, 4, 5, 4, 5, 4, 6, 4, 6, 5, 7};
        createMonsterType(6, level, AlgoxArcherStandee.class, emptyModifiers);
    }

    @Override
    public String toString() {
        return "Algox Archer";
    }
}
