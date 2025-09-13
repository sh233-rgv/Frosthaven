import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class MapTile {
    public String image;
    public int row;
    public int col;
    public char letter;
    public Scenario scenario;
    public int rotation;
    public final Point[] LOCATIONS;
    public final Point baseLoc;
    public final double PERCENT;
    public ArrayList<Hex> hexes;

    public MapTile(int x, int y, char letter, Scenario scenario, int rotation, Point baseLoc, Point[] locations, double percent) {
        this.letter = letter;
        this.image = "/Users/saahilherrero/Downloads/Images/Map Tiles/" + this + ".png";
        row = x;
        col = y;
        this.scenario = scenario;
        this.rotation = rotation;
        this.baseLoc = baseLoc;
        LOCATIONS = locations;
        PERCENT = percent;
        hexes = new ArrayList<>();
    }

    public abstract void getHexes();

    public void validateHexes() {
        for (Hex hex : hexes) {
            hex.validHex = true;
            scenario.grid.validHexes.add(hex);
            scenario.LOSLayeredPane.add(Methods.showValidHexLOS(hex), Integer.valueOf(3));
            JLabel label = new JLabel(hex.row + ", " + hex.col);
            label.setBounds((int)hex.getPositionLOS().getX()-15, (int)hex.getPositionLOS().getY()-15, 30, 30);
            scenario.LOSLayeredPane.add(label, Integer.valueOf(4));
        }
    }

    public void addMapTile(JLayeredPane layeredPane) {
        File inputFile = new File(image);
        BufferedImage inputImage;
        try {
            inputImage = ImageIO.read(inputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assert inputImage != null;
        BufferedImage rotatedImage1 = Methods.rotateImage(inputImage, rotation);
        ImageIcon icon1 = new ImageIcon(rotatedImage1);

        JLabel label1 = Methods.createImage(icon1, PERCENT);
        label1.setLocation(
                (int) (scenario.grid.hexes.get(row).get(col).getPosition().getX() + LOCATIONS[rotation / 60].getX() + baseLoc.getX()),
                (int) (scenario.grid.hexes.get(row).get(col).getPosition().getY() + LOCATIONS[rotation / 60].getY() + baseLoc.getY()));
        layeredPane.add(label1, 0);
    }

    @Override
    public abstract String toString();
}