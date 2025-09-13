import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO42 extends Event {
    public WO42(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO42.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO42.png", camp);
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
            if (camp.traitInParty("Nimble") || camp.ancestryInParty("Quatryl")) {
                buttonc.setBounds(78, 162, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(4, 4, true);
                        }).start());
            } else {
                buttonc.setBounds(225, 89, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(7, 7, true);
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.setBounds(45, 323, 55, 20);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                        dialog.dispose();
                        threat(7, 0, false);
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

    public void threat(int numTargets, int attacksWithD, boolean wallDefense) {
        camp.outpostAttack(35, numTargets, wallDefense, new ArrayList<>(Arrays.asList(98, 5, 74, 84, 37, 24, 88, 39, 85, 17,
                12, 34, 65, 81, 67, 35, 72, 21, 44, 83, 90, 42)), 0, attacksWithD);
        camp.distributeThings(3, "Metal");
    }
}
