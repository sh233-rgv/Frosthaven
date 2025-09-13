import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO4 extends Event {
    public WO4(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO4.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO4.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 468, 127, 88, 306, 264);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(2);
        threat(7);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.wreckBuildingOfChoice(camp.ODDBUILDINGSNUMS);
        threat(4);
    }

    public void threat(int targets) {
        camp.outpostAttack(15, targets, new ArrayList<>(Arrays.asList(85, 37, 35, 39, 21, 67, 17, 83, 65, 81, 5)));
        camp.distributeThings(3, "Hide");
    }
}