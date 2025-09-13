import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TempleoftheGreatOak extends Building {
    public TempleoftheGreatOak(Campaign camp) {
        BuildingCost[] lvlCosts = new BuildingCost[3];
        lvlCosts[0] = new BuildingCost(4, 2, 2, 1);
        lvlCosts[1] = new BuildingCost(3, 6, 3, 4);
        lvlCosts[2] = new BuildingCost(4, 10, 4, 7);
        setLevelCosts(lvlCosts);
        level = 0;
        setDamageCosts(new int[]{2, 3, 4});
        BuildingCost[] wreckCosts = new BuildingCost[3];
        wreckCosts[0] = new BuildingCost(2, 1, 1, 0);
        wreckCosts[1] = new BuildingCost(2, 2, 1, 0);
        wreckCosts[2] = new BuildingCost(3, 2, 1, 0);
        setWreckedCosts(wreckCosts);
        setNumber(42);
        BuildingUpgradeEffect[] upEff = new BuildingUpgradeEffect[3];
        upEff[0] = new BuildingUpgradeEffect(1, 192.5, new int[]{}, 0, 0);
        upEff[1] = new BuildingUpgradeEffect(3, 0, new int[]{}, 0, 0);
        upEff[2] = new BuildingUpgradeEffect(5, 0, new int[]{}, 0, -1);
        setUpgradeEffects(upEff);
        setUpgradeLocked(false);
        this.camp = camp;

        frontImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Fronts/TempleoftheGreatOak1.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/TempleoftheGreatOak2.png",
                "/Users/saahilherrero/Downloads/Images/Building Fronts/TempleoftheGreatOak3.png"};
        backImages = new String[]{"/Users/saahilherrero/Downloads/Images/Building Backs/TempleoftheGreatOak1.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/TempleoftheGreatOak2.png",
                "/Users/saahilherrero/Downloads/Images/Building Backs/TempleoftheGreatOak3.png"};
    }

    @Override
    public boolean hasBuildingOperations() {
        return level > 0;
    }

    @Override
    public void buildingOperations(JLayeredPane layeredPane, JDialog dialog) {
        if (!wrecked) {
            JButton nextBuilding = new JButton("Next Building");
            nextBuilding.setBounds(225, 495, 150, 30);
            layeredPane.add(nextBuilding, JLayeredPane.PALETTE_LAYER);
            nextBuilding.addActionListener(_ ->
                    dialog.dispose());
            JButton payForBlesses = new JButton("Pay for Blesses");
            payForBlesses.setBounds(180, 125, 120, 30);
            layeredPane.add(payForBlesses, JLayeredPane.PALETTE_LAYER);

            payForBlesses.addActionListener(_ ->
                    new Thread(() ->
                            payForBlesses(dialog)).start());
        } else {
            JButton gainCurses = new JButton("Gain Curses");
            gainCurses.setBounds(180, 125, 120, 30);
            layeredPane.add(gainCurses, JLayeredPane.PALETTE_LAYER);

            gainCurses.addActionListener(_ ->
                    new Thread(() ->
                    {
                        System.out.println("All characters start with Curse (to be done)");
                        dialog.dispose();
                    }).start());
        }
    }

    public void payForBlesses(JDialog dialog) {
        JDialog dialog1 = new JDialog((Frame) null, "Pay for Blesses", true);
        dialog1.setLayout(null);
        dialog1.setSize(380, 550);
        dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog1.setLocationRelativeTo(null);

        ArrayList<Character> eligibleCharacters = new ArrayList<>();
        for (int i = 0; i < camp.activeCharacters.size(); i++) {
            if (camp.activeCharacters.get(i).gold >= 5) {
                eligibleCharacters.add(camp.activeCharacters.get(i));
            }
        }

        for (int i = 0; i < eligibleCharacters.size(); i++) {
            payForBlessesHelper(dialog1, eligibleCharacters, i);
        }

        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(37, 200, 300, 30);
        dialog1.add(continueButton);
        continueButton.addActionListener(_ ->
        {
            dialog1.dispose();
            dialog.dispose();
        });
        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();
    }

    public void payForBlessesHelper(JDialog dialog1, ArrayList<Character> eligibleCharacters, int num) {
        JButton class1 = new JButton(eligibleCharacters.get(num).toString());
        class1.setBounds(20 + 180 * (num % 2), 25 + 75 * (num / 2), 150, 30);
        dialog1.add(class1);
        class1.addActionListener(_ ->
        {
            eligibleCharacters.get(num).gold -= 5;
            System.out.println(eligibleCharacters.get(num).toString() + " starts with 2 blesses (to be done)");
            class1.setVisible(false);
        });
    }

    @Override
    public String toString() {
        return "Temple of the Greak Oak";
    }
}
