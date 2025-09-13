import javax.swing.*;
import java.util.ArrayList;

public class SO44 extends Event {
    public SO44(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO44.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO44.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 316, 191, 163, 439);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        if (!camp.canPay("Gold", 10)) {
            System.out.println("All characters start with Wound, Poison, discard one card (to be done)");
        }
        camp.distributeThings(-10, "Gold");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.axenut += 3;
    }
}
