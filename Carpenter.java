import javax.swing.*;

public class Carpenter extends Building {
    public Carpenter(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[2];
        lvlCosts[0] = new BuildingCost(4, 3, 2, 2);
        lvlCosts[1] = new BuildingCost(6, 5, 4, 5);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3});
        BuildingCost[] wreckCosts = new BuildingCost[2];
        wreckCosts[0] = new BuildingCost(2, 1, 1, 0);
        wreckCosts[1] = new BuildingCost(3, 2, 2, 0);
        setWreckedCosts(wreckCosts);
        setNumber(85);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[2];
        upEff[0] = new BuildingUpgradeEffect(1, 7.3, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/Carpenter1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Carpenter2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Carpenter3.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/Carpenter1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Carpenter2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Carpenter3.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return wrecked;
    }

    @Override
    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {
        JButton payMaterial = new JButton("Pay 1 Material");
        payMaterial.setBounds(180, 125, 120, 30);
        layeredPane.add(payMaterial, JLayeredPane.PALETTE_LAYER);

        payMaterial.addActionListener(_ ->
                new Thread(() ->
                {
                    camp.gainMaterials(-1);
                    dialog.dispose();
                }).start());
    }

    @Override
    public String toString() {
        return "Carpenter";
    }
}
