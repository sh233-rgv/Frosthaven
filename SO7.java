import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SO7 extends Event {
    public SO7(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO7.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO7.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 80, 104, 305, 278);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(1);
        threat(15);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        System.out.println("All character start next scenario with wound (to be done)");
        threat(10);
    }

    private void threat(int attackVal) {
        camp.outpostAttack(attackVal, 4, camp.chooseOutpostAttackTargeting(new ArrayList<>(Arrays.asList(5, 17, 21, 35, 37, 39)), 4));
        camp.distributeThings(2, "Lumber");
        camp.distributeThings(2, "Hide");
    }
}