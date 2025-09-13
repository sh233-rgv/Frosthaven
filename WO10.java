import javax.swing.*;
import java.util.ArrayList;

public class WO10 extends Event {
    public WO10(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO10.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO10.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 439, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 468, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton();
            JButton buttonc2 = new JButton();
            boolean[] canPay = {camp.canPay("Gold", 25), camp.canPay("Gold", 15)};
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc2.setFont(Methods.DEFAULTFONT);

            if (canPay[0]) {
                buttonc.setText("68.1");
                buttonc.setBounds(87, 89, 25, 20);
                buttonc.addActionListener(_ ->
                        actionP(dialog, 25, 68.1));
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
                buttonc2.setText("131.1");
                buttonc2.setBounds(129, 89, 28, 20);
                buttonc2.addActionListener(_ ->
                        actionP(dialog, 25, 131.1));
                layeredPane.add(buttonc2, JLayeredPane.PALETTE_LAYER);
            } else if (canPay[1]) {
                buttonc.setText("155.2");
                buttonc.setBounds(88, 191, 28, 20);
                buttonc.addActionListener(_ ->
                        actionP(dialog, 15, 155.2));
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
                buttonc2.setText("158.3");
                buttonc2.setBounds(137, 191, 28, 20);
                buttonc2.addActionListener(_ ->
                        actionP(dialog, 15, 158.3));
                layeredPane.add(buttonc2, JLayeredPane.PALETTE_LAYER);
            } else {
                button2.doClick();
            }
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            if (camp.traitInParty("Persuasive") || camp.traitInParty("Resourceful")) {
                JButton buttonc = new JButton("155.2");
                buttonc.setFont(Methods.DEFAULTFONT);
                JButton buttonc2 = new JButton("158.3");
                buttonc2.setFont(Methods.DEFAULTFONT);
                buttonc.setBounds(88, 395, 28, 20);
                buttonc.addActionListener(_ ->
                        actionP(dialog, 0, 155.2));
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
                buttonc2.setBounds(137, 395, 28, 20);
                buttonc2.addActionListener(_ ->
                        actionP(dialog, 0, 158.3));
                layeredPane.add(buttonc2, JLayeredPane.PALETTE_LAYER);
            } else {
                JButton buttonc = new JButton("Continue");
                buttonc.setFont(Methods.DEFAULTFONT);
                buttonc.setBounds(101, 496, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.activeWinterOutpost.removeFirst();
                    dialog.dispose();
                });
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            }
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

    public void actionP(JDialog dialog, int goldCost, double section) {
        new Thread(() ->
        {
            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
            dialog.dispose();
            camp.distributeThings(-goldCost, "Gold");
            camp.calendarSections.get(camp.week + 1).add(section);
        }).start();
    }
}