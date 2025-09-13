import javax.swing.*;
import java.util.ArrayList;

public class WO69 extends Event {
    public WO69(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO69.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO69.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 453, 483, 128, 138, 126, 424);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.campaignStickers.add("Ice Spike");
        camp.townGuardDeck.add(new AttackModifier("+10 Rolling", 10, true, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+10 Rolling.png"));
        camp.increaseMorale(-1);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.increaseMorale(2);
    }
}
