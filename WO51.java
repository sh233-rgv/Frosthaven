import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO51 extends Event {
    public WO51(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO51.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO51.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 79, 103, 78, 265);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(60, 8);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(75, 3);
    }

    public void threat(int attackVal, int targets) {
        camp.outpostAttack(attackVal, targets, new ArrayList<>(Arrays.asList(37, 85, 35, 39, 67, 21, 17, 65, 83, 81, 5)));
        camp.increaseMorale(1);
    }
}