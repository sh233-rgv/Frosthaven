public class AlgoxGuard extends MonsterType {
    public AlgoxGuard(int level) {
        HEALTH = new int[]{6, 10, 7, 12, 10, 15, 12, 19, 15, 22, 19, 27, 25, 37, 33, 47};
        MOVE = new int[]{2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 4, 5, 5, 6};
        ATTACK = new int[]{3, 4, 3, 4, 3, 5, 4, 5, 4, 6, 4, 6, 5, 7, 5, 7};
        createMonsterType(6, level, AlgoxGuardStandee.class, emptyModifiers);
        abilityCardsDeck.add(new ParryAndThrust());
    }

    @Override
    public String toString() {
        return "Algox Guard";
    }
}
