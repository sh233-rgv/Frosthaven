import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SO12 extends Event {
    public SO12(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO12.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO12.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 468, 220, 104, 127, 497);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(-1);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.outpostAttack(20, 3, new ArrayList<>(Arrays.asList(84, 88, 74, 98, 12, 72, 90, 42, 34, 44, 24)));
        camp.increaseMorale(1);
    }
}