import javax.swing.*;

public class Inn extends Building {

    public Inn(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[3];
        lvlCosts[0] = new BuildingCost(4, 4, 4, 2);
        lvlCosts[1] = new BuildingCost(5, 5, 5, 4);
        lvlCosts[2] = new BuildingCost(6, 6, 6, 5);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[3];
        wreckCosts[0] = new BuildingCost(2, 2, 1, 0);
        wreckCosts[1] = new BuildingCost(2, 2, 2, 0);
        wreckCosts[2] = new BuildingCost(3, 2, 2, 0);
        setWreckedCosts(wreckCosts);
        setNumber(21);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[3];
        upEff[0] = new BuildingUpgradeEffect(1, 151.2, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/Inn1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Inn2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Inn3.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/Inn1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Inn2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Inn3.png"};
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
        JButton payForMaterial = new JButton("Pay for Material");
        payForMaterial.setBounds(180, 125, 150, 30);
        layeredPane.add(payForMaterial, JLayeredPane.PALETTE_LAYER);
        if (!camp.canPay("Gold", 2)) {
            payForMaterial.setVisible(false);
        }

        payForMaterial.addActionListener(_ ->
                new Thread(() ->
                {
                    count[0]++;
                    camp.distributeThings(-2, "Gold");
                    camp.gainMaterials(1);
                    if (count[0] == level) {
                        dialog.dispose();
                    }
                    if (!camp.canPay("Gold", 2)) {
                        payForMaterial.setVisible(false);
                    }
                }).start());
    }

    @Override
    public String toString() {
        return "Inn";
    }
}
