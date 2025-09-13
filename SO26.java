import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SO26 extends Event {
    private int checkedBoxes;

    public SO26(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO26.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO26.png", camp);
        checkedBoxes = 0;
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 78, 118, 101, 234);
    }

    @Override
    public void optionA() {
        camp.increaseMorale(-1);
        threat(10);
    }

    @Override
    public void optionB() {
        threat(15);
    }

    private void threat(int attackVal) {
        camp.outpostAttack(attackVal + 15 * checkedBoxes, 4, camp.chooseOutpostAttackTargeting(camp.BUILDINGSNUMS, 4));
        checkedBoxes++;
        if (checkedBoxes >= 3) {
            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
            camp.addEvent("Summer Outpost", 47);
        } else {
            camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        }
    }

    @Override
    public void buttonClicked(JDialog dialog, JLayeredPane layeredPane) {
        super.buttonClicked(dialog, layeredPane);
        if (checkedBoxes > 0) {
            JLabel x1 = new JLabel("X");
            x1.setBounds(49, 476, 55, 20);
            x1.setFont(new Font("Markazi Text", Font.BOLD, 18));
            layeredPane.add(x1, JLayeredPane.PALETTE_LAYER);
        }
        if (checkedBoxes > 1) {
            JLabel x2 = new JLabel("X");
            x2.setBounds(65, 476, 55, 20);
            x2.setFont(new Font("Markazi Text", Font.BOLD, 18));
            layeredPane.add(x2, JLayeredPane.PALETTE_LAYER);
        }
    }
}