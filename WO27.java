import javax.swing.*;
import java.util.ArrayList;

public class WO27 extends Event {
    public WO27(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO27.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO27.png", camp);
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

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Arcane") || camp.ancestryInParty("Savvas")) {
                buttonc.setBounds(165, 89, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            camp.unlockedBuildings.get(camp.findBuildingInUnlocked(84)).wrecked = true;
                            threat(35, 2);
                        }).start());
            } else {
                buttonc.setBounds(101, 162, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(35, 5);
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            if (camp.traitInParty("Armored")) {
                buttonc.setBounds(126, 264, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            camp.increaseMorale(1);
                            threat(25, 5);
                        }).start());
            } else {
                buttonc.setBounds(175, 293, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            threat(35, 5);
                        }).start());
            }
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

    public void threat(int attackVal, int targets) {
        int num = camp.outpostAttack(attackVal, targets, camp.getClosestTargeting(84));
        camp.distributeThings(num, "Metal");
    }
}