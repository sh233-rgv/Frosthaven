import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO21 extends Event {
    public WO21(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO21.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO21.png", camp);
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

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Armored")) {
                buttonc.setBounds(226, 89, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(6);
                        }).start());
            } else {
                buttonc.setBounds(306, 205, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            camp.increaseMorale(1);
                            System.out.println("All characters start with wound (to be done)");
                            threat(0);
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.setBounds(126, 321, 55, 20);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                        dialog.dispose();
                        camp.increaseMorale(-1);
                        threat(0);
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

    public void threat(int attacksWithD) {
        camp.outpostAttack(20, 6, new ArrayList<>(Arrays.asList(98, 90, 88, 84, 74, 72, 44, 42, 34, 24, 12, 85, 83, 81, 67, 65, 39, 37, 35, 21, 17, 5)), 0, attacksWithD);
    }
}