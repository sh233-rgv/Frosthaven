import javax.swing.*;
import java.util.ArrayList;

public class SO52 extends Event {
    public SO52(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO52.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO52.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 128, 103, 133, 395);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(2);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.distributeThings(5, "Hide");
        camp.increaseSoldiers(3);
    }

}
