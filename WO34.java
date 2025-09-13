import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO34 extends Event {
    public WO34(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO34.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO34.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 395, 439, 78, 118, 319, 307);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(60, false);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(-1);
        threat(55, true);
    }

    public void threat(int attackVal, boolean wreckDamaged) {
        camp.outpostAttack(attackVal, 5, new ArrayList<>(Arrays.asList(39, 37, 35, 34, 24, 21, 17, 12, 5)), wreckDamaged);
        camp.increaseMorale(-1);
        camp.distributeThings(2, "Lumber");
    }
}