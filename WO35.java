import javax.swing.*;
import java.util.ArrayList;

public class WO35 extends Event {
    public WO35(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO35.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO35.png", camp);
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
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Nimble")) {
                buttonc.setBounds(83, 59, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(45, 6);
                        }).start());
            } else {
                buttonc.setBounds(78, 162, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(60, 6);
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.setBounds(78, 293, 55, 20);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                        dialog.dispose();
                        threat(55, 4);
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

    public void threat(int attackVal, int numTargets) {
        ArrayList<Integer> fullTargets = new ArrayList<>();
        for (int i = 9; i > 0; i--) {
            ArrayList<Integer> targets = new ArrayList<>();
            for (int j = 0; j < camp.unlockedBuildings.size(); j++) {
                if (camp.unlockedBuildings.get(j).level == i) {
                    targets.add(camp.unlockedBuildings.get(j).number);
                }
            }
            if (!targets.isEmpty()) {
                ArrayList<Integer> temp = camp.chooseOutpostAttackTargeting(targets, numTargets);
                for (int integer : temp) {
                    if (fullTargets.size() < numTargets) {
                        fullTargets.add(integer);
                    }
                }
                if (fullTargets.size() >= numTargets) {
                    i = 0;
                }
            }
        }
        camp.outpostAttack(attackVal, numTargets, fullTargets);
        camp.distributeThings(2, "Metal");
    }
}