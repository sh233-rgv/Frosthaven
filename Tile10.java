import java.awt.*;

public class Tile10 extends MapTile {
    //keyhex: top left
    public Tile10(char letter, int row, int col, Scenario scenario, int rotation) {
        super(row, col, letter, scenario, rotation, new Point(-50, -87), new Point[]{new Point(0, 0),
                new Point(-350, 0), new Point(-600, -174), new Point(-500, -347), new Point(-301, -608), new Point(-50, -434)}, 25.65198803);
    }

    @Override
    public void getHexes() {
        int even = (row % 2 == 0) ? -1 : 0;

        int[][] offsets = switch (rotation) {
            case 0 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5},
                    {1, even + 1}, {1, even + 2}, {1, even + 3}, {1, even + 4}, {1, even + 5},
                    {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5},
                    {3, even + 1}, {3, even + 2}, {3, even + 3}, {3, even + 4}, {3, even + 5},
                    {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {4, 5}
            };
            case 60 -> new int[][]{
                    {0, 0},
                    {1, even + 1}, {1, even}, {1, even - 1},
                    {2, 1}, {2, 0}, {2, -1}, {2, -2}, {2, -3},
                    {3, even + 2}, {3, even + 1}, {3, even}, {3, even - 1}, {3, even - 2},
                    {4, 2}, {4, 1}, {4, 0}, {4, -1}, {4, -2},
                    {5, even + 3}, {5, even + 2}, {5, even + 1}, {5, even}, {5, even - 1},
                    {6, 1}, {6, 0}, {6, -1},
                    {7, even}
            };
            case 120 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2}, {0, -3}, {0, -4},
                    {-1, even - 1}, {-1, even - 2}, {-1, even - 3},
                    {-2, -3},
                    {1, even}, {1, even - 1}, {1, even - 2}, {1, even - 3}, {1, even - 4},
                    {2, -1}, {2, -2}, {2, -3}, {2, -4}, {2, -5},
                    {3, even - 1}, {3, even - 2}, {3, even - 3}, {3, even - 4}, {3, even - 5},
                    {4, -2}, {4, -3}, {4, -4},
                    {5, even - 2}
            };
            case 180 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2}, {0, -3}, {0, -4}, {0, -5},
                    {-1, even}, {-1, even - 1}, {-1, even - 2}, {-1, even - 3}, {-1, even - 4},
                    {-2, 0}, {-2, -1}, {-2, -2}, {-2, -3}, {-2, -4}, {-2, -5},
                    {-3, even}, {-3, even - 1}, {-3, even - 2}, {-3, even - 3}, {-3, even - 4},
                    {-4, 0}, {-4, -1}, {-4, -2}, {-4, -3}, {-4, -4}, {-4, -5}
            };
            case 240 -> new int[][]{
                    {0, 0},
                    {-1, even}, {-1, even + 1}, {-1, even + 2},
                    {-2, -1}, {-2, 0}, {-2, 1}, {-2, 2}, {-2, 3},
                    {-3, even - 1}, {-3, even}, {-3, even + 1}, {-3, even + 2}, {-3, even + 3},
                    {-4, -2}, {-4, -1}, {-4, 0}, {-4, 1}, {-4, 2},
                    {-5, even - 2}, {-5, even - 1}, {-5, even}, {-5, even + 1}, {-5, even + 2},
                    {-6, -1}, {-6, 0}, {-6, 1},
                    {-7, even + 1}
            };
            case 300 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4},
                    {1, even + 2}, {1, even + 3}, {1, even + 4},
                    {2, 3},
                    {-1, even + 1}, {-1, even + 2}, {-1, even + 3}, {-1, even + 4}, {-1, even + 5},
                    {-2, 1}, {-2, 2}, {-2, 3}, {-2, 4}, {-2, 5},
                    {-3, even + 2}, {-3, even + 3}, {-3, even + 4}, {-3, even + 5}, {-3, even + 6},
                    {-4, 2}, {-4, 3}, {-4, 4},
                    {-5, even + 3}
            };
            default -> new int[0][0];
        };

        for (int[] offset : offsets) {
            hexes.add(scenario.grid.hexes.get(row + offset[0]).get(col + offset[1]));
        }
    }

    @Override
    public String toString() {
        return "10-" + letter;
    }
}
