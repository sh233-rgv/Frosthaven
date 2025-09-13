import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO22 extends Event {
    public WO22(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO22.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO22.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 280, 89, 101, 264);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(2);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(0);
    }

    public void threat(int attacksWithD) {
        camp.outpostAttack(30, 5, camp.chooseOutpostAttackTargeting(new ArrayList<>(Arrays.asList(12, 24, 34, 42, 44)), 5), 0, attacksWithD);
        camp.giveToEach(10, "Experience");
    }
}