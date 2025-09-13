import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class LootCard {
    public int enhancements;
    public String image;
    public Campaign camp;

    public LootCard(String image, Campaign camp) {
        enhancements = 0;
        this.image = image;
        this.camp = camp;
    }

    public abstract void drawEffect(Character c);

    public JLabel getImage() {
        JLabel label = Methods.createImage(image, 50);
        if (enhancements <= 0) {
            return label;
        }

        BufferedImage baseImage = new BufferedImage(265, 407, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gBase = baseImage.createGraphics();
        gBase.drawImage(((ImageIcon) label.getIcon()).getImage(), 0, 0, null);
        gBase.dispose();

        BufferedImage combined = new BufferedImage(265, 407, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = combined.createGraphics();
        g.drawImage(baseImage, 0, 0, null);

        BufferedImage frontBuffer;
        try {
            frontBuffer = ImageIO.read(new File("/Users/saahilherrero/Downloads/Images/Enhancements/+1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image frontImage = frontBuffer.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        for (int i = 0; i < enhancements; i++) {
            g.drawImage(frontImage, i * 20, 0, null);
        }

        g.dispose();

        label = new JLabel(new ImageIcon(combined));
        label.setBounds(0, 0, 265, 407);
        return label;
    }

    @Override
    public abstract String toString();

}
