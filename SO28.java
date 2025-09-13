import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SO28 extends Event {
    public SO28(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO28.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO28.png", camp);
    }

    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 409, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 453, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            if (camp.canPay("Lumber", 3)) {
                buttonClicked(dialog, layeredPane);
                JButton buttonc = new JButton("Continue");
                buttonc.setFont(Methods.DEFAULTFONT);
                buttonc.setBounds(101, 89, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(-3, "Lumber");
                        }).start());
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            } else {
                button2.doClick();
            }
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (!((camp.week - 8) >= 0 && (camp.week - 8) % 20 <= 2)) {
                buttonc.setBounds(45, 471, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            ArrayList<Integer> buildings = new ArrayList<>(Arrays.asList(37, 39, 65, 5, 24, 34, 12, 74, 84, 88, 98, 17, 85));
                            camp.wreckBuildingOfChoice(buildings);
                            camp.wreckBuildingOfChoice(buildings);
                            camp.increaseMorale(1);
                        }).start());
            } else {
                buttonc.setBounds(101, 351, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
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