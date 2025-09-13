import javax.swing.*;
import java.util.ArrayList;

public class SO10 extends Event {
    public SO10(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO10.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO10.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        if (camp.canPay("Gold", 15)) {
            JButton button1 = new JButton("Option A");
            button1.setBounds(30, 410, 50, 20);
            button1.setFont(Methods.DEFAULTFONT);
            button1.addActionListener(_ ->
            {
                buttonClicked(dialog, layeredPane);
                JButton buttonc = new JButton("Continue");
                buttonc.setBounds(93, 118, 55, 20);
                buttonc.setFont(Methods.DEFAULTFONT);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(-15, "Gold");
                            if ((((camp.week + 2) / 10) % 2 == 0)) {
                                camp.calendarSections.get(camp.week + 3).add(103.2);
                            } else {
                                camp.calendarSections.get(((camp.week + 15) / 10) * 10 + 2).add(103.2);
                            }
                        }).start());
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            });
            JButton button2 = new JButton("Option B");
            button2.setBounds(30, 439, 50, 20);
            button2.setFont(Methods.DEFAULTFONT);

            button2.addActionListener(_ ->
            {
                buttonClicked(dialog, layeredPane);
                JButton buttonc = new JButton("Continue");
                buttonc.setBounds(93, 294, 55, 20);
                buttonc.setFont(Methods.DEFAULTFONT);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(-15, "Gold");
                            if ((((camp.week + 2) / 10) % 2 == 0)) {
                                camp.calendarSections.get(camp.week + 3).add(179.5);
                            } else {
                                camp.calendarSections.get(((camp.week + 15) / 10) * 10 + 2).add(179.5);
                            }
                        }).start());
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            });
            buttons.add(button1);
            buttons.add(button2);
        }
        JButton button3 = new JButton("Option C");
        button3.setBounds(30, 468, 50, 20);
        button3.setFont(Methods.DEFAULTFONT);

        button3.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(102, 409, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                        dialog.dispose();
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        buttons.add(button3);
        return buttons;
    }
}