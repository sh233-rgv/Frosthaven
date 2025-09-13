import javax.swing.*;
import java.util.ArrayList;

public class SO4 extends Event {
    public SO4(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO4.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO4.png", camp);
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

        JButton button3 = new JButton("Option C");
        button3.setBounds(30, 530, 50, 20);
        button3.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(168, 74, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                        dialog.dispose();
                        camp.distributeThings(-5, "Gold");
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(168, 249, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                        dialog.dispose();
                        camp.distributeThings(-5, "Gold");
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button3.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(189, 483, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                        dialog.dispose();
                        camp.distributeItem("Lucky Dice");
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);

        return buttons;
    }
}