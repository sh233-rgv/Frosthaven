import javax.swing.*;
import java.util.ArrayList;

public class SO18 extends Event {
    public SO18(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO18.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO18.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 315, 118, 323, 396);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        System.out.println("Bet Gold on success (to be done)");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        System.out.println("Bet resources on success (to be done)");
    }
}