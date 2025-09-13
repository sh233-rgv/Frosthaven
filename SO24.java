import javax.swing.*;
import java.util.ArrayList;

public class SO24 extends Event {
    public SO24(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO24.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO24.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 168, 118, 126, 424);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.distributeThings(-5, "Gold");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(1);
    }
}