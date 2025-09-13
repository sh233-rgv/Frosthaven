import javax.swing.*;
import java.util.ArrayList;

public class SO36 extends Event {
    public SO36(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO36.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO36.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 140, 177, 140, 483);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(2);
        for (int i = camp.week + 20; i < 120; i += 20) {
            camp.calendarSections.get(i).add(57.3);
        }
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(1);
        camp.distributeHerbs(2);
        for (int i = camp.week + 20; i < 120; i += 20) {
            camp.calendarSections.get(i).add(57.3);
        }
    }

}
