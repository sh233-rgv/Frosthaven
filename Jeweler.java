import javax.swing.*;

public class Jeweler extends Building {

    public Jeweler(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[3];
        lvlCosts[0] = new BuildingCost(3, 2, 4, 4);
        lvlCosts[1] = new BuildingCost(3, 6, 3, 6);
        lvlCosts[2] = new BuildingCost(2, 10, 3, 8);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[3];
        wreckCosts[0] = new BuildingCost(1, 2, 1, 0);
        wreckCosts[1] = new BuildingCost(1, 3, 1, 0);
        wreckCosts[2] = new BuildingCost(1, 4, 1, 0);
        setWreckedCosts(wreckCosts);
        setNumber(39);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[3];
        upEff[0] = new BuildingUpgradeEffect(1, 31.3, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 0, new int[]{160, 161, 162, 163}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 0, new int[]{164, 165, 166, 167}, 0, -1);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/Jeweler1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Jeweler2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Jeweler3.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/Jeweler1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Jeweler2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Jeweler3.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return wrecked;
    }

    @Override
    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {
        JButton damageBuilding = new JButton("Damage Building");
        damageBuilding.setBounds(145, 110, 150, 30);
        layeredPane.add(damageBuilding, JLayeredPane.PALETTE_LAYER);

        damageBuilding.addActionListener(_ ->
                new Thread(() ->
                {
                    camp.damageBuildingOfChoice();
                    dialog.dispose();
                }).start());
    }

    @Override
    public String toString() {
        return "Jeweler";
    }
}
