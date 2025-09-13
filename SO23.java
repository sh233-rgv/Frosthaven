import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SO23 extends Event {
    public SO23(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO23.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO23.png", camp);
    }

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
            if (camp.traitInParty("Educated")) {
                buttonc.setBounds(168, 103, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(3, "Metal");
                            threat(15, 3);
                        }).start());
            } else {
                buttonc.setBounds(305, 205, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            System.out.println("All characters start with Wound (to be done)");
                            threat(15, 4);
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(78, 293, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                        dialog.dispose();
                        threat(10, 4);
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

    private void threat(int attackVal, int targets) {
        camp.unlockedBuildings.get(camp.findBuildingInUnlocked(84)).wrecked = true;
        camp.outpostAttack(attackVal, targets, new ArrayList<>(Arrays.asList(74, 88, 90, 72, 98, 12, 42, 34, 44, 24)));
        camp.giveToEach(1, "Metal");
    }
}