import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO28 extends Event {
    public WO28(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO28.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO28.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 227, 103, 126, 264);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(5);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(-1);
        threat(0);
    }

    public void threat(int attacksWithD) {
        camp.outpostAttack(15, 5, camp.chooseOutpostAttackTargeting(new ArrayList<>(Arrays.asList(17, 21, 35, 37, 39, 65, 67)), 5), 0, attacksWithD);
        camp.distributeThings(2, "Lumber");
    }
}