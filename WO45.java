import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO45 extends Event {
    public WO45(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO45.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO45.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 45, 192, 234, 307);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.wreckBuildingOfChoice(new ArrayList<>(Arrays.asList(72, 74, 84, 88, 90, 98)));
        threat(0);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(1);
    }

    public void threat(int attacksWithA) {
        camp.outpostAttack(60, 6, new ArrayList<>(Arrays.asList(98, 90, 88, 84, 74, 72, 44, 42, 34, 24, 12)), attacksWithA, 0);
        camp.distributeThings(1, "Lumber");
        camp.distributeThings(2, "Metal");
    }
}