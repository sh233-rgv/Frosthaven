import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO49 extends Event {
    public WO49(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO49.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO49.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 79, 118, 78, 293);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(60, 11);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(80, 5);
    }

    public void threat(int attackVal, int targets) {
        camp.outpostAttack(attackVal, targets, new ArrayList<>(Arrays.asList(67, 65, 44, 42, 39, 37, 35, 34, 24, 21, 17, 12, 5)));
        camp.increaseMorale(1);
    }
}