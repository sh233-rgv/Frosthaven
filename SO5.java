import javax.swing.*;
import java.util.ArrayList;

public class SO5 extends Event {
    public SO5(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO5.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO5.png", camp);
    }


    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 424, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 453, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            boolean[] canPay = {camp.canPay("Gold", 10)};
            if (canPay[0]) {
                buttonc.setBounds(126, 103, 55, 20);
            } else {
                buttonc.setBounds(126, 191, 55, 20);
            }
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
                if (canPay[0]) {
                    camp.distributeThings(-10, "Gold");
                    camp.increaseMorale(1);
                } else {
                    camp.increaseMorale(-1);
                }
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(102, 380, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }
}