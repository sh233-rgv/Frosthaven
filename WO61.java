import javax.swing.*;
import java.util.ArrayList;

public class WO61 extends Event {
    public WO61(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO61.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO61.png", camp);
    }


    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 483, 140, 206, 140, 497);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(2);
        camp.giveToEach(10, "Gold");
        for (int i = camp.week + 20; i < 120; i += 20) {
            camp.calendarSections.get(i).add(57.3);
        }
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(3);
        System.out.println("All characters start with bless (to be done)");
        for (int i = camp.week + 20; i < 120; i += 20) {
            camp.calendarSections.get(i).add(57.3);
        }
    }
}