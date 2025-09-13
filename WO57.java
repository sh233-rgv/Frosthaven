import javax.swing.*;
import java.util.ArrayList;

public class WO57 extends Event {
    public WO57(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO57.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO57.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 453, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 483, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            if (!camp.traitInParty("Outcast")) {
                buttonClicked(dialog, layeredPane);
                JButton buttonc = new JButton("Continue");
                buttonc.setBounds(145, 191, 55, 20);
                buttonc.setFont(Methods.DEFAULTFONT);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                    dialog.dispose();
                    camp.increaseProsperityBoxes(1);
                });
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            } else {
                button2.doClick();
            }
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(145, 424, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                dialog.dispose();
                camp.increaseProsperityBoxes(-1);
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }
}