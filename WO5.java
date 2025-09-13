import javax.swing.*;
import java.util.ArrayList;

public class WO5 extends Event {
    public WO5(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO5.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO5.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 80, 118, 83, 278);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(35, 3);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(25, 5);
    }

    public void threat(int attackVal, int numTargets) {
        ArrayList<Integer> fullTargets = new ArrayList<>();
        for (int i = 9; i > 0; i--) {
            ArrayList<Integer> targets = new ArrayList<>();
            for (int j = 0; j < camp.unlockedBuildings.size(); j++) {
                if (camp.unlockedBuildings.get(j).level == i) {
                    targets.add(camp.unlockedBuildings.get(j).number);
                }
            }
            if (!targets.isEmpty()) {
                ArrayList<Integer> temp = camp.chooseOutpostAttackTargeting(targets, numTargets);
                for (int integer : temp) {
                    if (fullTargets.size() < numTargets) {
                        fullTargets.add(integer);
                    }
                }
                if (fullTargets.size() >= numTargets) {
                    i = 0;
                }
            }
        }
        camp.outpostAttack(attackVal, numTargets, fullTargets);
        camp.distributeThings(2, "Metal");
    }
}