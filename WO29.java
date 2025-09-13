import javax.swing.*;
import java.util.ArrayList;

public class WO29 extends Event {
    public WO29(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO29.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO29.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 177, 118, 175, 453);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.giveToEach(-3, "Experience");
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.distributeThings(10, "Gold");
    }
}