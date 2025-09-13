import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO38 extends Event {
    public WO38(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO38.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO38.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 468, 216, 132, 126, 307);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseProsperityBoxes(-1);
        camp.increaseMorale(-1);
        threat(35, 4);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(1);
        threat(40, 7);
    }

    public void threat(int attackVal, int targets) {
        camp.outpostAttack(attackVal, targets, camp.chooseOutpostAttackTargeting(new ArrayList<>(Arrays.asList(34, 42, 44, 72, 74, 84, 88, 90, 98)), targets));
        camp.increaseMorale(1);
    }

    @Override
    public void buttonClicked(JDialog dialog, JLayeredPane layeredPane) {
        new Thread(() ->
        {
            ArrayList<Integer> buildings = new ArrayList<>(Arrays.asList(34, 42, 44));
            camp.wreckBuildingOfChoice(buildings);
            camp.wreckBuildingOfChoice(buildings);
            super.buttonClicked(dialog, layeredPane);
        }).start();
    }
}