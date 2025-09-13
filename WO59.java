import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO59 extends Event {
    public WO59(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO59.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO59.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 222, 118, 101, 264);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(-2);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat();
    }

    public void threat() {
        camp.outpostAttack(50, 4, new ArrayList<>(Arrays.asList(85, 83, 81, 67, 65, 39, 37, 35, 21, 17, 5)));
        camp.distributeThings(3, "Hide");
        camp.increaseProsperityBoxes(1);
    }
}
