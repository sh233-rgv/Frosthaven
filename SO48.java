import javax.swing.*;
import java.util.ArrayList;

public class SO48 extends Event {
    public SO48(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO48.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO48.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 203, 220, 126, 409);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        System.out.println("One character gains Spiked Collar (to be done)");
    }

    @Override
    public void optionB() {
        camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(-1);
    }

}
