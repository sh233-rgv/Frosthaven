import javax.swing.*;
import java.util.ArrayList;

public class SO40 extends Event {
    public SO40(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO40.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO40.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 125, 191, 78, 497);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.addEvent("Summer Outpost", 1);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.distributeThings(10, "Gold");
        camp.addEvent("Summer Outpost", 1);
    }

}
