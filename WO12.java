import javax.swing.*;
import java.util.ArrayList;

public class WO12 extends Event {
    public WO12(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO12.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO12.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 174, 162, 89, 425);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(1);
        camp.increaseSoldiers(1);
        camp.distributeThings(-10, "Gold");
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(2);
        camp.increaseSoldiers(1);
        camp.loseItem();
    }
}