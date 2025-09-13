import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO32 extends Event {
    public WO32(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO32.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO32.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 227, 118, 83, 308);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        int[] evenClosestDocks = new int[]{84, 88, 74, 98, 12, 72, 90, 42, 34, 44, 24};
        for (int i = 0; i < 11; i++) {
            if (camp.findBuildingInUnlocked(evenClosestDocks[i]) != -1 && camp.unlockedBuildings.get(camp.findBuildingInUnlocked(evenClosestDocks[i])).level != 0 &&
                    !camp.unlockedBuildings.get(camp.findBuildingInUnlocked(evenClosestDocks[i])).wrecked) {
                camp.unlockedBuildings.get(camp.findBuildingInUnlocked(evenClosestDocks[i])).wrecked = true;
                i = 11;
            }
        }
        threat(50, 5);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseProsperityBoxes(-1);
        threat(40, 5);
    }

    public void threat(int attackVal, int attacksWithD) {
        camp.outpostAttack(attackVal, 5, new ArrayList<>(Arrays.asList(84, 88, 74, 98, 12, 72, 90, 42, 34, 44, 24)), 0, attacksWithD);
        camp.giveToEach(10, "Experience");
    }
}