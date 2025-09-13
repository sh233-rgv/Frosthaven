import javax.swing.*;
import java.util.ArrayList;

public class SO56 extends Event {
    public SO56(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO56.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO56.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 409, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 439, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(e ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(218, 177, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(e1 ->
            {
                camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
                camp.buildingOperationsModifiers.add("2 extra gold for hide sold from Hide Depot");
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(e ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.canPay("Hide", 1) && camp.flamefruit > 10) {
                buttonc.setBounds(128, 410, 55, 20);
                buttonc.addActionListener(e1 ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(-1, "Hide");
                            camp.flamefruit--;
                            camp.distributeThings(15, "Gold");
                            camp.increaseMorale(2);
                        }).start());
            } else {
                buttonc.setBounds(101, 497, 55, 20);
                buttonc.addActionListener(e1 ->
                {
                    camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                    dialog.dispose();
                });
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

}
