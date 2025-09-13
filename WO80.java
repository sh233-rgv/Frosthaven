import javax.swing.*;
import java.util.ArrayList;

public class WO80 extends Event {
    public WO80(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO80.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO80.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 278, 176, 278, 410);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(1);
        camp.calendarSections.get(camp.week + 5).add(163.3);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.distributeThings(10, "Gold");
        camp.calendarSections.get(camp.week + 5).add(163.3);
    }
}
