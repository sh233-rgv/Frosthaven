import javax.swing.*;
import java.util.ArrayList;

public class WO54 extends Event {
    public WO54(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO54.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO54.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 468, 80, 60, 264, 55);
    }

    @Override
    public void optionA() {
        camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(65);
    }

    @Override
    public void optionB() {
        camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.wreckBuildingOfChoice(camp.BUILDINGSNUMS);
        threat(50);
    }

    public void threat(int attackVal) {
        camp.outpostAttack(true, attackVal, 8, camp.chooseOutpostAttackTargeting(camp.BUILDINGSNUMS, 1));
        camp.increaseProsperityBoxes(-1);
    }
}