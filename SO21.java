import javax.swing.*;
import java.util.ArrayList;

/**
 * Write a description of class SO_1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SO21 extends Event {

    /**
     * Constructor for objects of class SO_1
     */
    public SO21(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO21.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO21.png", camp);
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
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            if (camp.traitInParty("Intimidating")) {
                buttonc.setFont(Methods.DEFAULTFONT);
                buttonc.setBounds(275, 132, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(2, "Metal");
                            camp.distributeThings(2, "Hide");
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
            if (camp.canPay("Gold", 20)) {
                buttonc.setBounds(136, 397, 55, 20);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(-20, "Gold");
                            camp.distributeThings(3, "Lumber");
                            camp.distributeThings(3, "Metal");
                            camp.distributeThings(3, "Hide");
                        }).start());
            } else {
                buttonc.setBounds(101, 483, 55, 20);
                buttonc.addActionListener(_ ->
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