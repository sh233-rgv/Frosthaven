import javax.swing.*;
import java.util.ArrayList;

public class SO22 extends Event {
    public SO22(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO22.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO22.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 168, 118, 127, 409);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(1);
        camp.distributeThings(-5, "Gold");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        System.out.println("All characters start by discarding two cards (to be done)");
        camp.increaseMorale(2);
    }
}