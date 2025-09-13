import javax.swing.*;
import java.util.ArrayList;

public class SO9 extends Event {
    public SO9(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO9.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO9.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 293, 161, 296, 438);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.addEvent("Winter Outpost", 62);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.addEvent("Summer Outpost", 39);
    }
}