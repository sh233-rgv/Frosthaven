import javax.swing.*;
import java.util.ArrayList;

public class WO66 extends Event {
    public WO66(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO66.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO66.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 468, 155, 177, 135, 381);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(-1);
        System.out.println("Each character gains a material resources only item (to be done)");
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseProsperityBoxes(1);
        camp.distributeThings(2, "Lumber");
        camp.distributeThings(2, "Metal");
        camp.distributeThings(2, "Hide");
    }
}