import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SO57 extends Event {
    public SO57(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO57.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO57.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 184, 104, 45, 498);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        JDialog dialog1 = new JDialog((Frame) null, "Select Building", true);
        dialog1.setLayout(null);
        dialog1.setSize(380, 550);
        dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog1.setLocationRelativeTo(null);
        String[] herb = {""};
        JButton arrowvineButton = new JButton("Arrowvine");
        arrowvineButton.setBounds(100, 30, 180, 30);
        dialog1.add(arrowvineButton);
        arrowvineButton.addActionListener(_ ->
        {
            herb[0] = "Arrowvine";
            dialog1.dispose();
        });
        JButton axenutButton = new JButton("Axenut");
        axenutButton.setBounds(100, 60, 180, 30);
        dialog1.add(axenutButton);
        axenutButton.addActionListener(_ ->
        {
            herb[0] = "Axenut";
            dialog1.dispose();
        });
        JButton corpsecapButton = new JButton("Corpsecap");
        corpsecapButton.setBounds(100, 90, 180, 30);
        dialog1.add(corpsecapButton);
        corpsecapButton.addActionListener(_ ->
        {
            herb[0] = "Corpsecap";
            dialog1.dispose();
        });
        JButton flamefruitButton = new JButton("Flamefruit");
        flamefruitButton.setBounds(100, 120, 180, 30);
        dialog1.add(flamefruitButton);
        flamefruitButton.addActionListener(_ ->
        {
            herb[0] = "Flamefruit";
            dialog1.dispose();
        });
        JButton rockrootButton = new JButton("Rockroot");
        rockrootButton.setBounds(100, 150, 180, 30);
        dialog1.add(rockrootButton);
        rockrootButton.addActionListener(_ ->
        {
            herb[0] = "Rockroot";
            dialog1.dispose();
        });
        JButton snowthistleButton = new JButton("Snowthistle");
        snowthistleButton.setBounds(100, 180, 180, 30);
        dialog1.add(snowthistleButton);
        snowthistleButton.addActionListener(_ ->
        {
            herb[0] = "Snowthistle";
            dialog1.dispose();
        });
        dialog1.setVisible(true);
        dialog1.repaint();
        dialog1.revalidate();
        int num = camp.loseAnyNumThings(herb[0]);
        camp.distributeHerbs(-num);
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.arrowvine++;
        camp.flamefruit++;
        camp.snowthistle++;
    }
}
