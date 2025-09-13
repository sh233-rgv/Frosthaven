import javax.swing.*;
import java.awt.*;

public class LabelWithNumber extends JPanel {
    public final JLabel imageLabel;
    public final JLabel healthLabel;
    public JLabel numberLabel;

    public LabelWithNumber(JLabel label, int initialHealthNumber, Integer number) {
        setLayout(null); // Use absolute positioning
        setOpaque(false); // Transparent panel background

        // Main image label
        imageLabel = label;
        imageLabel.setBounds(0, 0, label.getWidth(), label.getHeight());

        // Number label
        healthLabel = new JLabel(String.valueOf(initialHealthNumber));
        healthLabel.setForeground(Color.WHITE);
        healthLabel.setFont(new Font("Germania One", Font.BOLD, 14));
        int w = label.getPreferredSize().width;
        int h = label.getPreferredSize().height;

        label.setBounds(0, 0, w, h);

        healthLabel.setSize(healthLabel.getPreferredSize());
        int nx = (w - healthLabel.getWidth()) / 2;
        healthLabel.setLocation(nx, 73);

        healthLabel.setHorizontalAlignment(SwingConstants.CENTER);

        setSize(label.getWidth(), label.getHeight());

        if (number != null) {
            numberLabel = new JLabel(String.valueOf(number));
            numberLabel.setForeground(Color.BLACK);
            numberLabel.setFont(new Font("Germania One", Font.BOLD, 14));

            numberLabel.setSize(numberLabel.getPreferredSize());
            numberLabel.setLocation((w - numberLabel.getWidth()) / 2, 4);

            numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(numberLabel);
        }

        add(healthLabel);
        add(imageLabel);
    }

    public void setHealthNumber(int number) {
        healthLabel.setText(String.valueOf(number));
        healthLabel.setSize(healthLabel.getPreferredSize());
        int nx = (imageLabel.getPreferredSize().width - healthLabel.getWidth()) / 2;
        healthLabel.setLocation(nx, 73);
        repaint();
    }
}