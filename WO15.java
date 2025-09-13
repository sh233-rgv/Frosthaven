import javax.swing.*;
import java.util.ArrayList;

public class WO15 extends Event {
    public WO15(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO15.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO15.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 468, 101, 220, 127, 439);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(2);
    }
}