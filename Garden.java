import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Garden extends Building {

    public int plots;
    public boolean faceUp;
    public ArrayList<String> plantedHerbs;

    public Garden(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[4];
        lvlCosts[0] = new BuildingCost(3, 0, 0, 1);
        lvlCosts[1] = new BuildingCost(4, 2, 2, 2);
        lvlCosts[2] = new BuildingCost(3, 3, 3, 5);
        lvlCosts[3] = new BuildingCost(6, 3, 3, 7);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3, 3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[4];
        wreckCosts[0] = new BuildingCost(2, 0, 1, 0);
        wreckCosts[1] = new BuildingCost(2, 1, 1, 0);
        wreckCosts[2] = new BuildingCost(2, 1, 2, 0);
        wreckCosts[3] = new BuildingCost(2, 1, 2, 0);
        setWreckedCosts(wreckCosts);
        setNumber(24);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[4];
        upEff[0] = new BuildingUpgradeEffect(1, 78.4, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, 0);
        upEff[3] = new BuildingUpgradeEffect(1, 0, new int[]{}, 0, -1);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);
        plots = 0;
        faceUp = true;
        plantedHerbs = new ArrayList<>();

        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/Garden1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Garden2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Garden3.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/Garden4.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/Garden1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Garden2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Garden3.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/Garden4.png"};
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
    public void increaseLevel() {
        super.increaseLevel();
        if (level == 1 || level == 2 || level == 4) {
            plots++;
        }
    }

    @Override
    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {
        JButton nextBuilding = new JButton("Next Building");
        nextBuilding.setBounds(225, 495, 150, 30);
        layeredPane.add(nextBuilding, JLayeredPane.PALETTE_LAYER);
        nextBuilding.addActionListener(_ ->
                dialog.dispose());
        JButton gainHerbsButton = new JButton("Gain Planted Herbs");
        gainHerbsButton.setBounds(200, 150, 180, 30);
        layeredPane.add(gainHerbsButton, JLayeredPane.PALETTE_LAYER);

        JButton plantHerbsButton = new JButton("Plant Herbs");
        plantHerbsButton.setBounds(200, 150, 120, 30);
        layeredPane.add(plantHerbsButton, JLayeredPane.PALETTE_LAYER);

        if ((level < 3 && !faceUp) || level > 3) {
            plantHerbsButton.setVisible(false);
        } else {
            gainHerbsButton.setVisible(false);
        }

        gainHerbsButton.addActionListener(_ ->
                new Thread(() ->
                {
                    gainHerbs();
                    gainHerbsButton.setVisible(false);
                    if (level > 3) {
                        plantHerbsButton.setVisible(true);
                    } else {
                        dialog.dispose();
                    }
                }).start());

        plantHerbsButton.addActionListener(_ ->
                new Thread(() ->
                {
                    JDialog dialog1 = new JDialog((Frame) null, "Plant Herb", true);
                    dialog1.setLayout(null);
                    dialog1.setSize(380, 550);
                    dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog1.setLocationRelativeTo(null);

                    int num = 0;
                    if (camp.arrowvine > 0) {
                        plantHerbsButtons("Arrowvine", dialog1, num, dialog);
                        num++;
                    }
                    if (camp.axenut > 0) {
                        plantHerbsButtons("Axenut", dialog1, num, dialog);
                        num++;
                    }
                    if (camp.corpsecap > 0) {
                        plantHerbsButtons("Corpsecap", dialog1, num, dialog);
                        num++;
                    }
                    if (camp.flamefruit > 0) {
                        plantHerbsButtons("Flamefruit", dialog1, num, dialog);
                        num++;
                    }
                    if (camp.rockroot > 0) {
                        plantHerbsButtons("Rockroot", dialog1, num, dialog);
                        num++;
                    }
                    if (camp.snowthistle > 0) {
                        plantHerbsButtons("Snowthistle", dialog1, num, dialog);
                        num++;
                    }
                    JButton continueButton = new JButton("Continue without planting");
                    continueButton.setBounds(90, 25 + 40 * num, 200, 30);
                    dialog1.add(continueButton);
                    dialog1.revalidate();
                    dialog1.repaint();
                    dialog1.setVisible(true);
                }).start());
        if (level < 3) {
            faceUp = !faceUp;
        }
    }

    public void plantHerbsButtons(String herb, JDialog dialog1, int num, JDialog dialog) {
        JButton chooseHerb = new JButton("Plant " + herb);
        chooseHerb.setBounds(90, 25 + 40 * num, 200, 30);
        dialog1.add(chooseHerb);

        chooseHerb.addActionListener(_ ->
                new Thread(() ->
                {
                    switch (herb) {
                        case "Arrowvine" -> camp.arrowvine--;
                        case "Axenut" -> camp.axenut--;
                        case "Corpsecap" -> camp.corpsecap--;
                        case "Flamefruit" -> camp.flamefruit--;
                        case "Rockroot" -> camp.rockroot--;
                        case "Snowthistle" -> camp.snowthistle--;
                    }
                    if (plantedHerbs.size() < plots) {
                        plantedHerbs.add(herb);
                        dialog1.dispose();
                        dialog.dispose();
                    } else {
                        JDialog dialog2 = new JDialog((Frame) null, "Discard Herb", true);
                        dialog2.setLayout(null);
                        dialog2.setSize(380, 550);
                        dialog2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog2.setLocationRelativeTo(null);

                        for (int i = 0; i < plantedHerbs.size(); i++) {
                            discardPlanted(dialog2, plantedHerbs.get(i), herb, i, dialog1, dialog);
                        }

                        dialog2.revalidate();
                        dialog2.repaint();
                        dialog2.setVisible(true);
                    }
                }).start());
    }

    public void discardPlanted(JDialog dialog2, String herbDiscard, String herb, int num, JDialog dialog1, JDialog dialog) {
        JButton discardHerb = new JButton("Discard " + herbDiscard);
        discardHerb.setBounds(90, 25 + 40 * num, 200, 30);
        dialog2.add(discardHerb);
        discardHerb.addActionListener(_ ->
                new Thread(() ->
                {
                    plantedHerbs.remove(num);
                    plantedHerbs.add(herb);
                    dialog2.dispose();
                    dialog1.dispose();
                    dialog.dispose();
                }).start());
    }

    public void gainHerbs() {
        for (String p : plantedHerbs) {
            switch (p) {
                case "Arrowvine" -> camp.arrowvine++;
                case "Axenut" -> camp.axenut++;
                case "Corpsecap" -> camp.corpsecap++;
                case "Flamefruit" -> camp.flamefruit++;
                case "Rockroot" -> camp.rockroot++;
                case "Snowthistle" -> camp.snowthistle++;
            }
        }
    }

    @Override
    public String toString() {
        return "Garden";
    }
}
