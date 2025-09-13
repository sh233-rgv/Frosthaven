import javax.swing.*;
import java.util.ArrayList;

public class SO42 extends Event {
    public SO42(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO42.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO42.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 128, 147, 208, 366);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(3);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        System.out.println("One character gains Exquisite Map (to be done)");
    }

}
