import javax.swing.*;
import java.util.ArrayList;

public class SO6 extends Event {
    public SO6(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO6.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO6.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 174, 162, 255, 483);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.distributeThings(15, "Gold");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        System.out.println("All armored characters gain a chest item (to be done)");
    }
}