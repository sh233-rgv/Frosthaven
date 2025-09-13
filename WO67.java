import javax.swing.*;
import java.util.ArrayList;

public class WO67 extends Event {
    public WO67(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO67.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO67.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 202, 177, 215, 307);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(false);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(true);
    }

    private void threat(boolean wantToDefend) {
        int targets = 0;
        for (int i = 0; i < 5; i++) {
            if (camp.findBuildingInUnlocked(camp.EVENBUILDINGSNUMS.get(i)) != -1 && camp.unlockedBuildings.get(camp.findBuildingInUnlocked(camp.EVENBUILDINGSNUMS.get(i))).level != 0 &&
                    !camp.unlockedBuildings.get(camp.findBuildingInUnlocked(camp.EVENBUILDINGSNUMS.get(i))).wrecked) {
                targets++;
            }
        }
        //if !wanttoDefend, draw modifier for each character (to be done)
        int num = camp.outpostAttackNonBuilding(35, targets);
        if (wantToDefend) {
            camp.increaseMorale(targets - num);
        } else {
            camp.increaseMorale(num);
        }
        camp.giveToEach(1, "Checkmark");
    }
}