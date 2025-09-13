import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO48 extends Event {
    public WO48(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO48.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO48.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 79, 104, 78, 307);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(70, 6);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(65, 3);
    }

    public void threat(int attackVal, int targets) {
        camp.outpostAttack(attackVal, targets, new ArrayList<>(Arrays.asList(98, 90, 88, 85, 84, 83,
                81, 74, 72, 67, 65, 44, 42, 39, 37, 35, 34, 24, 21, 17, 12, 5)));
    }
}