import javax.swing.*;
import java.util.ArrayList;

public class WO26 extends Event {
    public WO26(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO26.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO26.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 424, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 468, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Strong")) {
                buttonc.setBounds(126, 162, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            camp.increaseMorale(1);
                            threat(40);
                        }).start());
            } else {
                buttonc.setBounds(119, 74, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(40);
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.setBounds(78, 337, 55, 20);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                        dialog.dispose();
                        camp.increaseMorale(-1);
                        threat(30);
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

    public void threat(int attackVal) {
        camp.outpostAttack(attackVal, 3, camp.randomBuildings(), true);
    }
}