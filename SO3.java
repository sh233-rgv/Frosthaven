import javax.swing.*;
import java.util.ArrayList;

public class SO3 extends Event {
    public SO3(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO3.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO3.png", camp);
    }


    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 278, 176, 278, 468);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(1);
        camp.inspiration++;
        camp.calendarSections.get(camp.week + 2).add(180.1);
        camp.distributeThings(5, "Gold");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(1);
        camp.snowthistle++;
        camp.calendarSections.get(camp.week + 2).add(180.1);
    }
}