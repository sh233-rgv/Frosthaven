import javax.swing.*;
import java.util.ArrayList;

/**
 * Write a description of class SO_1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SO54 extends Event {

    /**
     * Constructor for objects of class SO_1
     */
    public SO54(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO54.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO54.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 425, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 453, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Persuasive") || camp.ancestryInParty("Unfettered")) {
                buttonc.setBounds(125, 176, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                    camp.townGuardDeck.add(new AttackModifier("+20", 20, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+20.png"));
                    camp.increaseSoldiers(1);
                });
            } else {
                buttonc.setBounds(101, 249, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                });
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.setBounds(101, 409, 55, 20);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

}
