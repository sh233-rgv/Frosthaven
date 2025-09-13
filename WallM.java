public class WallM extends Building {
    public WallM(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[1];
        lvlCosts[0] = new BuildingCost(4, 3, 3, 4);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{0});
        BuildingCost[] wreckCosts = new BuildingCost[1];
        wreckCosts[0] = new BuildingCost(0, 0, 0, 0);
        setWreckedCosts(wreckCosts);
        setNumber(103);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[1];
        upEff[0] = new BuildingUpgradeEffect(0, 0, new int[]{}, 0, 0);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);
        this.camp = camp;
    }

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
        return "Wall M";
    }
}
