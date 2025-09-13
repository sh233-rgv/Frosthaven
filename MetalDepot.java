import javax.swing.*;

public class MetalDepot extends Building {
    public MetalDepot(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[2];
        lvlCosts[0] = new BuildingCost(2, 6, 2, 3);
        lvlCosts[1] = new BuildingCost(5, 5, 5, 7);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[2];
        wreckCosts[0] = new BuildingCost(1, 2, 1, 0);
        wreckCosts[1] = new BuildingCost(1, 3, 1, 0);
        setWreckedCosts(wreckCosts);
        setNumber(65);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[2];
        upEff[0] = new BuildingUpgradeEffect(1, 193.4, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(2, 0, new int[]{}, 0, 0);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/MetalDepot1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/MetalDepot2.png",};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/MetalDepot1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/MetalDepot2.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return level > 0;
    }

    @Override
    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {
        if (!wrecked) {
            int[] count = {0};
            JButton nextBuilding = new JButton("Next Building");
            nextBuilding.setBounds(225, 495, 150, 30);
            layeredPane.add(nextBuilding, JLayeredPane.PALETTE_LAYER);
            nextBuilding.addActionListener(_ ->
                    dialog.dispose());
            JButton payForGold = new JButton("Pay for Gold");
            payForGold.setBounds(180, 125, 120, 30);
            layeredPane.add(payForGold, JLayeredPane.PALETTE_LAYER);
            if (!camp.canPay("Metal", 1)) {
                payForGold.setVisible(false);
            }

            payForGold.addActionListener(_ ->
                    new Thread(() ->
                    {
                        count[0]++;
                        camp.distributeThings(-1, "Metal");
                        camp.distributeThings(5, "Gold");
                        if (count[0] == level) {
                            dialog.dispose();
                        }
                        if (!camp.canPay("Metal", 1)) {
                            payForGold.setVisible(false);
                        }
                    }).start());
        } else {
            JButton payMetal = new JButton("Pay Metal");
            payMetal.setBounds(180, 125, 120, 30);
            layeredPane.add(payMetal, JLayeredPane.PALETTE_LAYER);

            payMetal.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.distributeThings(-2, "Metal");
                        dialog.dispose();
                    }).start());
        }
    }

    @Override
    public String toString() {
        return "Metal Depot";
    }
}
