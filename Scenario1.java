import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Scenario1 extends Scenario {
    public Scenario1(Campaign camp) {
        super(new ArrayList<>(Arrays.asList(new AlgoxGuard(camp.scenarioLevel), new AlgoxArcher(camp.scenarioLevel), new AlgoxPriest(camp.scenarioLevel), new CityGuard(camp.scenarioLevel))),
                new ArrayList<>(Arrays.asList(new Point(1, 3), new Point(1, 4), new Point(2, 3), new Point(2, 4), new Point(2, 5))), camp, new int[]{6, 5, 3, 3, 0, 0, 0, 0, 1, 2, 0}, 1);
        setMapTiles(new ArrayList<>(Arrays.asList(new Tile9('A', 7, 6, this, 180), new Tile13('A', 4, 8, this, 0), new Tile13('C', 4, 13, this, 0))));
        mapTiles.getFirst().validateHexes();
        monsterTypes.getLast().shield[0]++;
        monsterTypes.getLast().shield[1]++;
        monsterTypes.getLast().retaliate[0] += 2;
        monsterTypes.getLast().retaliate[1] += 2;
    }

    @Override
    public void setupRoom1() {
        addMonsterStandee(0, 3, 4, false);

    }

    @Override
    public void setupRoom2() {
        createWallLine(grid.hexes.get(6).get(7), grid.hexes.get(6).get(8));
    }

    @Override
    public String toString() {
        return "Scenario 1: A Town in Flames";
    }
}
