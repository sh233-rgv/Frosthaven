import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Event {
    public String frontImage;
    public String backImage;
    Campaign camp;

    public Event(String imagePath, String imagePath2, Campaign camp) {
        this.frontImage = imagePath;
        this.backImage = imagePath2;
        this.camp = camp;
    }

    public void doEvent() {
        JDialog dialog = new JDialog((Frame) null, "Event", true);
        dialog.setLayout(null);
        dialog.setSize(376, 549);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 375, 525);

        JLabel label1 = createImage(frontImage, 375, 525);
        label1.setBounds(0, 0, 375, 525);

        ArrayList<JButton> buttons = getButtons(layeredPane, dialog);

        for (JButton button : buttons) {
            layeredPane.add(button, JLayeredPane.PALETTE_LAYER);
        }

        layeredPane.add(label1, JLayeredPane.DEFAULT_LAYER);

        dialog.setContentPane(layeredPane);

        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();
    }

    public JLabel createImage(String s, int width, int height) {
        ImageIcon originalIcon1 = new ImageIcon(s);

        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(scaledImage1);

        JLabel label1 = new JLabel(resizedIcon1);
        label1.setBounds(0, 0, width, height);

        return label1;
    }

    public abstract ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog);

    public ArrayList<JButton> basicButtons(JLayeredPane layeredPane, JDialog dialog, int optionAPos, int optionBPos, int ax, int ay, int bx, int by) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Option A");
        button1.setBounds(30, optionAPos, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        JButton button2 = new JButton("Option B");
        button2.setBounds(30, optionBPos, 50, 20);
        button2.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton button = new JButton("Continue");
            button.setBounds(ax, ay, 55, 20);
            button.setFont(Methods.DEFAULTFONT);
            button.addActionListener(_ ->
                    new Thread(() ->
                    {
                        dialog.dispose();
                        optionA();
                    }).start());
            layeredPane.add(button, JLayeredPane.PALETTE_LAYER);
        });

        button2.addActionListener(_ ->
        {
            buttonClicked(dialog, layeredPane);
            JButton button = new JButton("Continue");
            button.setFont(Methods.DEFAULTFONT);
            button.setBounds(bx, by, 55, 20);
            button.addActionListener(_ -> new Thread(() ->
            {
                dialog.dispose();
                optionB();
            }).start());
            layeredPane.add(button, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);
        buttons.add(button2);

        return buttons;
    }

    public void optionA() {

    }

    public void optionB() {

    }

    public void buttonClicked(JDialog dialog, JLayeredPane layeredPane) {
        dialog.getContentPane().removeAll();
        dialog.revalidate();
        dialog.repaint();
        JLabel label1 = createImage(backImage, 375, 525);
        label1.setBounds(0, 0, 375, 525);
        layeredPane.add(label1, JLayeredPane.DEFAULT_LAYER);
    }

    @Override
    public String toString() {
        return frontImage.substring(59, frontImage.length()-4);
    }
}

