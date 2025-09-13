import javax.swing.*;
import java.util.ArrayList;

public class WO73 extends Event {
    public WO73(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO73.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO73.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 168, 132, 145, 410);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.distributeThings(5, "Lumber");
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseProsperityBoxes(1);
    }
}
