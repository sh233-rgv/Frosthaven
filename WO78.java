import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WO78 extends Event {
    public WO78(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO78.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO78.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 165, 147, 273, 380);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        JDialog dialog = new JDialog((Frame) null, "Select Card to Remove", true);
        dialog.setSize(350, 550);
        dialog.setLayout(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        int index = 0;
        boolean found = false;
        for (int i = 0; i < camp.townGuardDeck.size(); i++) {
            if (camp.townGuardDeck.get(i).frontImage.contains("Frostguard")) {
                optionAHelper(dialog, index, i);
                index++;
                found = true;
            }
        }

        if (found) {
            dialog.setVisible(true);
        }
    }

    private void optionAHelper(JDialog dialog, int index, int num) {
        JButton class1 = new JButton(camp.townGuardDeck.get(num).name);
        class1.setBounds(100, 30 * index, 150, 30);
        class1.addActionListener(_ ->
        {
            camp.townGuardDeck.remove(num);
            dialog.dispose();
        });
        dialog.add(class1);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.calendarSections.get(camp.week + 1).add(139.3);
    }
}
