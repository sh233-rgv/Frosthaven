import javax.swing.*;

public class Tavern extends Building {
    public Tavern(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[3];
        lvlCosts[0] = new BuildingCost(2, 2, 1, 2);
        lvlCosts[1] = new BuildingCost(4, 3, 2, 4);
        lvlCosts[2] = new BuildingCost(6, 4, 2, 6);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[3];
        wreckCosts[0] = new BuildingCost(2, 1, 0, 0);
        wreckCosts[1] = new BuildingCost(2, 2, 0, 0);
        wreckCosts[2] = new BuildingCost(2, 2, 2, 0);
        setWreckedCosts(wreckCosts);
        setNumber(74);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[3];
        upEff[0] = new BuildingUpgradeEffect(1, 160.2, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 35.3, new int[]{}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 113.1, new int[]{}, 0, 0);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/Tavern1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Tavern2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Tavern3.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/Tavern1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Tavern2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Tavern3.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return wrecked;
    }

    @Override
    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {
        JButton loseMorale = new JButton("Lose 1 Morale");
        loseMorale.setBounds(180, 125, 120, 30);
        layeredPane.add(loseMorale, JLayeredPane.PALETTE_LAYER);

        loseMorale.addActionListener(_ ->
                new Thread(() ->
                {
                    camp.increaseMorale(-1);
                    dialog.dispose();
                }).start());
    }

    @Override
    public String toString() {
        return "Tavern";
    }
}