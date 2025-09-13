import javax.swing.*;
import java.util.ArrayList;

public class SO63 extends Event {
    public SO63(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO63.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO63.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Continue");
        button1.setBounds(245, 469, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            System.out.println("Draw Personal quests for each (to be done)");
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(174, 162, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);

        return buttons;
    }

}
