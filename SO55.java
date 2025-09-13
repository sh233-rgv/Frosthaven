import javax.swing.*;
import java.util.ArrayList;

public class SO55 extends Event {
    public SO55(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO55.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO55.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 395, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);
        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(132, 74, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
                System.out.println("If you kill an Algox next scenario, gain 20 gold (to be done)");
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 439, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.ancestryInParty("Algox")) {
                buttonc.setBounds(126, 219, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                    camp.increaseMorale(1);
                });
            } else {
                buttonc.setBounds(101, 293, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                });
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        if (camp.ancestryInParty("Algox")) {
            JButton button3 = new JButton("Option C");
            button3.setBounds(30, 483, 50, 20);
            button3.setFont(Methods.DEFAULTFONT);

            button3.addActionListener(_ ->
            {
                buttonClicked(dialog, layeredPane);
                JButton buttonc = new JButton("Continue");
                buttonc.setBounds(180, 410, 55, 20);
                buttonc.setFont(Methods.DEFAULTFONT);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            int num = camp.distributeToOne(20, "Gold", "Algox");
                            System.out.println(camp.activeCharacters.get(num) + " starts with wound (to be done)");
                        }).start());
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            });
            buttons.add(button3);
        }
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

}
