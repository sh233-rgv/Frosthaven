import javax.swing.*;
import java.util.ArrayList;

public class SO49 extends Event {
    public SO49(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO49.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO49.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 409, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 439, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.canPay("Gold", 10)) {
                buttonc.setBounds(297, 146, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(-10, "Gold");
                            camp.distributeThings(5, "Lumber");
                            camp.addEvent("Summer Outpost", 50);
                        }).start());
            } else {
                buttonc.setBounds(101, 220, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                });
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.ancestryInParty("Unfettered")) {
                buttonc.setBounds(126, 337, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                    camp.increaseMorale(-1);
                });
            } else {
                buttonc.setBounds(297, 424, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                    camp.addEvent("Summer Outpost", 50);
                });
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

}
