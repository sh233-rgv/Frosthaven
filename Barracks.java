import javax.swing.*;

public class Barracks extends Building {

    public Barracks(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[4];
        lvlCosts[0] = new BuildingCost(0, 0, 0, 0);
        lvlCosts[1] = new BuildingCost(0, 0, 0, 0);
        lvlCosts[2] = new BuildingCost(0, 0, 0, 0);
        lvlCosts[3] = new BuildingCost(0, 0, 0, 0);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 2, 3, 3});
        BuildingCost[] wreckCosts = new BuildingCost[4];
        wreckCosts[0] = new BuildingCost(1, 1, 1, 0);
        wreckCosts[1] = new BuildingCost(1, 2, 1, 0);
        wreckCosts[2] = new BuildingCost(1, 2, 1, 0);
        wreckCosts[3] = new BuildingCost(1, 3, 1, 0);
        setWreckedCosts(wreckCosts);
        setNumber(98);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[4];
        upEff[0] = new BuildingUpgradeEffect(0, 0, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 0, new int[]{}, 2, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 0, new int[]{}, 2, 0);
        upEff[3] = new BuildingUpgradeEffect(1, 0, new int[]{}, 2, 0);
        setUpgradeEffects(upEff);
        setUpgradeLocked(true);

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/Barracks1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Barracks2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Barracks3.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Barracks4.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/Barracks1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Barracks2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Barracks3.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Barracks4.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return !wrecked;
    }

    @Override
    public void increaseLevel() {
        super.increaseLevel();
        if (level != 1) {
            camp.soldierCapacity += 2;
            camp.increaseSoldiers(2);
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
        JButton payForSoldier = new JButton("Pay for Soldier");
        payForSoldier.setBounds(180, 125, 120, 30);
        layeredPane.add(payForSoldier, JLayeredPane.PALETTE_LAYER);
        if (!camp.canPay("Gold", 3) || !camp.canPay("Material", 1) || camp.soldiers == camp.soldierCapacity) {
            payForSoldier.setVisible(false);
        }

        payForSoldier.addActionListener(_ ->
                new Thread(() ->
                {
                    count[0]++;
                    camp.distributeThings(-3, "Gold");
                    camp.gainMaterials(-1);
                    camp.soldiers++;
                    if (!camp.canPay("Gold", 3) || !camp.canPay("Material", 1) || camp.soldiers == camp.soldierCapacity) {
                        payForSoldier.setVisible(false);
                    }
                    if (count[0] == (level + 1) / 2) {
                        dialog.dispose();
                    }
                }).start());
    }

    @Override
    public String toString() {
        return "Barracks";
    }
}
