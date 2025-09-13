import javax.swing.*;
import java.util.ArrayList;

public class WO79 extends Event {
    public WO79(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO79.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO79.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 483, 305, 89, 101, 351);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        System.out.println("Read a section on a special coin (to be done)");
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
    }
}
