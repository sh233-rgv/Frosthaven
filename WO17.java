import javax.swing.*;
import java.util.ArrayList;

public class WO17 extends Event {
    public WO17(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO17.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO17.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 269, 118, 295, 424);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.calendarSections.get(camp.week + 1).add(115.1);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(2);
        camp.addEvent("Summer Outpost", 41);
    }
}