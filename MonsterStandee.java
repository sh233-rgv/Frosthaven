import java.util.ArrayList;

public abstract class MonsterStandee extends Figure {
    MonsterType monster;
    boolean elite;
    int move;
    int attack;
    ArrayList<String> attackEffects;
    int number;

    public void createMonsterStandee(MonsterType m, boolean e, ScenarioGrid gr, int n, Scenario scenario) {
        monster = m;
        elite = e;
        maxHealth = (e) ? m.health[1] : m.health[0];
        health = maxHealth;
        move = (e) ? m.move[1] : m.move[0];
        attack = (e) ? m.attack[1] : m.attack[0];
        shieldVal = (e) ? m.shield[1] : m.shield[0];
        retaliateVal = (e) ? m.retaliate[1] : m.retaliate[0];
        attackEffects = (e) ? m.attackEffects[1] : m.attackEffects[0];
        image = "/Users/saahilherrero/Downloads/Images/Monster Standees/" + m + ".png";
        team = 2;
        number = n;
        label = new LabelWithNumber(Methods.createImageMonster(image, 20, e), health, number);
        this.scenario = scenario;
        attackModifierDeck = scenario.monsterAMDDeck;
        attackModifierDiscard = scenario.monsterAMDDiscard;
    }

    public Figure findFocus() {
        int minRange = Integer.MAX_VALUE;
        ArrayList<Figure> figures = new ArrayList<>();
        for (int i = 0; i < scenario.grid.validHexes.size(); i++) {
            if (scenario.grid.validHexes.get(i).figure != null && scenario.grid.validHexes.get(i).figure.team != team) {
                int r = Methods.getRange(hex, scenario.grid.validHexes.get(i));
                if (r < minRange) {
                    minRange = r;
                    figures.clear();
                }
                if (r <= minRange) {
                    figures.add(scenario.grid.validHexes.get(i).figure);
                }
            }
        }
        if (figures.isEmpty()) {
            return null;
        }
        if (figures.size() == 1) {
            return figures.getFirst();
        }
        return null; // to be done
    }


}
