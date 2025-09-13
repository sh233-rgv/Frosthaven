import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SO31 extends Event {
    public SO31(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO31.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO31.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 78, 104, 288, 293);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.outpostAttack(30, 4, camp.getClosestTargeting(camp.chooseOutpostAttackTargeting(new ArrayList<>(Arrays.asList(34, 35, 37, 39, 42, 44, 65, 67)), 1).getFirst()));
        camp.distributeThings(3, "Metal");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        Character c = camp.selectCharacter();
        int num = camp.outpostAttackNonBuilding(35, 4);
        System.out.println(c.toString() + " starts with " + num * 2 + " damage (to be done)");
        camp.distributeThings(3, "Metal");
    }
}