import javax.swing.*;
import java.util.ArrayList;

public class WO52 extends Event {
    public WO52(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO52.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO52.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 468, 246, 206, 126, 439);
    }

    @Override
    public void optionA() {
        camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(true);
    }

    @Override
    public void optionB() {
        camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        if (camp.findInTownGuardDeck("+10") != -1) {
            camp.townGuardDeck.remove(camp.findInTownGuardDeck("+10"));
        }
        camp.increaseMorale(-3);
        threat(false);
    }

    public void threat(boolean wreckNegativeModifier) {
        camp.outpostAttack(60, wreckNegativeModifier, 22, camp.chooseOutpostAttackTargeting(camp.BUILDINGSNUMS, 22));
    }
}