import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO50 extends Event {
    public WO50(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO50.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO50.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 126, 118, 83, 293);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(1);
        threat(70);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(60);
    }

    public void threat(int attackVal) {
        camp.outpostAttack(attackVal, 7, new ArrayList<>(Arrays.asList(98, 90, 88, 85, 84, 83, 81, 74, 72, 67, 65, 44, 42, 39, 37, 35, 34, 24, 21, 17, 12)));
        camp.distributeThings(3, "Hide");
    }
}