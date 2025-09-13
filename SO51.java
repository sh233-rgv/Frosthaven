import javax.swing.*;
import java.util.ArrayList;

public class SO51 extends Event {
    public SO51(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO51.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO51.png", camp);
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
            buttonc.setBounds(131, 264, 55, 20);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                        dialog.dispose();
                        camp.distributeThings(-15, "Gold");
                        System.out.println("Fight: Attack Modifiers (to be done)");
                        boolean win = true;
                        if (win) {
                            camp.increaseMorale(2);
                            camp.distributeThings(30, "Gold");
                        }
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Chaotic")) {
                buttonc.setBounds(255, 497, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                    camp.increaseMorale(1);
                    System.out.println("All characters start with 2 damage (to be done)");
                    camp.giveToEach(10, "Experience");
                });
            } else {
                buttonc.setBounds(45, 439, 55, 20);
                buttonc.addActionListener(_ ->
                {
                    camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                    camp.increaseMorale(1);
                    System.out.println("All characters start with 2 damage (to be done)");
                });
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

}
