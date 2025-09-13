import javax.swing.*;
import java.util.ArrayList;

public class WO56 extends Event {
    public WO56(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO56.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO56.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 222, 118, 101, 249);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.arrowvine = 0;
        camp.axenut = 0;
        camp.corpsecap = 0;
        camp.flamefruit = 0;
        camp.rockroot = 0;
        camp.snowthistle = 0;
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat();
    }

    public void threat() {
        camp.outpostAttack(60, 6, camp.randomBuildings());
        camp.calendarSections.get(camp.week + 1).add(159.2);
    }
}