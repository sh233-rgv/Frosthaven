import javax.swing.*;

public class Craftsman extends Building {

    public Craftsman(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[9];
        lvlCosts[0] = new BuildingCost(0, 0, 0, 0);
        lvlCosts[1] = new BuildingCost(2, 2, 1, 1);
        lvlCosts[2] = new BuildingCost(3, 2, 2, 2);
        lvlCosts[3] = new BuildingCost(4, 3, 2, 3);
        lvlCosts[4] = new BuildingCost(5, 3, 3, 4);
        lvlCosts[5] = new BuildingCost(6, 4, 3, 5);
        lvlCosts[6] = new BuildingCost(7, 4, 4, 6);
        lvlCosts[7] = new BuildingCost(8, 5, 4, 7);
        lvlCosts[8] = new BuildingCost(9, 5, 5, 8);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 2, 2, 3, 3, 3, 4, 4, 4});
        BuildingCost[] wreckCosts = new BuildingCost[9];
        wreckCosts[0] = new BuildingCost(1, 1, 0, 0);
        wreckCosts[1] = new BuildingCost(1, 1, 1, 0);
        wreckCosts[2] = new BuildingCost(2, 1, 1, 0);
        wreckCosts[3] = new BuildingCost(3, 1, 1, 0);
        wreckCosts[4] = new BuildingCost(3, 1, 1, 0);
        wreckCosts[5] = new BuildingCost(3, 2, 1, 0);
        wreckCosts[6] = new BuildingCost(3, 2, 1, 0);
        wreckCosts[7] = new BuildingCost(3, 2, 2, 0);
        wreckCosts[8] = new BuildingCost(3, 2, 2, 0);
        setWreckedCosts(wreckCosts);
        setNumber(34);
        this.camp = camp;
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[9];
        upEff[0] = new BuildingUpgradeEffect(0, 0, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 0, new int[]{11, 12, 13, 14, 15}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 0, new int[]{16, 17, 18, 19, 20}, 0, 0);
        upEff[3] = new BuildingUpgradeEffect(1, 0, new int[]{21, 22, 23, 24, 25}, 0, 0);
        upEff[4] = new BuildingUpgradeEffect(1, 0, new int[]{26, 27, 28, 29, 30}, 0, 0);
        upEff[5] = new BuildingUpgradeEffect(1, 0, new int[]{31, 32, 33, 34, 35}, 0, 0);
        upEff[6] = new BuildingUpgradeEffect(1, 0, new int[]{36, 37, 38, 39, 40}, 0, 0);
        upEff[7] = new BuildingUpgradeEffect(1, 0, new int[]{41, 42, 43, 44, 45}, 0, -1);
        upEff[8] = new BuildingUpgradeEffect(1, 0, new int[]{46, 47, 48, 49, 50}, 0, -1);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/Craftsman1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Craftsman2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Craftsman3.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Craftsman4.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Craftsman5.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Craftsman6.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Craftsman7.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Craftsman8.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Craftsman9.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/Craftsman1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Craftsman2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Craftsman3.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Craftsman4.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Craftsman5.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Craftsman6.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Craftsman7.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Craftsman8.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Craftsman9.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return wrecked;
    }

    @Override
    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {
        int num = -((level - 1) / 3 + 1);
        JButton payHide = new JButton("Pay " + -num + " Hide");
        payHide.setBounds(180, 125, 120, 30);
        layeredPane.add(payHide, JLayeredPane.PALETTE_LAYER);

        payHide.addActionListener(_ ->
        {
            camp.distributeThings(num, "Hide");
            dialog.dispose();
        });
    }

    @Override
    public String toString() {
        return "Craftsman";
    }
}
