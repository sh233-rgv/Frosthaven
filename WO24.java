import javax.swing.*;
import java.util.ArrayList;

public class WO24 extends Event {
    public WO24(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO24.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO24.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, 409, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, 468, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Nimble")) {
                buttonc.setBounds(78, 59, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(3, 0);
                        }).start());
            } else {
                buttonc.setBounds(79, 132, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(8, 0);
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Educated") || camp.ancestryInParty("Quatryl")) {
                buttonc.setBounds(210, 278, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            camp.increaseMorale(1);
                            threat(5, 5);
                        }).start());
            } else {
                buttonc.setBounds(126, 410, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            ArrayList<Building> wreckableBuildings = new ArrayList<>();
                            for (int i = 0; i < camp.unlockedBuildings.size(); i++) {
                                if (!camp.unlockedBuildings.get(i).wrecked && camp.unlockedBuildings.get(i).level != 0) {
                                    wreckableBuildings.add(camp.unlockedBuildings.get(i));
                                }
                            }
                            int rand = (int) (Math.random() * wreckableBuildings.size());
                            wreckableBuildings.get(rand).wrecked = true;
                            wreckableBuildings.remove(rand);
                            rand = (int) (Math.random() * wreckableBuildings.size());
                            wreckableBuildings.get(rand).wrecked = true;
                            threat(5, 0);
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

    public void threat(int targets, int attacksWithA) {
        camp.outpostAttack(20, targets, camp.randomBuildings(), attacksWithA, 0);
    }
}