public class WallJ extends Building {
    public WallJ(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[1];
        lvlCosts[0] = new BuildingCost(4, 0, 0, 1);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{0});
        BuildingCost[] wreckCosts = new BuildingCost[1];
        wreckCosts[0] = new BuildingCost(0, 0, 0, 0);
        setWreckedCosts(wreckCosts);
        setNumber(100);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[1];
        upEff[0] = new BuildingUpgradeEffect(0, 0, new int[]{}, 0, 0);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);
        this.camp = camp;
    }

    @Override
    public boolean hasBuildingOperations() {
        return false;
    }

    @Override
    public void increaseLevel() {
        super.increaseLevel();
        camp.defense += 5;
    }

    @Override
    public String toString() {
        return "Wall J";
    }
}
