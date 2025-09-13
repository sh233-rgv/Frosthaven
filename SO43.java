import javax.swing.*;
import java.util.ArrayList;

public class SO43 extends Event {
    public SO43(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO43.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO43.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 128, 147, 264, 380);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseProsperityBoxes(1);
        camp.distributeThings(3, "Lumber");
        camp.distributeThings(3, "Metal");
        camp.distributeThings(3, "Hide");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseProsperityBoxes(1);
        System.out.println("Gain one random item (to be done)");
    }

}
