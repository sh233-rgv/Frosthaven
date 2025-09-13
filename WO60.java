import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO60 extends Event {
    public WO60(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO60.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO60.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 222, 161, 101, 322);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(1);
        camp.arrowvine = Math.max(camp.arrowvine - 3, 0);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat();
    }

    public void threat() {
        camp.outpostAttack(45, 5, camp.chooseOutpostAttackTargeting(new ArrayList<>(Arrays.asList(35, 37, 39, 65, 67, 81, 83, 85)), 5));
    }
}