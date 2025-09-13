import javax.swing.*;
import java.util.ArrayList;

public class WO6 extends Event {
    public WO6(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO6.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO6.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 410, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 453, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.morale > 6) {
                buttonc.setBounds(220, 59, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            int attackVal = 25;
                            if (camp.traitInParty("Chaotic")) {
                                attackVal += 5;
                            }
                            threat(attackVal, 5);
                        }).start());
            } else {
                buttonc.setBounds(150, 132, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            int attackVal = 25;
                            if (camp.traitInParty("Chaotic")) {
                                attackVal += 5;
                            }
                            threat(attackVal, 7);
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.morale < 11) {
                buttonc.setBounds(83, 278, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(30, 5);
                        }).start());
            } else {
                buttonc.setBounds(149, 366, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(30, 2);
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

    public void threat(int attackVal, int targets) {
        int currentSoldiers = camp.soldiers;
        camp.soldiers = 0;
        camp.outpostAttack(attackVal, targets, camp.randomBuildings());
        camp.soldiers = currentSoldiers;
    }
}