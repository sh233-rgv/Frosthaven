import javax.swing.*;
import java.util.ArrayList;

public class WO36 extends Event {
    public WO36(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO36.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO36.png", camp);
    }

    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 83, 103, 272, 307);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.outpostAttack(45, 5, camp.chooseOutpostAttackTargeting(camp.BUILDINGSNUMS, 5));
        camp.giveToEach(10, "Experience");
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(-1);
        camp.calendarSections.get(camp.week + 1).add(189.3);
    }
}