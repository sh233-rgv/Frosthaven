import java.util.ArrayList;

public class AreaOfEffect {
    ArrayList<Hex> redHexes;
    Hex greyHex;
    ArrayList<Hex> blueHexes;
    ScenarioGrid grid;

    AreaOfEffect(ArrayList<Hex> rh, Hex gh, ArrayList<Hex> bh, ScenarioGrid grid) {
        redHexes = rh;
        greyHex = gh;
        blueHexes = bh;
        this.grid = grid;
    }

    ArrayList<Figure> getFigures() {
        ArrayList<Figure> figures = new ArrayList<>();
        for (Hex hex : redHexes) {
            if (hex.figure != null) {
                figures.add(hex.figure);
            }
        }
        return figures;
    }

    public void rotateArea() {
        Hex keyHex = getKeyHex();
        rotateArray(redHexes, keyHex);
        rotateArray(blueHexes, keyHex);
    }

    private void rotateArray(ArrayList<Hex> hexArrayList, Hex keyHex) {
        int keyQ = keyHex.col - ((keyHex.row - (keyHex.row & 1)) / 2);
        int keyR = keyHex.row;
        int keyS = -keyQ - keyR;
        for (int i = 0; i < hexArrayList.size(); i++) {
            Hex rotateHex = hexArrayList.get(i);
            int q = rotateHex.col - ((rotateHex.row - (rotateHex.row & 1)) / 2);
            int r = rotateHex.row;
            int s = -q - r - keyS;
            q -= keyQ;
            r -= keyR;

            int newQ = -r + keyQ;
            int newR = -s + keyR;
            int newS = -q + keyS;

            int col = newQ + ((newR - (newR & 1)) / 2);
            int row = newR;

            if (grid.inGrid(row, col)) {
                hexArrayList.set(i, grid.hexes.get(row).get(col));
            } else {
                hexArrayList.set(i, new Hex(row, col)); // fallback if outside grid
            }
        }
    }

    public void flipArea() {
        Hex keyHex = getKeyHex();
        flipArray(redHexes, keyHex);
        flipArray(blueHexes, keyHex);
    }

    private void flipArray(ArrayList<Hex> hexArrayList, Hex keyHex) {
        for (int i = 0; i < hexArrayList.size(); i++) {
            Hex flipHex = hexArrayList.get(i);
            int newR = flipHex.row - 2 * (flipHex.row - keyHex.row);
            if (grid.inGrid(newR, flipHex.col)) {
                hexArrayList.set(i, grid.hexes.get(newR).get(flipHex.col));
            } else {
                hexArrayList.set(i, new Hex(newR, flipHex.col));
            }
        }
    }

    public Hex getKeyHex() {
        return (greyHex == null) ? redHexes.getFirst() : greyHex;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (greyHex != null) {
            s.append(greyHex).append("\n");
        }
        for (Hex redHex : redHexes) {
            s.append(redHex).append("\n");
        }
        for (Hex blueHex : blueHexes) {
            s.append(blueHex).append("\n");
        }
        if (!s.isEmpty()) {
            s.delete(s.length() - 1, s.length());
        }
        return s.toString();
    }

}
