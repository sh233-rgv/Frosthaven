import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO55 extends Event {
    public WO55(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO55.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO55.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 45, 149, 139, 308);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        int num = camp.wreckBuildingOfChoice(camp.ODDBUILDINGSNUMS);
        threat(75, camp.getClosestTargeting(num));
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(65, new ArrayList<>(Arrays.asList(5, 12, 17, 21, 24, 34, 35, 37, 39, 42, 44, 65, 67)));
    }

    public void threat(int attackVal, ArrayList<Integer> targeting) {
        camp.outpostAttack(attackVal, 8, targeting);
    }
}