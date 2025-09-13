import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO62 extends Event {
    public WO62(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO62.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO62.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 78, 103, 83, 264);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(35, 3);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(50, 5);
    }

    public void threat(int attackVal, int targets) {
        camp.outpostAttack(attackVal, targets, new ArrayList<>(Arrays.asList(44, 42, 34, 24, 12)));
        camp.addEvent("Summer Outpost", 39);
    }
}