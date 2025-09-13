import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SO34 extends Event {
    public SO34(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO34.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO34.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 84, 117, 227, 307);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        threat(25, 6);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        threat(35, 0);
    }

    private void threat(int attackVal, int attacksWithD) {
        camp.outpostAttack(attackVal, 6, camp.chooseOutpostAttackTargeting(new ArrayList<>(Arrays.asList(24, 34, 42, 44, 72, 74)), 6), 0, attacksWithD);
        camp.increaseMorale(-1);
    }
}
