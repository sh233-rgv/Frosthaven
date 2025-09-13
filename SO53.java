import javax.swing.*;
import java.util.ArrayList;

public class SO53 extends Event {
    public SO53(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO53.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO53.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton buttonc = new JButton("Continue");
        buttonc.setBounds(133, 409, 55, 20);
        buttonc.setFont(Methods.DEFAULTFONT);
        buttonc.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton button1 = new JButton("Option A");
            button1.setBounds(19, 424, 50, 20);
            button1.setFont(Methods.DEFAULTFONT);

            JButton button2 = new JButton("Option B");
            button2.setBounds(19, 453, 50, 20);
            button2.setFont(Methods.DEFAULTFONT);

            button1.addActionListener(_ ->
            {
                camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
                System.out.println("(to be done): Read Section 187.3");
            });

            button2.addActionListener(_ ->
            {
                camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
                System.out.println("(to be done): Read Section 144.2");
            });
            layeredPane.add(button1, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);
        });

        buttons.add(buttonc);

        return buttons;
    }

}
