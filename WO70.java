import javax.swing.*;
import java.util.ArrayList;

public class WO70 extends Event {
    public WO70(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO70.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO70.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 483, 222, 278, 251, 410);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(2);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat();
    }

    public void threat() {
        camp.outpostAttack(40, 5, camp.EVENBUILDINGSNUMS, 0, 1);
    }
}
