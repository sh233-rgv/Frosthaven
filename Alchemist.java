public class Alchemist extends Building {

    public Alchemist(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[3];
        lvlCosts[0] = new BuildingCost(0, 0, 0, 0);
        lvlCosts[1] = new BuildingCost(2, 2, 1, 1);
        lvlCosts[2] = new BuildingCost(4, 4, 2, 4);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 2, 3});
        BuildingCost[] wreckCosts = new BuildingCost[3];
        wreckCosts[0] = new BuildingCost(1, 1, 0, 0);
        wreckCosts[1] = new BuildingCost(1, 1, 1, 0);
        wreckCosts[2] = new BuildingCost(3, 1, 1, 0);
        setWreckedCosts(wreckCosts);
        setNumber(35);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[3];
        upEff[0] = new BuildingUpgradeEffect(0, 0, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 183.5, new int[]{}, 0, 0);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/Alchemist1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Alchemist2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Alchemist3.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/Alchemist1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Alchemist2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Alchemist3.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return false;
    }

    @Override
    public String toString() {
        return "Alchemist";
    }
}