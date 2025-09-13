import javax.swing.*;
import java.util.ArrayList;

public class SO39 extends Event {
    public SO39(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO39.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO39.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 160, 131, 101, 453);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        System.out.println("One character gains Tanjo (to be done)");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
    }

}
