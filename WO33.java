import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO33 extends Event {
    public WO33(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO33.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO33.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 177, 168, 304, 337);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(true);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        System.out.println("All characters start with Immobilize (to be done)");
        threat(false);
    }

    public void threat(boolean drawForAttacker) {
        camp.outpostAttack(40, 8, new ArrayList<>(Arrays.asList(98, 90, 88, 84, 74, 72, 44, 42, 34, 24, 12)), "", drawForAttacker);
        camp.increaseMorale(-1);
    }
}