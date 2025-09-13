import javax.swing.*;
import java.util.ArrayList;

public class WO64 extends Event {
    public WO64(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO64.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO64.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 424, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 453, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Educated") || camp.ancestryInParty("Quatryl")) {
                buttonc.setBounds(126, 117, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                    dialog.dispose();
                    camp.increaseMorale(1);
                });
            } else {
                buttonc.setBounds(310, 278, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                    dialog.dispose();
                    System.out.println("All characters start with 2 damage (to be done)");
                });
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(145, 497, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                dialog.dispose();
                camp.unlockedBuildings.get(camp.findBuildingInUnlocked(84)).wrecked = true;
                camp.increaseProsperityBoxes(1);
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }
}