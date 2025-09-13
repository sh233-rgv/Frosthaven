import javax.swing.*;
import java.util.ArrayList;

public class WO58 extends Event {
    public WO58(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO58.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO58.png", camp);
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

        Character[] selectedCharacter = new Character[1];

        button1.addActionListener(_ ->
        {
            selectedCharacter[0] = camp.selectCharacter();
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (selectedCharacter[0].countMasteries() == 2) {
                buttonc.setBounds(242, 89, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            selectedCharacter[0].gold += 20;
                        }).start());
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            } else if (selectedCharacter[0].countMasteries() == 1) {
                buttonc.setBounds(242, 147, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            selectedCharacter[0].gold += 10;
                        }).start());
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            } else {
                button2.doClick();
            }
        });

        button2.addActionListener(_ ->
        {
            if (selectedCharacter[0] == null) {
                selectedCharacter[0] = camp.selectCharacter();
            }
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (selectedCharacter[0].level == 9) {
                buttonc.setBounds(224, 351, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            camp.increaseMorale(1);
                            camp.inspiration += 2;
                        }).start());
            } else if (selectedCharacter[0].level >= 5) {
                buttonc.setBounds(126, 424, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            camp.increaseMorale(1);
                        }).start());
            } else {
                buttonc.setBounds(101, 497, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }
}