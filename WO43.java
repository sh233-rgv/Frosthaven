import javax.swing.*;
import java.util.ArrayList;

public class WO43 extends Event {
    public WO43(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO43.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO43.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 77, 59, 126, 278);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(3);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(2);
        threat(0);
    }

    public void threat(int reduceTargets) {
        int numTargets = 0;
        for (int i = 0; i < camp.unlockedBuildings.size(); i++) {
            if (camp.unlockedBuildings.get(i).level != 0 && !camp.unlockedBuildings.get(i).wrecked) {
                numTargets++;
            }
        }
        numTargets -= reduceTargets;
        camp.outpostAttack(30, true, numTargets, camp.chooseOutpostAttackTargeting(camp.BUILDINGSNUMS, numTargets));
        camp.distributeThings(5, "Metal");
    }
}