import javax.swing.*;

public class LoggingCamp extends Building {
    public LoggingCamp(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[4];
        lvlCosts[0] = new BuildingCost(2, 3, 2, 1);
        lvlCosts[1] = new BuildingCost(4, 5, 2, 3);
        lvlCosts[2] = new BuildingCost(6, 6, 3, 5);
        lvlCosts[3] = new BuildingCost(8, 8, 3, 7);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3, 3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[4];
        wreckCosts[0] = new BuildingCost(2, 1, 0, 0);
        wreckCosts[1] = new BuildingCost(2, 1, 1, 0);
        wreckCosts[2] = new BuildingCost(2, 2, 1, 0);
        wreckCosts[3] = new BuildingCost(2, 2, 1, 0);
        setWreckedCosts(wreckCosts);
        setNumber(17);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[4];
        upEff[0] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[3] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, -1);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);
        this.camp = camp;
        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/LoggingCamp1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/LoggingCamp2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/LoggingCamp3.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/LoggingCamp4.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/LoggingCamp1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/LoggingCamp2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/LoggingCamp3.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/LoggingCamp4.png"};
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
        JButton payForLumber = new JButton("Pay for Lumber");
        payForLumber.setBounds(180, 125, 120, 30);
        layeredPane.add(payForLumber, JLayeredPane.PALETTE_LAYER);
        if (!camp.canPay("Gold", 2)) {
            payForLumber.setVisible(false);
        }

        payForLumber.addActionListener(_ ->
                new Thread(() ->
                {
                    count[0]++;
                    camp.distributeThings(-2, "Gold");
                    camp.distributeThings(1, "Lumber");
                    if (count[0] == level) {
                        dialog.dispose();
                    }
                    if (!camp.canPay("Gold", 2)) {
                        payForLumber.setVisible(false);
                    }
                }).start());
    }

    @Override
    public String toString() {
        return "Logging Camp";
    }
}
