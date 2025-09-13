import javax.swing.*;
import java.util.ArrayList;

public class WO7 extends Event {
    public WO7(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO7.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO7.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 468, 79, 88, 250, 250);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(2, 0);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(5, 1);
    }

    private void threat(int targets, int attacksWithD) {
        camp.outpostAttack(20, targets, camp.EVENBUILDINGSNUMS, 0, attacksWithD);
        camp.calendarSections.get(30 + (camp.week / 20) * 20).add(47.1);
    }
}