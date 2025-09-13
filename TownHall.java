public class TownHall extends Building {
    public TownHall(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[3];
        lvlCosts[0] = new BuildingCost(2, 2, 1, 2);
        lvlCosts[1] = new BuildingCost(3, 3, 3, 4);
        lvlCosts[2] = new BuildingCost(4, 5, 4, 6);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[3];
        wreckCosts[0] = new BuildingCost(2, 2, 0, 0);
        wreckCosts[1] = new BuildingCost(2, 2, 1, 0);
        wreckCosts[2] = new BuildingCost(2, 2, 2, 0);
        setWreckedCosts(wreckCosts);
        setNumber(90);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[3];
        upEff[0] = new BuildingUpgradeEffect(1, 189.1, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/TownHall1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/TownHall2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/TownHall3.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/TownHall1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/TownHall2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/TownHall3.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return false;
    }

    @Override
    public String toString() {
        return "Town Hall";
    }
}
