import javax.swing.*;
import java.util.ArrayList;

public class WO31 extends Event {
    public WO31(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO31.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO31.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 380, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 424, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        JButton button3 = new JButton("Option C");
        button3.setBounds(30, 468, 50, 20);
        button3.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(292, 103, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                dialog.dispose();
                optionA();
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.setBounds(123, 293, 55, 20);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                dialog.dispose();
                optionB();
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        button3.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.canPay("Lumber", 3) && camp.canPay("Metal", 3)) {
                buttonc.setBounds(176, 366, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(-3, "Lumber");
                            camp.distributeThings(-3, "Metal");
                            optionA();
                            optionB();
                        }).start());
            } else {
                buttonc.setBounds(231, 395, 10, 20);
                buttonc.setText("A");
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            optionA();
                        }).start());
                JButton buttonc2 = new JButton("Continue");
                buttonc2.setFont(Methods.DEFAULTFONT);
                buttonc2.setBounds(254, 395, 10, 20);
                buttonc2.setText("B");
                buttonc2.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            optionB();
                        }).start());
                layeredPane.add(buttonc2, JLayeredPane.PALETTE_LAYER);
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);

        return buttons;
    }

    public void optionA() {
        camp.increaseProsperityBoxes(1);
        camp.campaignStickers.add("Portcullis");
        camp.addEvent("Winter Outpost", 59);
    }

    public void optionB() {
        camp.increaseMorale(2);
        camp.campaignStickers.add("Stage");
        camp.addEvent("Winter Outpost", 57);
        camp.addEvent("Winter Outpost", 58);
    }
}