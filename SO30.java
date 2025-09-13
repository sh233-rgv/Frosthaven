import javax.swing.*;
import java.util.ArrayList;

public class SO30 extends Event {
    public SO30(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO30.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO30.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 274, 176, 274, 425);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.calendarSections.get(camp.week + 6).add(183.4);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.calendarSections.get(camp.week + 6).add(171.4);
    }
}