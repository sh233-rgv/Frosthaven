import java.util.ArrayList;

public abstract class OverlayTile {
    public String name;
    public ArrayList<Hex> hexes;

    public OverlayTile(String n) {
        name = n;
        hexes = new ArrayList<>();
    }

    public void destroyTile() {
        for (Hex hex : hexes) {
            hex.overlayTiles.remove(this);
        }
        hexes.clear();
    }
}