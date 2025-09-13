import javax.swing.*;
import java.util.ArrayList;

public class WO75 extends Event {
    public WO75(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO75.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO75.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 409, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 453, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(e1 ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Resourceful")) {
                buttonc.setBounds(126, 103, 55, 20);
                buttonc.addActionListener(e ->
                {
                    camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                    dialog.dispose();
                    camp.increaseMorale(1);
                });
            } else {
                buttonc.setBounds(101, 234, 55, 20);
                buttonc.addActionListener(e ->
                {
                    camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                    dialog.dispose();
                });
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(e ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Resourceful")) {
                buttonc.setBounds(126, 395, 55, 20);
                buttonc.addActionListener(e1 ->
                {
                    camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                    dialog.dispose();
                    camp.increaseMorale(1);
                });
            } else {
                buttonc.setBounds(101, 497, 55, 20);
                buttonc.addActionListener(e1 ->
                {
                    camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                    dialog.dispose();
                });
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }
}
