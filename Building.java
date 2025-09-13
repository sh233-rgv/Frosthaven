import javax.swing.*;
import java.awt.*;

public abstract class Building {
    public BuildingCost[] levelCosts;
    public int level;
    public int[] damageCosts;
    public BuildingCost[] wreckedCosts;
    public int number;
    public BuildingUpgradeEffect[] upgradeEffects;
    public boolean upgradeLocked;
    public boolean wrecked = false;
    public String[] frontImages;
    public String[] backImages;
    public Campaign camp;

    public void setLevelCosts(BuildingCost[] levelCosts) {
        this.levelCosts = levelCosts;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDamageCosts(int[] damageCosts) {
        this.damageCosts = damageCosts;
    }

    public void setWreckedCosts(BuildingCost[] wreckedCosts) {
        this.wreckedCosts = wreckedCosts;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setUpgradeEffects(BuildingUpgradeEffect[] upgradeEffects) {
        this.upgradeEffects = upgradeEffects;
    }

    public void setUpgradeLocked(boolean upgradeLocked) {
        this.upgradeLocked = upgradeLocked;
    }

    public void setWrecked(boolean wrecked) {
        this.wrecked = wrecked;
    }

    public void setFrontImages(String[] frontImages) {
        this.frontImages = frontImages;
    }

    public void setBackImages(String[] backImages) {
        this.backImages = backImages;
    }

    public void increaseLevel() {
        camp.increaseProsperityBoxes(upgradeEffects[level].prosperityIncrease);
        if (upgradeEffects[level].section != 0) {
            System.out.println("Read section " + upgradeEffects[level].section + " (To be done)");
        }
        for (int i = 0; i < upgradeEffects[level].itemsAdded.length; i++) {
            System.out.println("Add Item " + upgradeEffects[level].itemsAdded[i] + " to available (to be done)");
        }
        camp.increaseSoldiers(upgradeEffects[level].soldierIncrease);
        if (upgradeEffects[level].moraleEffect != 0) {
            camp.increaseMorale(upgradeEffects[level].moraleEffect);
        }
        level++;
    }

    public abstract boolean hasBuildingOperations();

    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {

    }

    public void damageBuilding() {
        JDialog dialog = new JDialog((Frame) null, "Damage Building", true);
        dialog.setLayout(null);
        dialog.setSize(380, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 375, 525);

        String image = frontImages[level - 1];

        JLabel label1 = Methods.createImage(image, 50);
        label1.setBounds(0, 0, 375, 525);

        layeredPane.add(label1, JLayeredPane.DEFAULT_LAYER);
        dialog.setContentPane(layeredPane);

        JButton payWithResources = new JButton("Pay with " + damageCosts[level - 1] + " Resources");
        payWithResources.setBounds(220, 390, 160, 30);
        layeredPane.add(payWithResources, JLayeredPane.PALETTE_LAYER);
        if (!camp.canPay("Material", damageCosts[level - 1], true)) {
            payWithResources.setVisible(false);
        }

        payWithResources.addActionListener(_ ->
        {
            camp.gainMaterials(-damageCosts[level - 1], true);
            dialog.dispose();
        });
        JButton loseMorale = new JButton("Lose 1 Morale");
        loseMorale.setBounds(220, 360, 160, 30);
        layeredPane.add(loseMorale, JLayeredPane.PALETTE_LAYER);

        loseMorale.addActionListener(_ ->
        {
            camp.increaseMorale(-1);
            dialog.dispose();
        });
        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();
    }

    public void buttonForConstruction(JDialog dialog, int num, boolean carpenter, int moraleCost) {
        int[] costs = {levelCosts[level].lumberCost, levelCosts[level].metalCost, levelCosts[level].hideCost};

        JButton buildingName = new JButton(this.toString());
        buildingName.setBounds(100, 25 + 40 * num, 180, 30);
        dialog.add(buildingName, JLayeredPane.PALETTE_LAYER);

        buildingName.addActionListener(_ ->
                new Thread(() ->
                {
                    dialog.dispose();
                    if (carpenter) {
                        JDialog dialog1 = new JDialog((Frame) null, "Choose material to pay less of", true);
                        dialog1.setLayout(null);
                        dialog1.setSize(380, 550);
                        dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog1.setLocationRelativeTo(null);
                        if (costs[0] != 0) {
                            JButton lumberButton = new JButton("Lumber " + costs[0] + " -> " + (costs[0] - 1));
                            lumberButton.setBounds(100, 30, 180, 30);
                            dialog1.add(lumberButton);
                            lumberButton.addActionListener(_ ->
                            {
                                costs[0]--;
                                dialog1.dispose();
                            });
                        }
                        if (costs[1] != 0) {
                            JButton metalButton = new JButton("Metal " + costs[1] + " -> " + (costs[1] - 1));
                            metalButton.setBounds(100, 60, 180, 30);
                            dialog1.add(metalButton);
                            metalButton.addActionListener(_ ->
                            {
                                costs[1]--;
                                dialog1.dispose();
                            });
                        }
                        if (costs[2] != 0) {
                            JButton hideButton = new JButton("Hide " + costs[2] + " -> " + (costs[2] - 1));
                            hideButton.setBounds(100, 90, 180, 30);
                            dialog1.add(hideButton);
                            hideButton.addActionListener(_ ->
                            {
                                costs[2]--;
                                dialog1.dispose();
                            });
                        }
                        dialog1.setVisible(true);
                        dialog1.repaint();
                        dialog1.revalidate();
                    }
                    camp.distributeThings(-costs[0], "Lumber", true);
                    camp.distributeThings(-costs[1], "Metal", true);
                    camp.distributeThings(-costs[2], "Hide", true);
                    if (level == 0) {
                        camp.distributeThings(-10, "Gold");
                    }

                    increaseLevel();
                    camp.increaseMorale(-moraleCost);
                }).start());
    }

    public boolean upgradeAvailable(boolean carpenter) {
        if (!upgradeLocked && level != levelCosts.length && levelCosts[level].prosperityRec <= camp.prosperity && !wrecked) {
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

    public boolean rebuildAvailable(boolean carpenter) {
        if (wrecked) {
            int l = camp.countTotal("Lumber") - wreckedCosts[level - 1].lumberCost;
            int m = camp.countTotal("Metal") - wreckedCosts[level - 1].metalCost;
            int h = camp.countTotal("Hide") - wreckedCosts[level - 1].hideCost;
            int numBelow = l + m + h;
            int maxBelow = -camp.inspiration;
            if (carpenter) {
                maxBelow--;
            }
            if (camp.constructionModifiers.contains("Reduce one rebuild cost by 2")) {
                maxBelow -= 2;
            }
            return numBelow >= maxBelow && l >= maxBelow && m >= maxBelow && h >= maxBelow;
        }
        return false;
    }

    public void buttonForRebuild(JDialog dialog, int num, boolean carpenter, boolean[] reduceCostTwo) {
        int[] costs = {wreckedCosts[level - 1].lumberCost, wreckedCosts[level - 1].metalCost, wreckedCosts[level - 1].hideCost};

        JButton buildingName = new JButton(this.toString());
        buildingName.setBounds(100, 25 + 40 * num, 180, 30);
        dialog.add(buildingName);

        buildingName.addActionListener(_ ->
                new Thread(() ->
                {
                    dialog.dispose();
                    int numMaterialsLess = 0;
                    if (carpenter) {
                        numMaterialsLess++;
                    }
                    if (reduceCostTwo[0]) {
                        numMaterialsLess += 2;
                    }
                    for (int i = 0; i < numMaterialsLess; i++) {
                        JDialog dialog1 = new JDialog((Frame) null, "Choose material to pay less of", true);
                        dialog1.setLayout(null);
                        dialog1.setSize(380, 550);
                        dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog1.setLocationRelativeTo(null);
                        if (costs[0] != 0) {
                            JButton lumberButton = new JButton("Lumber " + costs[0] + " -> " + (costs[0] - 1));
                            lumberButton.setBounds(100, 30, 180, 30);
                            dialog1.add(lumberButton);
                            lumberButton.addActionListener(_ ->
                            {
                                costs[0]--;
                                dialog1.dispose();
                            });
                        }
                        if (costs[1] != 0) {
                            JButton metalButton = new JButton("Metal " + costs[1] + " -> " + (costs[1] - 1));
                            metalButton.setBounds(100, 60, 180, 30);
                            dialog1.add(metalButton);
                            metalButton.addActionListener(_ ->
                            {
                                costs[1]--;
                                dialog1.dispose();
                            });
                        }
                        if (costs[2] != 0) {
                            JButton hideButton = new JButton("Hide " + costs[2] + " -> " + (costs[2] - 1));
                            hideButton.setBounds(100, 90, 180, 30);
                            dialog1.add(hideButton);
                            hideButton.addActionListener(_ ->
                            {
                                costs[2]--;
                                dialog1.dispose();
                            });
                        }
                        if (costs[2] == 0 && costs[1] == 0 && costs[0] == 0) {
                            dialog1.dispose();
                        }

                        dialog1.setVisible(true);
                        dialog1.repaint();
                        dialog1.revalidate();
                    }
                    camp.distributeThings(-costs[0], "Lumber", true);
                    camp.distributeThings(-costs[1], "Metal", true);
                    camp.distributeThings(-costs[2], "Hide", true);

                    wrecked = false;

                    if (reduceCostTwo[0]) {
                        camp.constructionModifiers.remove("Reduce one rebuild cost by 2");
                    }

                    camp.rebuildHelper(carpenter);
                }).start());
    }

    @Override
    public abstract String toString();
}
