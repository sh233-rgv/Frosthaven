import javax.swing.*;
import java.util.ArrayList;

public class WO16 extends Event {
    public WO16(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO16.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO16.png", camp);
    }

    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Orchid");
        button1.setBounds(30, 449, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Valrath");
        button2.setBounds(30, 478, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        JButton button3 = new JButton("Savvas");
        button3.setBounds(100, 449, 50, 20);
        button3.setFont(Methods.DEFAULTFONT);

        JButton button4 = new JButton("Human");
        button4.setBounds(100, 478, 50, 20);
        button4.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
                failGuess(layeredPane, dialog));
        button3.addActionListener(_ ->
                failGuess(layeredPane, dialog));
        button4.addActionListener(_ ->
                failGuess(layeredPane, dialog));

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.setBounds(156, 220, 55, 20);
            buttonc.addActionListener(_ ->
            {
                camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                dialog.dispose();
                camp.increaseMorale(2);
                camp.giveToEach(10, "Experience");
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);

        return buttons;
    }

    private void failGuess(JLayeredPane layeredPane, JDialog dialog) {
        buttonClicked(dialog, layeredPane);
        JButton buttonc = new JButton("Continue");
        buttonc.setBounds(98, 482, 55, 20);
        buttonc.setFont(Methods.DEFAULTFONT);
        buttonc.addActionListener(_ ->
        {
            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
            dialog.dispose();
            camp.increaseMorale(-1);
        });
        layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
    }
}