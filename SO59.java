import javax.swing.*;
import java.util.ArrayList;

public class SO59 extends Event {
    public SO59(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO59.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO59.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 468, 216, 205, 222, 468);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(1);
        camp.campaignStickers.add("Red Well");
        camp.addEvent("Winter Outpost", 75);
        camp.addEvent("Winter Road", 43);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(1);
        camp.campaignStickers.add("Blue Well");
        camp.addEvent("Winter Outpost", 76);
        camp.addEvent("Summer Road", 40);
    }
}
