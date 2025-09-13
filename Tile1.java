import java.awt.*;

public class Tile1 extends MapTile {
    //keyhex: bottom left
    public Tile1(char letter, int row, int col, Scenario scenario, int rotation) {
        super(row, col, letter, scenario, rotation, new Point(-50, -175), new Point[]{new Point(0, 0),
                new Point(-50, 45), new Point(-250, 89), new Point(-400, 89), new Point(-325, -258), new Point(-75, -215)}, 25.93360996);
    }

    @Override
    public void getHexes() {
        int even = (row % 2 == 0) ? -1 : 0;
        int[][] offsets = switch (rotation) {
            case 0 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4},
                    {-1, even + 1}, {-1, even + 2}, {-1, even + 3}, {-1, even + 4}
            };
            case 60 -> new int[][]{
                    {0, 0}, {0, 1},
                    {1, even + 1}, {1, even + 2},
                    {2, 1}, {2, 2},
                    {3, even + 2}, {3, even + 3},
                    {4, 2}
            };
            case 120 -> new int[][]{
                    {0, 0},
                    {1, even}, {1, even + 1},
                    {2, -1}, {2, 0},
                    {3, even - 1}, {3, even},
                    {4, -2}, {4, -1}
            };
            case 180 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2}, {0, -3}, {0, -4},
                    {1, even}, {1, even - 1}, {1, even - 2}, {1, even - 3}
            };
            case 240 -> new int[][]{
                    {0, 0}, {0, -1},
                    {-1, even}, {-1, even - 1},
                    {-2, -1}, {-2, -2},
                    {-3, even - 1}, {-3, even - 2},
                    {-4, -2}
            };
            case 300 -> new int[][]{
                    {0, 0},
                    {-1, even}, {-1, even + 1},
                    {-2, 0}, {-2, 1},
                    {-3, even + 1}, {-3, even + 2},
                    {-4, 1}, {-4, 2}
            };
            default -> new int[0][0];
        };

        for (int[] offset : offsets) {
            hexes.add(scenario.grid.hexes.get(row + offset[0]).get(col + offset[1]));
        }
    }

    @Override
    public String toString() {
        return "01-" + letter;
    }
}