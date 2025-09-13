import javax.swing.*;
import java.util.ArrayList;

public class SO32 extends Event {
    public SO32(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO32.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO32.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        if (camp.canPay("Metal", 3)) {
            JButton button1 = new JButton("Option A");
            button1.setBounds(30, 409, 50, 20);
            button1.setFont(Methods.DEFAULTFONT);

            button1.addActionListener(_ ->
            {
                buttonClicked(dialog, layeredPane);
                JButton buttonc = new JButton("Continue");
                buttonc.setBounds(222, 132, 55, 20);
                buttonc.setFont(Methods.DEFAULTFONT);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(-3, "Metal");
                        }).start());
                layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
            });
            buttons.add(button1);
        }

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 453, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(305, 483, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                        dialog.dispose();
                        ArrayList<Integer> fullTargets = new ArrayList<>();
                        for (int i = 9; i > 0; i--) {
                            ArrayList<Integer> targets = new ArrayList<>();
                            for (int j = 0; j < camp.unlockedBuildings.size(); j++) {
                                if (camp.unlockedBuildings.get(j).level == i) {
                                    targets.add(camp.unlockedBuildings.get(j).number);
                                }
                            }
                            if (!targets.isEmpty()) {
                                ArrayList<Integer> temp = camp.chooseOutpostAttackTargeting(targets, 1);
                                for (int integer : temp) {
                                    if (fullTargets.isEmpty()) {
                                        fullTargets.add(integer);
                                    }
                                }
                                if (!fullTargets.isEmpty()) {
                                    i = 0;
                                }
                            }
                        }
                        camp.outpostAttack(10000, 1, fullTargets, true);
                        System.out.println("All character start with wound (to be done)");
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button2);

        return buttons;
    }

}
