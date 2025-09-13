import javax.swing.*;
import java.util.ArrayList;

public class WO19 extends Event {
    public WO19(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO19.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO19.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 304, 220, 101, 497);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.giveToEach(5, "Gold");
        System.out.println("All characters start with brittle (to be done)");
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
    }
}