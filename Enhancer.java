import javax.swing.*;

public class Enhancer extends Building {

    public Enhancer(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[4];
        lvlCosts[0] = new BuildingCost(3, 4, 0, 1);
        lvlCosts[1] = new BuildingCost(4, 5, 0, 3);
        lvlCosts[2] = new BuildingCost(4, 4, 4, 5);
        lvlCosts[3] = new BuildingCost(5, 6, 6, 7);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3, 3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[4];
        wreckCosts[0] = new BuildingCost(2, 2, 0, 0);
        wreckCosts[1] = new BuildingCost(3, 2, 0, 0);
        wreckCosts[2] = new BuildingCost(3, 2, 1, 0);
        wreckCosts[3] = new BuildingCost(3, 2, 2, 0);
        setWreckedCosts(wreckCosts);
        setNumber(44);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[4];
        upEff[0] = new BuildingUpgradeEffect(1, 193.1, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 157.2, new int[]{}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[3] = new BuildingUpgradeEffect(1, 56.3, new int[]{}, 0, -1);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/Enhancer1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Enhancer2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Enhancer3.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Enhancer4.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/Enhancer1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Enhancer2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Enhancer3.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Enhancer4.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return wrecked;
    }

    @Override
    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {
        JButton gainDisarm = new JButton("Gain Disarm");
        gainDisarm.setBounds(180, 125, 120, 30);
        layeredPane.add(gainDisarm, JLayeredPane.PALETTE_LAYER);

        gainDisarm.addActionListener(_ ->
                new Thread(() ->
                {
                    System.out.println("All characters start with Disarm (to be done)");
                    dialog.dispose();
                }).start());
    }

    @Override
    public String toString() {
        return "Enhancer";
    }
}
