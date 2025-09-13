import javax.swing.*;

public class TradingPost extends Building {
    public TradingPost(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[4];
        lvlCosts[0] = new BuildingCost(2, 2, 1, 1);
        lvlCosts[1] = new BuildingCost(3, 3, 2, 3);
        lvlCosts[2] = new BuildingCost(4, 3, 3, 5);
        lvlCosts[3] = new BuildingCost(5, 4, 4, 7);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3, 3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[4];
        wreckCosts[0] = new BuildingCost(1, 1, 0, 0);
        wreckCosts[1] = new BuildingCost(1, 1, 1, 0);
        wreckCosts[2] = new BuildingCost(2, 1, 1, 0);
        wreckCosts[3] = new BuildingCost(2, 2, 1, 0);
        setWreckedCosts(wreckCosts);
        setNumber(37);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[4];
        upEff[0] = new BuildingUpgradeEffect(1, 25.2, new int[0], 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 12.4, new int[0], 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 38.3, new int[0], 0, 0);
        upEff[3] = new BuildingUpgradeEffect(1, 196.2, new int[0], 0, -1);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/TradingPost1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/TradingPost2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/TradingPost3.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/TradingPost4.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/TradingPost1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/TradingPost2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/TradingPost3.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/TradingPost4.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return wrecked;
    }

    @Override
    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {
        int num = -level * 5;
        JButton payGold = new JButton("Pay " + -num + " Gold");
        payGold.setBounds(180, 125, 120, 30);
        layeredPane.add(payGold, JLayeredPane.PALETTE_LAYER);

        payGold.addActionListener(_ ->
        {
            camp.distributeThings(num, "Gold");
            dialog.dispose();
        });
    }

    @Override
    public String toString() {
        return "Trading Post";
    }
}