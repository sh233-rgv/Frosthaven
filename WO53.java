import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO53 extends Event {
    public WO53(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO53.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO53.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 468, 78, 74, 78, 265);
    }

    @Override
    public void optionA() {
        camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(55, 8);
    }

    @Override
    public void optionB() {
        camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(50, 10);
    }

    public void threat(int attackVal, int targets) {
        camp.outpostAttack(attackVal, targets, new ArrayList<>(Arrays.asList(85, 83, 81, 67, 65, 39, 37, 35, 21, 17, 5)), true);
    }
}