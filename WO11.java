import javax.swing.*;
import java.util.ArrayList;

public class WO11 extends Event {
    public WO11(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO11.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO11.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 101, 74, 101, 366);
    }

    @Override
    public void optionA() {
        camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
    }

    @Override
    public void optionB() {
        camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
    }
}