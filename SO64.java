import javax.swing.*;
import java.util.ArrayList;

public class SO64 extends Event {
    public SO64(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO64.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO64.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 238, 132, 126, 468);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseProsperityBoxes(1);
        camp.inspiration++;
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseProsperityBoxes(1);
        camp.increaseMorale(-1);
    }

}
