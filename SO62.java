import javax.swing.*;
import java.util.ArrayList;

public class SO62 extends Event {
    public SO62(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO62.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO62.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 220, 132, 220, 409);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.townGuardDeck.add(new AttackModifier("+30", 30, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+30 Frostguard.png"));
        camp.addEvent("Winter Outpost", 78);
        camp.addEvent("Summer Road", 45);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseSoldiers(2);
        camp.townGuardDeck.add(new AttackModifier("+10 Rolling", 10, true, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+10 Rolling Frostguard.png"));
        camp.townGuardDeck.add(new AttackModifier("+10 Rolling", 10, true, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+10 Rolling Frostguard.png"));
        camp.addEvent("Winter Outpost", 78);
        camp.addEvent("Summer Road", 45);
    }

}
