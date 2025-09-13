import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO40 extends Event {
    public WO40(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO40.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO40.png", camp);
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
            buttonc.setBounds(222, 118, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                        dialog.dispose();
                        camp.increaseMorale(-1);
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Arcane")) {
                buttonc.setBounds(101, 249, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(55, 4);
                        }).start());
            } else {
                buttonc.setBounds(120, 351, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(65, 6);
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

    public void threat(int attackVal, int targets) {
        camp.outpostAttack(attackVal, targets, camp.chooseOutpostAttackTargeting(new ArrayList<>(Arrays.asList(65, 67, 81, 83, 85)), targets));
        camp.distributeThings(3, "Lumber");
    }
}
