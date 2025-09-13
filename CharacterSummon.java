import java.util.ArrayList;

public abstract class CharacterSummon extends Figure {
    CharacterStandee characterStandee;
    Integer move;
    Integer attack;
    Integer range;
    ArrayList<String> attackEffects;
    int number;


    public abstract void createSummonStandee(CharacterStandee cs, int number);

    public void createSummonStandee(CharacterStandee cs, int h, Integer m, Integer a, Integer r, int s, int retal, int n, ArrayList<String> ae) {
        characterStandee = cs;
        maxHealth = h;
        health = maxHealth;
        move = m;
        attack = a;
        range = r;
        shieldVal = s;
        retaliateVal = retal;
        attackEffects = ae;
        image = "/Users/saahilherrero/Downloads/Images/Character Summons/" + this + ".png";
        team = 1;
        number = n;
        label = new LabelWithNumber(Methods.createImageCharacterSummon(image, 20, characterStandee), health, number);
        scenario = cs.scenario;
        attackModifierDeck = cs.attackModifierDeck;
        attackModifierDiscard = cs.attackModifierDiscard;
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

    @Override
    public abstract String toString();

}
