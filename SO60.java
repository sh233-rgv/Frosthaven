import javax.swing.*;
import java.util.ArrayList;

public class SO60 extends Event {
    public SO60(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO60.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO60.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 105, 206, 126, 468);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.distributeThings(30, "Gold");
        camp.increaseProsperityBoxes(1);
        System.out.println("All characters discard two cards (to be done)");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.distributeThings(15, "Gold");
        camp.increaseMorale(-1);
    }
}
