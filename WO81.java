import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO81 extends Event {
    public WO81(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO81.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO81.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 78, 88, 76, 234);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(35, 0);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(40, 1);
    }

    public void threat(int attackVal, int reduceTargets) {
        int targets = camp.countCampaignStickers("Coral Shard");
        if (targets - reduceTargets > 0) {
            camp.outpostAttack(true, attackVal, targets - reduceTargets,
                    new ArrayList<>(Arrays.asList(98, 90, 88, 85, 84, 83, 81, 74, 72, 67, 65, 44, 42, 39, 37, 35, 34, 24, 21, 17, 12, 5)), true);
        }
        camp.distributeThings(4, "Hide");
        camp.increaseMorale(2);
    }
}