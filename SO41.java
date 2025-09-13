import javax.swing.*;
import java.util.ArrayList;

public class SO41 extends Event {
    public SO41(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO41.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO41.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 285, 176, 285, 468);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.distributeThings(15, "Gold");
        camp.calendarSections.get(camp.week + 3).add(178.1);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.distributeThings(30, "Gold");
        camp.calendarSections.get(camp.week + 3).add(178.1);
    }

}
