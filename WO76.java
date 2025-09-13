import javax.swing.*;
import java.util.ArrayList;

public class WO76 extends Event {
    public WO76(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO76.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO76.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 438, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 483, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Persuasive")) {
                buttonc.setBounds(104, 177, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                    dialog.dispose();
                    System.out.println("Gain one collective material only cost item (to be done)");
                    System.out.println("Gain one random item blueprint (to be done)");
                });
            } else {
                buttonc.setBounds(106, 132, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                    dialog.dispose();
                    System.out.println("Gain one random item blueprint (to be done)");
                });
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(229, 395, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                dialog.dispose();
                System.out.println("Gain one random item blueprint (to be done)");
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }
}