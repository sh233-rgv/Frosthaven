import javax.swing.*;
import java.util.ArrayList;

public class WO20 extends Event {
    public WO20(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO20.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO20.png", camp);
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
            if (camp.canPay("Gold", 10)) {
                buttonc.setBounds(170, 176, 55, 20);
                buttonc.setFont(Methods.DEFAULTFONT);
                buttonc.addActionListener(_ ->
                        new Thread(() ->
                        {
                            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                            dialog.dispose();
                            camp.distributeThings(-10, "Gold");
                            if (camp.unlockedBuildings.get(camp.findBuildingInUnlocked(98)).level != 4) {
                                camp.unlockedBuildings.get(camp.findBuildingInUnlocked(98)).increaseLevel();
                            } else {
                                camp.increaseMorale(1);
                            }
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
            buttonc.setBounds(102, 337, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
                dialog.dispose();
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }
}