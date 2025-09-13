import javax.swing.*;
import java.util.ArrayList;

public class WO71 extends Event {
    public WO71(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO71.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO71.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 222, 147, 101, 293);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.distributeThings(10, "Gold");
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat();
    }

    public void threat() {
        ArrayList<Integer> fullTargets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> targets = new ArrayList<>();
            for (int j = 0; j < camp.unlockedBuildings.size(); j++) {
                if (camp.unlockedBuildings.get(j).level == i) {
                    targets.add(camp.unlockedBuildings.get(j).number);
                }
            }
            if (!targets.isEmpty()) {
                ArrayList<Integer> temp = camp.chooseOutpostAttackTargeting(targets, 4);
                for (int integer : temp) {
                    if (fullTargets.size() < 4) {
                        fullTargets.add(integer);
                    }
                }
                if (fullTargets.size() >= 4) {
                    i = 10;
                }
            }
        }
        camp.outpostAttack(45, 4, fullTargets);
        camp.distributeThings(3, "Hide");
    }
}
