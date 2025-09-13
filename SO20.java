import javax.swing.*;
import java.util.ArrayList;

public class SO20 extends Event {
    public SO20(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO20.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO20.png", camp);
    }

    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 410, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);
        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(102, 89, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
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
            buttonc.setBounds(132, 264, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
                camp.increaseSoldiers(2);
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        JButton button3 = new JButton("Option C");
        button3.setBounds(30, 483, 50, 20);
        button3.setFont(Methods.DEFAULTFONT);

        button3.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(282, 497, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
                camp.calendarSections.get(camp.week + 3).add(41.3);
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        return buttons;
    }
}