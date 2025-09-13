import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WO68 extends Event {
    public WO68(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO68.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO68.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 147, 191, 146, 366);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        Building b = camp.unlockedBuildings.get(camp.findBuildingInUnlocked(12));
        int[] costs = {b.levelCosts[b.level].lumberCost, b.levelCosts[b.level].metalCost, b.levelCosts[b.level].hideCost};
        if (camp.findBuildingInUnlocked(85) != -1 && camp.unlockedBuildings.get(camp.findBuildingInUnlocked(85)).level != 0 &&
                !camp.unlockedBuildings.get(camp.findBuildingInUnlocked(85)).wrecked) {
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
            dialog1.setVisible(true);
            dialog1.repaint();
            dialog1.revalidate();
        }
        camp.distributeThings(-costs[0], "Lumber", true);
        camp.distributeThings(-costs[1], "Metal", true);
        if (b.level == 0) {
            camp.distributeThings(-10, "Gold");
        }
        b.increaseLevel();
        if (b.level == 4) {
            camp.increaseProsperityBoxes(1);
        }
        if (b.level >= 3) {
            camp.increaseMorale(3);
        } else if (b.level >= 1) {
            camp.increaseMorale(2);
        }
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.enhanceLootCard("Hide");
        camp.increaseProsperityBoxes(1);
    }
}
