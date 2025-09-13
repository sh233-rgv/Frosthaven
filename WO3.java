import javax.swing.*;
import java.util.ArrayList;

public class WO3 extends Event {
    public WO3(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO3.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO3.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 127, 118, 306, 264);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(-1);
        threat(15, 7);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(25, 4);
        System.out.println("Characters start with 1 Damage (to be done)");
    }

    public void threat(int attackVal, int targets) {
        camp.outpostAttack(attackVal, targets, camp.randomBuildings());
        camp.giveToEach(1, "Metal");
        System.out.println("Characters start with Poison (to be done)");
    }
}