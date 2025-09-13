import javax.swing.*;
import java.util.ArrayList;

public class SO2 extends Event {
    public SO2(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO2.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO2.png", camp);
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
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(307, 190, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
                camp.increaseMorale(1);
                System.out.println("All charactets start next scenario with wound (To be done)");
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Resourceful")) {
                buttonc.setBounds(128, 351, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                    camp.increaseMorale(2);
                });
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            } else {
                buttonc.setBounds(102, 496, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                });
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            }
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }
}