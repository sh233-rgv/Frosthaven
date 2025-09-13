import javax.swing.*;
import java.util.ArrayList;

public class SO25 extends Event {
    public SO25(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO25.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO25.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 117, 89, 117, 249);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        threat(20, 1);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        threat(10, 4);
    }

    private void threat(int attackVal, int numTargets) {
        ArrayList<Integer> fullTargets = new ArrayList<>();
        for (int k = 0; k < 2; k++) {
            for (int i = 1; i < 10; i++) {
                ArrayList<Integer> targets = new ArrayList<>();
                for (int j = 0; j < camp.unlockedBuildings.size(); j++) {
                    if (camp.unlockedBuildings.get(j).level == i && camp.unlockedBuildings.get(j).number % 2 == k) {
                        targets.add(camp.unlockedBuildings.get(j).number);
                    }
                }
                if (!targets.isEmpty()) {
                    ArrayList<Integer> temp = camp.chooseOutpostAttackTargeting(targets, numTargets);
                    for (Integer integer : temp) {
                        if (fullTargets.size() < numTargets) {
                            fullTargets.add(integer);
                        }
                    }
                    if (fullTargets.size() >= numTargets) {
                        i = 0;
                        k = 2;
                    }
                }
            }
        }
        camp.outpostAttack(attackVal, numTargets, fullTargets);
        camp.distributeThings(3, "Hide");
    }
}