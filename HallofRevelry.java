import javax.swing.*;

public class HallofRevelry extends Building {

    public HallofRevelry(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[2];
        lvlCosts[0] = new BuildingCost(6, 6, 6, 5);
        lvlCosts[1] = new BuildingCost(0, 0, 0, 0);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[2];
        wreckCosts[0] = new BuildingCost(2, 2, 2, 0);
        wreckCosts[1] = new BuildingCost(2, 2, 2, 0);
        setWreckedCosts(wreckCosts);
        setNumber(81);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[2];
        upEff[0] = new BuildingUpgradeEffect(1, 178.2, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);
        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/HallofRevelry1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/HallofRevelry2.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/HallofRevelry1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/HallofRevelry2.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return wrecked;
    }

    @Override
    public void increaseLevel() {
        super.increaseLevel();
        if (level == 1) {
            setUpgradeLocked(false);
        }
    }

    @Override
    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {
        JButton loseMorale = new JButton("Lose 1 Morale");
        loseMorale.setBounds(180, 125, 120, 30);
        layeredPane.add(loseMorale, JLayeredPane.PALETTE_LAYER);

        loseMorale.addActionListener(_ ->
        {
            camp.increaseMorale(-1);
            dialog.dispose();
        });
    }

    @Override
    public String toString() {
        return "Hall of Revelry";
    }
}
