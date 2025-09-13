import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Methods {

    static final Font DEFAULTFONT = new Font("Markazi Text", Font.BOLD, 13);

    public static JDialog scrollableDialog(int width, int height) {
        JDialog dialog = new JDialog((Frame) null, "Scrollable Dialog", false);
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JComponent content = (JComponent) dialog.getContentPane();
        content.setLayout(new BorderLayout());
        content.add(scrollPane, BorderLayout.CENTER);

        return dialog;
    }

    public static JLabel createImage(String s, double percentage) {
        ImageIcon originalIcon1 = new ImageIcon(s);
        return createImage(originalIcon1, percentage);
    }

    public static JLabel createImage(ImageIcon originalIcon1, double percentage) {
        int width = (int) (originalIcon1.getIconWidth() * percentage / 100);
        int height = (int) (originalIcon1.getIconHeight() * percentage / 100);

        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(scaledImage1);

        JLabel label1 = new JLabel(resizedIcon1);
        label1.setBounds(0, 0, width, height);

        return label1;
    }

    public static ImageIcon overlayImages(ArrayList<ImageIcon> icons) {
        int width = icons.getFirst().getIconWidth();
        int height = icons.getFirst().getIconHeight();

        BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = combined.createGraphics();

        for (ImageIcon icon : icons)
        {
            g.drawImage(icon.getImage(), 0, 0, null);
        }
        g.dispose();

        return new ImageIcon(combined);
    }

    public static JLabel createImageMonster(String s, double percentage, boolean e) {
        ImageIcon originalIcon1 = new ImageIcon(s);
        return createImageMonster(originalIcon1, percentage, e);
    }

    public static JLabel createImageMonster(ImageIcon originalIcon1, double percentage, boolean e) {
        int width = (int) (originalIcon1.getIconWidth() * percentage / 100);
        int height = (int) (originalIcon1.getIconHeight() * percentage / 100);

        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = overlayImages(new ArrayList<>(Arrays.asList(new ImageIcon(scaledImage1),
                new ImageIcon((new ImageIcon("/Users/saahilherrero/Downloads/Images/" + ((e) ? "Yellow" : "White") + " Outline.png")).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)),
                new ImageIcon((new ImageIcon("/Users/saahilherrero/Downloads/Images/Health.png")).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)))));
        JLabel label1 = new JLabel(resizedIcon1);
        label1.setBounds(0, 0, width, height);

        return label1;
    }

    public static JLabel createImageCharacterSummon(String s, double percentage, CharacterStandee characterStandee) {
        ImageIcon originalIcon1 = new ImageIcon(s);
        return createImageCharacterSummon(originalIcon1, percentage, characterStandee);
    }

    public static JLabel createImageCharacterSummon(ImageIcon originalIcon1, double percentage, CharacterStandee characterStandee) {
        int width = (int) (originalIcon1.getIconWidth() * percentage / 100);
        int height = (int) (originalIcon1.getIconHeight() * percentage / 100);

        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = overlayImages(new ArrayList<>(Arrays.asList(new ImageIcon(scaledImage1),
                new ImageIcon((new ImageIcon("/Users/saahilherrero/Downloads/Images/Character Summons/" + characterStandee.c.toString() + " Outline.png")).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)),
                new ImageIcon((new ImageIcon("/Users/saahilherrero/Downloads/Images/Health.png")).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)))));
        JLabel label1 = new JLabel(resizedIcon1);
        label1.setBounds(0, 0, width, height);

        return label1;
    }

    public static JLabel createImageCharacter(String s, double percentage) {
        ImageIcon originalIcon1 = new ImageIcon(s);
        return createImageCharacter(originalIcon1, percentage);
    }

    public static JLabel createImageCharacter(ImageIcon originalIcon1, double percentage) {
        int width = (int) (originalIcon1.getIconWidth() * percentage / 100);
        int height = (int) (originalIcon1.getIconHeight() * percentage / 100);

        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = overlayImages(new ArrayList<>(Arrays.asList(new ImageIcon(scaledImage1),
                new ImageIcon((new ImageIcon("/Users/saahilherrero/Downloads/Images/Health.png")).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)))));
        JLabel label1 = new JLabel(resizedIcon1);
        label1.setBounds(0, 0, width, height);

        return label1;
    }

    public static JLabel createImage(String path, double scale, float alpha) {
        BufferedImage original;
        try {
            original = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int width = (int) (original.getWidth() * scale / 100);
        int height = (int) (original.getHeight() * scale / 100);

        // Properly scale image using BufferedImage
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = scaledImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        // Apply alpha
        BufferedImage transparentImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = transparentImage.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        JLabel label = new JLabel(new ImageIcon(transparentImage));
        label.setBounds(0, 0, width, height);
        return label;
    }

    public static JLabel showHex(Hex hex) {
        JLabel label = Methods.createImage("/Users/saahilherrero/Downloads/Images/Hex.png", 21.0, 0.7f);
        label.setLocation((int) hex.getPosition().getX() - label.getWidth() / 2, (int) hex.getPosition().getY() - label.getHeight() / 2);
        return label;
    }

    public static JLabel showValidHexLOS(Hex hex) {
        JLabel label = Methods.createImage("/Users/saahilherrero/Downloads/Images/LOS Blue Hex.png", 100);
        label.setLocation((int) hex.getPositionLOS().getX() - label.getWidth() / 2, (int) hex.getPositionLOS().getY() - label.getHeight() / 2);
        return label;
    }

    public static JLabel showUnvalidHexLOS(Hex hex) {
        JLabel label = Methods.createImage("/Users/saahilherrero/Downloads/Images/LOS Red Hex.png", 100);
        label.setLocation((int) hex.getPositionLOS().getX() - label.getWidth() / 2, (int) hex.getPositionLOS().getY() - label.getHeight() / 2);
        return label;
    }

    public static JLabel showStartHex(Hex hex) {
        JLabel label = Methods.createImage("/Users/saahilherrero/Downloads/Images/Start Hex.png", 50.0);
        label.setLocation((int) hex.getPosition().getX() - label.getWidth() / 2, (int) hex.getPosition().getY() - label.getHeight() / 2);
        return label;
    }

    public static BufferedImage rotateImage(BufferedImage inputImage, double angleDegrees) {
        double rotationAngle = Math.toRadians(angleDegrees);

        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        int newWidth = (int) Math.abs(width * Math.cos(rotationAngle)) + (int) Math.abs(height * Math.sin(rotationAngle));
        int newHeight = (int) Math.abs(height * Math.cos(rotationAngle)) + (int) Math.abs(width * Math.sin(rotationAngle));

        BufferedImage outputImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        AffineTransform transform = new AffineTransform();

        transform.translate((double) newWidth / 2, (double) newHeight / 2);
        transform.rotate(rotationAngle);
        transform.translate((double) -width / 2, (double) -height / 2);

        Graphics2D g2d = outputImage.createGraphics();

        g2d.setTransform(transform);
        g2d.drawImage(inputImage, 0, 0, null);
        g2d.dispose();

        return outputImage;
    }

    public static int getRange(Hex start, Hex end) {
        if (start == null || end == null || !start.validHex || !end.validHex) {
            return -1;
        }

        Set<Hex> visited = new HashSet<>();
        Queue<Hex> queue = new LinkedList<>();
        Map<Hex, Integer> distance = new HashMap<>();

        queue.add(start);
        visited.add(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            Hex current = queue.poll();
            int currDist = distance.get(current);

            if (current == end) {
                return currDist;
            }

            for (Hex neighbor : current.adjacentHexes) {
                if (neighbor != null && neighbor.validHex && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    distance.put(neighbor, currDist + 1);
                    queue.add(neighbor);
                }
            }
        }

        return -1;
    }

    public static int getDistance(Hex start, Hex end) {
        if (start == null || end == null || !start.validHex || !end.validHex) {
            return -1;
        }

        Set<Hex> visited = new HashSet<>();
        Queue<Hex> queue = new LinkedList<>();
        Map<Hex, Integer> distance = new HashMap<>();

        queue.add(start);
        visited.add(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            Hex current = queue.poll();
            int currDist = distance.get(current);

            if (current == end) {
                return currDist;
            }

            for (Hex neighbor : current.surroundingHexes) {
                if (neighbor != null && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    distance.put(neighbor, currDist + 1);
                    queue.add(neighbor);
                }
            }
        }

        return -1;
    }

    public static void animateLabelTo(JDialog dialog, LabelWithNumber label, Point target, int durationMs, Runnable onComplete) {
        Point start = label.getLocation();
        int frames = 60; // smoother animation
        int delay = durationMs / frames;

        int dx = (target.x - start.x);
        int dy = (target.y - start.y);

        Timer timer = new Timer(delay, null);
        final int[] step = {0};

        timer.addActionListener(e -> {
            step[0]++;
            float progress = step[0] / (float) frames;

            int newX = start.x + Math.round(dx * progress);
            int newY = start.y + Math.round(dy * progress);
            label.setLocation(newX, newY);

            dialog.repaint();

            if (step[0] >= frames) {
                label.setLocation(target); // final correction
                ((Timer) e.getSource()).stop();
                if (onComplete != null) onComplete.run();
            }
        });
        timer.start();
    }

    public static void animateLabelTo(JDialog dialog, JLabel label, Point target, int durationMs, Runnable onComplete) {
        Point start = label.getLocation();
        int frames = 60; // smoother animation
        int delay = durationMs / frames;

        int dx = (target.x - start.x);
        int dy = (target.y - start.y);

        Timer timer = new Timer(delay, null);
        final int[] step = {0};

        timer.addActionListener(e -> {
            step[0]++;
            float progress = step[0] / (float) frames;

            int newX = start.x + Math.round(dx * progress);
            int newY = start.y + Math.round(dy * progress);
            label.setLocation(newX, newY);

            dialog.repaint();

            if (step[0] >= frames) {
                label.setLocation(target); // final correction
                ((Timer) e.getSource()).stop();
                if (onComplete != null) onComplete.run();
            }
        });
        timer.start();
    }

    static void enqueue(Queue<Runnable> moveQueue, Runnable moveTask) {
        moveQueue.add(moveTask);
        if (moveQueue.size() == 1) {
            moveTask.run();
        }
    }

    static void finishQueue(Queue<Runnable> moveQueue) {
        moveQueue.poll();
        Runnable next = moveQueue.peek();
        if (next != null) next.run();
    }

    static double angleBetweenPoints(double x1, double y1, double x2, double y2) {
        double angleRad = Math.atan2(y1 - y2, x2 - x1); // in radians
        double angleDeg = Math.toDegrees(angleRad);     // convert to degrees
        if (angleDeg < 0) angleDeg += 360;              // make it 0–360
        return angleDeg;
    }


    static <T> T createInstanceType(Class<?> type) {
        try {
            return (T) type.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
