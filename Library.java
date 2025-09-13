import javax.swing.*;

public class Library extends Building {
    public Library(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[3];
        lvlCosts[0] = new BuildingCost(3, 2, 0, 2);
        lvlCosts[1] = new BuildingCost(4, 4, 1, 4);
        lvlCosts[2] = new BuildingCost(2, 5, 5, 6);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[3];
        wreckCosts[0] = new BuildingCost(2, 1, 0, 0);
        wreckCosts[1] = new BuildingCost(2, 2, 0, 0);
        wreckCosts[2] = new BuildingCost(2, 2, 2, 0);
        setWreckedCosts(wreckCosts);
        setNumber(83);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[3];
        upEff[0] = new BuildingUpgradeEffect(1, 190.3, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 116.3, new int[]{}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 89.2, new int[]{}, 0, 0);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/Library1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Library2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Library3.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/Library1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Library2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Library3.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return wrecked;
    }

    @Override
    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {
        JButton loseInspiration = new JButton("Lose 1 Inspiration");
        loseInspiration.setBounds(180, 125, 120, 30);
        layeredPane.add(loseInspiration, JLayeredPane.PALETTE_LAYER);

        loseInspiration.addActionListener(_ ->
                new Thread(() ->
                {
                    camp.gainInspiration(-1);
                    dialog.dispose();
                }).start());
    }

    @Override
    public boolean upgradeAvailable(boolean carpenter) {
        if (camp.constructionModifiers.contains("Upgrade Library for Free") && levelCosts[level].prosperityRec <= camp.prosperity) {
            return true;
        }
        return super.upgradeAvailable(carpenter);
    }

    @Override
    public void buttonForConstruction(JDialog dialog, int num, boolean carpenter, int moraleCost) {
        if (camp.constructionModifiers.contains("Upgrade Library for Free")) {
            JButton buildingName = new JButton("Free: " + this);
            buildingName.setBounds(100, 25 + 40 * num, 180, 30);
            dialog.add(buildingName, JLayeredPane.PALETTE_LAYER);

            buildingName.addActionListener(_ ->
            {
                increaseLevel();
                camp.increaseMorale(-moraleCost);
                dialog.dispose();
            });
        } else {
            super.buttonForConstruction(dialog, num, carpenter, moraleCost);
        }
    }

    @Override
    public String toString() {
        return "Library";
    }
}
