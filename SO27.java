import javax.swing.*;
import java.util.ArrayList;

public class SO27 extends Event {
    public SO27(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO27.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO27.png", camp);
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
            if (camp.canPay("Gold", 3)) {
                buttonc.setBounds(127, 103, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(-3, "Gold");
                            camp.increaseMorale(1);
                        }).start());
            } else {
                buttonc.setBounds(101, 205, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Persuasive")) {
                buttonc.setBounds(278, 380, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.increaseMorale(1);
                            camp.distributeThings(10, "Gold");
                            camp.rockroot++;
                        }).start());
            } else {
                buttonc.setBounds(160, 497, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                    camp.rockroot += 2;
                });
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }
}