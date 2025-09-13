public class Stables extends Building {

    public Stables(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[4];
        lvlCosts[0] = new BuildingCost(6, 2, 2, 2);
        lvlCosts[1] = new BuildingCost(4, 5, 5, 4);
        lvlCosts[2] = new BuildingCost(6, 7, 6, 6);
        lvlCosts[3] = new BuildingCost(8, 8, 8, 8);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3, 3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[4];
        wreckCosts[0] = new BuildingCost(3, 1, 0, 0);
        wreckCosts[1] = new BuildingCost(3, 1, 1, 0);
        wreckCosts[2] = new BuildingCost(3, 2, 2, 0);
        wreckCosts[3] = new BuildingCost(3, 3, 3, 0);
        setWreckedCosts(wreckCosts);
        setNumber(88);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[4];
        upEff[0] = new BuildingUpgradeEffect(1, 3.1, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[3] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, -1);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/Stables1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Stables2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Stables3.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Stables4.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/Stables1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Stables2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Stables3.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Stables4.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return false;
    }

    @Override
    public String toString() {
        return "Stables";
    }
}
