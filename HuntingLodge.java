import javax.swing.*;

public class HuntingLodge extends Building {
    public HuntingLodge(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[4];
        lvlCosts[0] = new BuildingCost(4, 1, 2, 1);
        lvlCosts[1] = new BuildingCost(6, 2, 3, 3);
        lvlCosts[2] = new BuildingCost(8, 2, 5, 5);
        lvlCosts[3] = new BuildingCost(10, 3, 6, 7);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3, 3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[4];
        wreckCosts[0] = new BuildingCost(2, 0, 1, 0);
        wreckCosts[1] = new BuildingCost(2, 1, 1, 0);
        wreckCosts[2] = new BuildingCost(2, 1, 2, 0);
        wreckCosts[3] = new BuildingCost(2, 1, 2, 0);
        setWreckedCosts(wreckCosts);
        setNumber(12);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[4];
        upEff[0] = new BuildingUpgradeEffect(1, 172.3, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[3] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, -1);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);
        this.camp = camp;
        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/HuntingLodge1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/HuntingLodge2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/HuntingLodge3.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/HuntingLodge4.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/HuntingLodge1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/HuntingLodge2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/HuntingLodge3.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/HuntingLodge4.png"};
    }

    @Override
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
        JButton payForHide = new JButton("Pay for Hide");
        payForHide.setBounds(180, 125, 120, 30);
        layeredPane.add(payForHide, JLayeredPane.PALETTE_LAYER);
        if (!camp.canPay("Gold", 2)) {
            payForHide.setVisible(false);
        }

        payForHide.addActionListener(_ ->
                new Thread(() ->
                {
                    count[0]++;
                    camp.distributeThings(-2, "Gold");
                    camp.distributeThings(1, "Hide");
                    if (!camp.canPay("Gold", 2)) {
                        payForHide.setVisible(false);
                    }
                    if (count[0] == level) {
                        dialog.dispose();
                    }
                }).start());
    }

    @Override
    public String toString() {
        return "Hunting Lodge";
    }
}
