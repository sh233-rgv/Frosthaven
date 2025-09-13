import javax.swing.*;
import java.util.ArrayList;

public class SO13 extends Event {
    public SO13(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO13.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO13.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();

        if (camp.canPay("Snowthistle", 1)) {
            JButton button1 = new JButton("Option A");
            button1.setBounds(30, 409, 50, 20);
            button1.setFont(Methods.DEFAULTFONT);

            button1.addActionListener(_ ->
            {
                buttonClicked(dialog, layeredPane);
                JButton buttonc = new JButton("Continue");
                buttonc.setFont(Methods.DEFAULTFONT);
                if (camp.ancestryInParty("Valrath")) {
                    buttonc.setBounds(167, 118, 55, 20);
                    buttonc.addActionListener(_ ->
                            new Thread(() ->
                            {
                                camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                                dialog.dispose();
                                camp.increaseMorale(1);
                                camp.distributeThings(15, "Gold");
                                camp.snowthistle = 0;
                            }).start());
                    layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
                } else {
                    buttonc.setBounds(254, 220, 55, 20);
                    buttonc.addActionListener(_ ->
                            new Thread(() ->
                            {
                                camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                                dialog.dispose();
                                int num = -5 * camp.loseAnyNumThings("Snowthistle");
                                camp.distributeThings(num, "Gold");
                            }).start());
                    layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
                }
            });
            buttons.add(button1);
        }

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 439, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);

            if (camp.traitInParty("Persuasive")) {
                buttonc.setBounds(102, 351, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                });
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            } else {
                buttonc.setBounds(102, 452, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                });
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            }
        });
        buttons.add(button2);

        return buttons;
    }
}