import javax.swing.*;
import java.util.ArrayList;

public class SO47 extends Event {
    public SO47(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO47.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO47.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Continue");
        button1.setBounds(60, 439, 55, 20);
        button1.setFont(Methods.DEFAULTFONT);
        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(168, 482, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
                    new Thread(() ->
                    {
                        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                        dialog.dispose();
                        int attackVal = 50;
                        int attacksWithA = 0;
                        int reduceOneAttack = 0;
                        if (camp.traitInParty("Arcane")) {
                            attackVal -= 10;
                        }
                        if (camp.traitInParty("Armored")) {
                            camp.giveToEach(5, "Experience");
                        }
                        if (camp.traitInParty("Intimidating")) {
                            camp.constructionModifiers.add("Reduce one rebuild cost by 2");
                        }
                        if (camp.traitInParty("Nimble")) {
                            camp.increaseMorale(1);
                        }
                        if (camp.traitInParty("Resourceful")) {
                            reduceOneAttack = 20;
                        }
                        if (camp.traitInParty("Strong")) {
                            attacksWithA = 22;
                        }
                        camp.outpostAttack(attackVal, 22, camp.chooseOutpostAttackTargeting(camp.BUILDINGSNUMS, 22), attacksWithA, 0, true, reduceOneAttack);
                        camp.distributeThings(5, "Lumber");
                    }).start());
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });

        buttons.add(button1);

        return buttons;
    }

}
