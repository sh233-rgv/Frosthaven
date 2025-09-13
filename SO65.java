import javax.swing.*;
import java.util.ArrayList;

public class SO65 extends Event {
    public SO65(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO65.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO65.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 307, 161, 126, 497);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(2);
        System.out.println("All characters start with Bless (to be done)");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(1);
    }
}