import javax.swing.*;
import java.util.ArrayList;

public class WO77 extends Event {
    public WO77(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO77.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO77.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 468, 227, 177, 214, 424);
    }

    @Override
    public void optionA() {
        camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        System.out.println("Return random item blueprint (to be done)");
        System.out.println("Gain random item blueprint (to be done)");
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        System.out.println("Gain Item: Aesther Diadem 197 (to be done)");
    }
}
