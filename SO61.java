import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SO61 extends Event {
    private boolean checkedBox;

    public SO61(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO61.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO61.png", camp);
        checkedBox = false;
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
            int[] num = {6};
            if (checkedBox) {
                num[0] = 3;
            }
            if (camp.canPay("Metal", num[0])) {
                buttonClicked(dialog, layeredPane);
                checkedBoxes(layeredPane);
                JButton buttonc = new JButton("Continue");
                buttonc.setFont(Methods.DEFAULTFONT);
                buttonc.setBounds(192, 220, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(-num[0], "Metal");
                            camp.enhanceLootCard("Metal");
                        }).start());
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            } else {
                button2.doClick();
            }
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            checkedBoxes(layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(65, 367, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
                checkedBox = true;
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

    private void checkedBoxes(JLayeredPane layeredPane) {
        if (checkedBox) {
            JLabel x1 = new JLabel("X");
            x1.setBounds(49, 366, 55, 20);
            x1.setFont(new Font("Markazi Text", Font.BOLD, 18));
            layeredPane.add(x1, JLayeredPane.PALETTE_LAYER);
        }
    }

}
