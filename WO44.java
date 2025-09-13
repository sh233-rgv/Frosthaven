import javax.swing.*;
import java.util.ArrayList;

public class WO44 extends Event {
    public WO44(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO44.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO44.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 424, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 468, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            Character[] c = new Character[1];
            new Thread(() ->
                    c[0] = camp.selectCharacter()).start();
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(222, 179, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                        dialog.dispose();
                        c[0].increaseExperience(10);
                        System.out.println(c[0] + " starts with wound and loses a card (to be done)");
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.setBounds(82, 293, 55, 20);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                        dialog.dispose();
                        camp.outpostAttack(55, 4, camp.ODDBUILDINGSNUMS);
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }
}
