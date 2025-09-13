import javax.swing.*;
import java.util.ArrayList;

public class WO46 extends Event {
    public WO46(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO46.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO46.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 483, 45, 104, 126, 278);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(4, 98);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(-2);
        threat(0, 0);
    }

    public void threat(int attacksWithD, int nonDisadvantageBuilding) {
        camp.outpostAttack(40, 4, camp.randomBuildings(), 0, attacksWithD, nonDisadvantageBuilding);
    }
}