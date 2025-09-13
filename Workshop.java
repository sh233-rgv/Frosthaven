import javax.swing.*;
import java.awt.*;

public class Workshop extends Building {

    public Workshop(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[4];
        lvlCosts[0] = new BuildingCost(0, 0, 0, 0);
        lvlCosts[1] = new BuildingCost(4, 1, 2, 1);
        lvlCosts[2] = new BuildingCost(3, 2, 1, 1);
        lvlCosts[3] = new BuildingCost(1, 3, 2, 1);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 2, 2, 2});
        BuildingCost[] wreckCosts = new BuildingCost[4];
        wreckCosts[0] = new BuildingCost(2, 1, 1, 0);
        wreckCosts[1] = new BuildingCost(2, 1, 1, 0);
        wreckCosts[2] = new BuildingCost(2, 1, 1, 0);
        wreckCosts[3] = new BuildingCost(2, 1, 1, 0);
        setWreckedCosts(wreckCosts);
        setNumber(84);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[4];
        upEff[0] = new BuildingUpgradeEffect(0, 0, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(0, 139.2, new int[]{}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(0, 169.2, new int[]{}, 0, 0);
        upEff[3] = new BuildingUpgradeEffect(0, 161.1, new int[]{}, 0, 0);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/Workshop1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Workshop1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Workshop1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Workshop1.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/Workshop1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Workshop1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Workshop1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Workshop1.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return wrecked;
    }

    @Override
    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {
        JButton payLumber = new JButton("Pay 1 Lumber");
        payLumber.setBounds(180, 125, 120, 30);
        layeredPane.add(payLumber, JLayeredPane.PALETTE_LAYER);

        payLumber.addActionListener(_ ->
        {
            camp.distributeThings(-1, "Lumber");
            dialog.dispose();
        });
    }

    @Override
    public boolean upgradeAvailable(boolean carpenter) {
        return upgradeAvailableHelper(carpenter, 1, camp.boat) || upgradeAvailableHelper(carpenter, 2, camp.sled)
                || upgradeAvailableHelper(carpenter, 3, camp.climbingGear);
    }

    public boolean upgradeAvailableHelper(boolean carpenter, int lvl, boolean transport) {
        if (lvl != levelCosts.length && levelCosts[lvl].prosperityRec <= camp.prosperity && !transport && !wrecked) {
            int l = camp.countTotal("Lumber") - levelCosts[level].lumberCost;
            int m = camp.countTotal("Metal") - levelCosts[level].metalCost;
            int h = camp.countTotal("Hide") - levelCosts[level].hideCost;
            int numBelow = l + m + h;
            int maxBelow = -camp.inspiration;
            if (carpenter) {
                maxBelow--;
            }
            boolean going = numBelow >= maxBelow && l >= maxBelow && m >= maxBelow && h >= maxBelow;

            if (going) {
                if (level == 0) {
                    return camp.countTotal("Gold") >= 10;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void buttonForConstruction(JDialog dialog, int num, boolean carpenter, int moraleCost) {
        JButton buildingName = new JButton(toString());
        buildingName.setBounds(100, 25 + 40 * num, 180, 30);
        dialog.add(buildingName, JLayeredPane.PALETTE_LAYER);

        buildingName.addActionListener(_ ->
                new Thread(() ->
                {
                    JDialog dialog1 = new JDialog((Frame) null, "Choose transportation to buy", true);
                    dialog1.setLayout(null);
                    dialog1.setSize(380, 550);
                    dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog1.setLocationRelativeTo(null);

                    buttonForConstructionHelper(dialog1, carpenter, "Boat", moraleCost, dialog);
                    buttonForConstructionHelper(dialog1, carpenter, "Sled", moraleCost, dialog);
                    buttonForConstructionHelper(dialog1, carpenter, "Climbing Gear", moraleCost, dialog);
                    dialog1.setVisible(true);
                }).start());
    }

    public void buttonForConstructionHelper(JDialog dialog1, boolean carpenter, String transport, int moraleCost, JDialog dialog) {
        boolean b = false;
        int num = 1;
        switch (transport) {
            case "Boat" -> b = camp.boat;
            case "Sled" -> {
                b = camp.sled;
                num += 1;
            }
            case "Climbing Gear" -> {
                b = camp.climbingGear;
                num += 2;
            }
        }

        if (upgradeAvailableHelper(carpenter, num, b)) {
            JButton t = new JButton("Build " + transport);
            t.setBounds(100, 25 + 40 * num, 180, 30);
            dialog1.add(t);
            int[] costs = {levelCosts[num].lumberCost, levelCosts[num].metalCost, levelCosts[num].hideCost};

            t.addActionListener(_ ->
                    new Thread(() ->
                    {
                        if (carpenter) {
                            JDialog dialog2 = new JDialog((Frame) null, "Choose material to pay less of", true);
                            dialog2.setLayout(null);
                            dialog2.setSize(380, 550);
                            dialog2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                            dialog2.setLocationRelativeTo(null);
                            if (costs[0] != 0) {
                                JButton lumberButton = new JButton("Lumber " + costs[0] + " -> " + (costs[0] - 1));
                                lumberButton.setBounds(100, 30, 180, 30);
                                dialog2.add(lumberButton);
                                lumberButton.addActionListener(_ ->
                                {
                                    costs[0]--;
                                    dialog2.dispose();
                                });
                            }
                            if (costs[1] != 0) {
                                JButton metalButton = new JButton("Metal " + costs[1] + " -> " + (costs[1] - 1));
                                metalButton.setBounds(100, 60, 180, 30);
                                dialog2.add(metalButton);
                                metalButton.addActionListener(_ ->
                                {
                                    costs[1]--;
                                    dialog2.dispose();
                                });
                            }
                            if (costs[2] != 0) {
                                JButton hideButton = new JButton("Hide " + costs[2] + " -> " + (costs[2] - 1));
                                hideButton.setBounds(100, 90, 180, 30);
                                dialog2.add(hideButton);
                                hideButton.addActionListener(_ ->
                                {
                                    costs[2]--;
                                    dialog2.dispose();
                                });
                            }
                            dialog2.setVisible(true);
                            dialog2.repaint();
                            dialog2.revalidate();
                        }
                        camp.distributeThings(-costs[0], "Lumber");
                        camp.distributeThings(-costs[1], "Metal");
                        camp.distributeThings(-costs[2], "Hide");
                        switch (transport) {
                            case "Boat" -> camp.boat = true;
                            case "Sled" -> camp.sled = true;
                            case "Climbing Gear" -> camp.climbingGear = true;
                        }
                        camp.increaseMorale(-moraleCost);
                        dialog1.dispose();
                        dialog.dispose();
                    }).start());
        }
    }

    @Override
    public String toString() {
        return "Workshop";
    }
}
