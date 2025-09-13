import javax.swing.*;

public class MiningCamp extends Building {
    public MiningCamp(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[4];
        lvlCosts[0] = new BuildingCost(4, 2, 1, 1);
        lvlCosts[1] = new BuildingCost(6, 3, 2, 3);
        lvlCosts[2] = new BuildingCost(8, 5, 2, 5);
        lvlCosts[3] = new BuildingCost(10, 6, 3, 7);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3, 3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[4];
        wreckCosts[0] = new BuildingCost(1, 2, 0, 0);
        wreckCosts[1] = new BuildingCost(1, 2, 1, 0);
        wreckCosts[2] = new BuildingCost(2, 2, 1, 0);
        wreckCosts[3] = new BuildingCost(2, 2, 1, 0);
        setWreckedCosts(wreckCosts);
        setNumber(5);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[4];
        upEff[0] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 49.2, new int[]{}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[3] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, -1);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);
        this.camp = camp;
        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/MiningCamp1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/MiningCamp2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/MiningCamp3.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/MiningCamp4.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/MiningCamp1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/MiningCamp2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/MiningCamp3.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/MiningCamp4.png"};
    }

    public boolean hasBuildingOperations() {
        if (level > 0) {
            return !wrecked;
        } else {
            return false;
        }
    }

    @Override
    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {
        int[] count = {0};
        JButton nextBuilding = new JButton("Next Building");
        nextBuilding.setBounds(225, 495, 150, 30);
        layeredPane.add(nextBuilding, JLayeredPane.PALETTE_LAYER);
        nextBuilding.addActionListener(_ ->
                dialog.dispose());
        JButton payForMetal = new JButton("Pay for Metal");
        payForMetal.setBounds(180, 125, 120, 30);
        layeredPane.add(payForMetal, JLayeredPane.PALETTE_LAYER);
        if (!camp.canPay("Gold", 2)) {
            payForMetal.setVisible(false);
        }

        payForMetal.addActionListener(_ ->
                new Thread(() ->
                {
                    count[0]++;
                    camp.distributeThings(-2, "Gold");
                    camp.distributeThings(1, "Metal");
                    if (count[0] == level) {
                        dialog.dispose();
                    }
                    if (!camp.canPay("Gold", 2)) {
                        payForMetal.setVisible(false);
                    }
                }).start());
    }

    @Override
    public String toString() {
        return "Mining Camp";
    }
}
