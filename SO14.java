import javax.swing.*;
import java.util.ArrayList;

public class SO14 extends Event {
    public SO14(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO14.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO14.png", camp);
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
            if (camp.canPay("Gold", 10) && camp.canPay("Herb", 2)) {
                buttonc.setFont(Methods.DEFAULTFONT);
                buttonc.setBounds(45, 163, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(-10, "Gold");
                            camp.distributeHerbs(-2);
                            camp.increaseMorale(1);
                            camp.addEvent("Summer Outpost", 37);
                            camp.addEvent("Summer Outpost", 38);
                        }).start());
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            } else {
                button2.doClick();
            }
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(126, 468, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
                System.out.println("Gain random side scenario (to be done)");
                camp.increaseSoldiers(1);
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }
}