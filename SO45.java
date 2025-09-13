import javax.swing.*;
import java.util.ArrayList;

public class SO45 extends Event {

    public SO45(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO45.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO45.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 176, 132, 149, 410);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.distributeThings(40, "Gold");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.distributeThings(30, "Gold");
        camp.inspiration++;
    }

}
