import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO9 extends Event {
    public WO9(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO9.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO9.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 453, 483, 77, 118, 126, 278);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(4);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(1);
        threat(5);
    }

    public void threat(int targets) {
        camp.outpostAttack(15, targets, new ArrayList<>(Arrays.asList(85, 83, 81, 67, 65, 39, 37, 35, 21, 17, 5)));
    }
}