import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SO33 extends Event {
    public SO33(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO33.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO33.png", camp);
    }


    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 227, 220, 78, 337);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        threat(30, 5);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        threat(25, 0);
    }

    private void threat(int attackVal, int attacksWithD) {
        camp.outpostAttack(attackVal, 5, camp.chooseOutpostAttackTargeting(new ArrayList<>(Arrays.asList(34, 42, 44, 72, 74)), 5), 0, attacksWithD);
        camp.increaseMorale(2);
    }
}
