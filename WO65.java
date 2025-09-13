import javax.swing.*;
import java.util.ArrayList;

public class WO65 extends Event {
    public WO65(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO65.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO65.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 276, 90, 273, 497);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        if (camp.findInTownGuardDeck("+20") != -1) {
            camp.townGuardDeck.remove(camp.findInTownGuardDeck("+20"));
        }
        camp.calendarSections.get(camp.week + 2).add(179.1);
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        if (camp.findInTownGuardDeck("+20") != -1) {
            camp.townGuardDeck.remove(camp.findInTownGuardDeck("+20"));
        }
        if (camp.findInTownGuardDeck("Success") != -1) {
            camp.townGuardDeck.remove(camp.findInTownGuardDeck("Success"));
        }
        camp.calendarSections.get(camp.week + 2).add(28.4);
    }
}
