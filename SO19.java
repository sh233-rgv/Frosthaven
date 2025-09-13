import javax.swing.*;
import java.util.ArrayList;

public class SO19 extends Event {
    public SO19(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO19.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO19.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 296, 118, 292, 395);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.addEvent("Summer Outpost", 36);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.addEvent("Winter Outpost", 61);
    }
}