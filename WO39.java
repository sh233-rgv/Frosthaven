import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO39 extends Event {
    public WO39(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO39.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO39.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 483, 80, 191, 101, 366);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(55);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(50);
    }

    public void threat(int attackVal) {
        camp.outpostAttack(attackVal, 6, camp.chooseOutpostAttackTargeting(new ArrayList<>(Arrays.asList(21, 24, 34, 35, 37, 39, 42, 44)), 6));
        camp.distributeThings(3, "Hide");
    }
}