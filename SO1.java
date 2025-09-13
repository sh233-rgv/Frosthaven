import javax.swing.*;
import java.util.ArrayList;

public class SO1 extends Event {
    public SO1(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO1.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO1.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 298, 205, 175, 496);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.addEvent("Summer Outpost", 40);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.distributeThings(30, "Gold");
    }

}