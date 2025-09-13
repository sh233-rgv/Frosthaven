import javax.swing.*;
import java.util.ArrayList;

public class WO18 extends Event {
    public WO18(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO18.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO18.png", camp);
    }

    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 409, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 439, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Strong") || camp.ancestryInParty("Unfettered")) {
                buttonc.setBounds(266, 151, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                    dialog.dispose();
                    System.out.println("All characters start with Wound (to be done)");
                    System.out.println("All characters start with 2 Damage (to be done)");
                    System.out.println("Unlock Scenario 120 (to be done)");
                });
            } else {
                buttonc.setBounds(266, 265, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                    dialog.dispose();
                    System.out.println("All characters start with Wound (to be done)");
                    System.out.println("Unlock Scenario 120 (to be done)");
                });
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.ancestryInParty("Aesther") || camp.ancestryInParty("Lurker") ||
                    camp.ancestryInParty("Vermling") || camp.ancestryInParty("Unfettered")) {
                buttonc.setBounds(266, 395, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                    dialog.dispose();
                    System.out.println("Unlock Scenario 120 (to be done)");
                });
            } else {
                button1.doClick();
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }
}