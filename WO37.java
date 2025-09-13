import javax.swing.*;
import java.util.ArrayList;

public class WO37 extends Event {
    public WO37(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO37.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO37.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 200, 60, 101, 264);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(200, 200);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(150, 0);
    }

    public void threat(int attackVal, int attacksWithA) {
        int targets = 0;
        for (int i = 0; i < camp.unlockedBuildings.size(); i++) {
            if (camp.unlockedBuildings.get(i).level != 0 && !camp.unlockedBuildings.get(i).wrecked) {
                targets++;
            }
        }
        int num = camp.outpostAttackNonBuilding(attackVal, targets, attacksWithA, 0, true);
        camp.increaseMorale(-num);
        camp.increaseMorale(4);
    }
}