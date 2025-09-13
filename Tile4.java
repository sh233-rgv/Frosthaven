import java.awt.*;

public class Tile4 extends MapTile {
    //keyhex: top left
    public Tile4(char letter, int row, int col, Scenario scenario, int rotation) {
        super(row, col, letter, scenario, rotation, new Point(-200, -87), new Point[]{new Point(0, 0),
                new Point(-275, -130), new Point(-375, -303), new Point(-202, -348), new Point(-75, -478), new Point(26, -304)}, 25.81755594);
    }

    @Override
    public void getHexes() {
        int even = (row % 2 == 0) ? -1 : 0;
        int[][] offsets = switch (rotation) {
            case 0 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2},
                    {1, even}, {1, even + 1}, {1, even + 2}, {1, even + 3},
                    {2, -1}, {2, 0}, {2, 1}, {2, 2}, {2, 3},
                    {3, even}, {3, even + 1}, {3, even + 2}, {3, even + 3},
                    {4, 0}, {4, 1}, {4, 2}
            };
            case 60 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2},
                    {1, even + 1}, {1, even}, {1, even - 1}, {1, even - 2},
                    {2, 1}, {2, 0}, {2, -1}, {2, -2}, {2, -3},
                    {3, even + 1}, {3, even}, {3, even - 1}, {3, even - 2},
                    {4, 0}, {4, -1}, {4, -2}
            };
            case 120 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2}, {0, -3}, {0, -4},
                    {-1, even}, {-1, even - 1}, {-1, even - 2}, {-1, even - 3},
                    {-2, -1}, {-2, -2}, {-2, -3},
                    {1, even}, {1, even - 1}, {1, even - 2}, {1, even - 3},
                    {2, -1}, {2, -2}, {2, -3}
            };
            case 180 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2},
                    {-1, even + 1}, {-1, even}, {-1, even - 1}, {-1, even - 2},
                    {-2, 1}, {-2, 0}, {-2, -1}, {-2, -2}, {-2, -3},
                    {-3, even + 1}, {-3, even}, {-3, even - 1}, {-3, even - 2},
                    {-4, 0}, {-4, -1}, {-4, -2}
            };
            case 240 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2},
                    {-1, even}, {-1, even + 1}, {-1, even + 2}, {-1, even + 3},
                    {-2, -1}, {-2, 0}, {-2, 1}, {-2, 2}, {-2, 3},
                    {-3, even}, {-3, even + 1}, {-3, even + 2}, {-3, even + 3},
                    {-4, 0}, {-4, 1}, {-4, 2}
            };
            case 300 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4},
                    {1, even + 1}, {1, even + 2}, {1, even + 3}, {1, even + 4},
                    {2, 1}, {2, 2}, {2, 3},
                    {-1, even + 1}, {-1, even + 2}, {-1, even + 3}, {-1, even + 4},
                    {-2, 1}, {-2, 2}, {-2, 3}
            };
            default -> new int[0][0];
        };

        for (int[] offset : offsets) {
            hexes.add(scenario.grid.hexes.get(row + offset[0]).get(col + offset[1]));
        }
    }

    @Override
    public String toString() {
        return "04-" + letter;
    }
}