import java.util.ArrayList;

public abstract class MonsterType {
    public int standeeLimit;
    public int[] HEALTH;
    public int[] MOVE;
    public int[] ATTACK;
    public int[] health;
    public int[] move;
    public int[] attack;
    public int[] SHIELD = new int[16];
    public int[] shield;
    public int[] RETALIATE = new int[16];
    public int[] retaliate;
    public ArrayList<String>[] ATTACKEFFECTS = new ArrayList[16];
    public ArrayList<String>[] attackEffects;
    public ArrayList<MonsterAbilityCard> abilityCardsDeck;
    public ArrayList<MonsterAbilityCard> abilityCardsDiscard;
    public ArrayList<Integer> availableStandeeNumbers;
    public Class<?> type;
    static final ArrayList<String>[] emptyModifiers = new ArrayList[] {new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()};

    public void createMonsterType(int s, int level, Class<?> type, ArrayList<String>[] modifiers) {
        for (int i = 0; i < 16; i++) {
            ATTACKEFFECTS[i] = new ArrayList<>();
        }
        standeeLimit = s;
        for (int i = 0; i < modifiers.length; i++) {
            for (String str : modifiers[i]) {
                int value = Integer.parseInt(str.substring(str.length() - 1));
                if (str.startsWith("Shield")) {
                    SHIELD[i] = value;
                } else if (str.startsWith("Retaliate")) {
                    RETALIATE[i] = value;
                } else if (str.startsWith("Pierce")) {
                    ATTACKEFFECTS[i] = modifiers[i];
                }
            }
        }
        health = new int[]{HEALTH[2 * level], HEALTH[2 * level + 1]};
        move = new int[]{MOVE[2 * level], MOVE[2 * level + 1]};
        attack = new int[]{ATTACK[2 * level], ATTACK[2 * level + 1]};
        shield = new int[]{SHIELD[2 * level], SHIELD[2 * level + 1]};
        retaliate = new int[]{RETALIATE[2 * level], RETALIATE[2 * level + 1]};
        attackEffects = new ArrayList[]{ATTACKEFFECTS[2 * level], ATTACKEFFECTS[2 * level + 1]};
        abilityCardsDeck = new ArrayList<>();
        abilityCardsDiscard = new ArrayList<>();
        availableStandeeNumbers = new ArrayList<>();
        for (int i = 1; i <= standeeLimit; i++) {
            availableStandeeNumbers.add(i);
        }
        this.type = type;
    }

    public MonsterStandee newStandee(ScenarioGrid grid, boolean e, Scenario scenario)
    {
        int rand = (int) (Math.random()*availableStandeeNumbers.size());
        int standeeNumber = availableStandeeNumbers.remove(rand);
        MonsterStandee ms = Methods.createInstanceType(type);
        ms.createMonsterStandee(this, e, grid, standeeNumber, scenario);
        return ms;
    }

    @Override
    public abstract String toString();
}
