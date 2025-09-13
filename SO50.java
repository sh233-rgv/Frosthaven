import javax.swing.*;
import java.util.ArrayList;

public class SO50 extends Event {
    public SO50(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO50.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO50.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 453, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 482, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.setBounds(240, 161, 55, 20);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                        dialog.dispose();
                        camp.increaseMorale(2);
                        camp.distributeThings(3, "Lumber");
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.ancestryInParty("Unfettered")) {
                buttonc.setBounds(168, 292, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.increaseMorale(-1);
                            camp.distributeThings(3, "Lumber");
                        }).start());
            } else {
                buttonc.setBounds(240, 496, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.increaseMorale(1);
                            camp.distributeThings(3, "Lumber");
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

}
