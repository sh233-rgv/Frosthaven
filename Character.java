import java.util.ArrayList;

public abstract class Character {
    public static final int[] LOWHEALTH = {6, 7, 8, 9, 10, 11, 12, 13, 14};
    public static final int[] MIDHEALTH = {8, 9, 11, 12, 14, 15, 17, 18, 20};
    public static final int[] HIGHHEALTH = {10, 12, 14, 16, 18, 20, 22, 24, 26};
    public int handSize;
    public ArrayList<CharacterAbilityCard> availableCards;
    public ArrayList<CharacterAbilityCard> unavailableCards;
    public int xp;
    public final int[] LEVELXP = {0, 45, 95, 150, 210, 275, 345, 420, 500};
    public boolean[] perks;
    public int checkmarks;
    public boolean[] masteries;
    public int level;
    public int gold;
    public int lumber;
    public int metal;
    public int hide;
    public int arrowvine;
    public int axenut;
    public int corpsecap;
    public int flamefruit;
    public int rockroot;
    public int snowthistle;
    public String trait1;
    public String trait2;
    public String trait3;
    public ArrayList<Item> items;
    public String ancestry;
    public Campaign camp;
    public int[] healthProgression;

    public Character(Campaign camp, int[] healthProgression, int handSize, int xp, String trait1,
                     String trait2, String trait3, String ancestry, ArrayList<CharacterAbilityCard> cards) {
        this.healthProgression = healthProgression;
        this.handSize = handSize;
        this.xp = xp;
        this.trait1 = trait1;
        this.trait2 = trait2;
        this.trait3 = trait3;
        gold = 0;
        lumber = 0;
        metal = 0;
        hide = 0;
        arrowvine = 0;
        axenut = 0;
        corpsecap = 0;
        flamefruit = 0;
        rockroot = 0;
        snowthistle = 0;
        level = 1;
        items = new ArrayList<>();
        this.ancestry = ancestry;
        perks = new boolean[18];
        masteries = new boolean[2];
        checkmarks = 0;
        this.camp = camp;
        availableCards = new ArrayList<>();
        unavailableCards = new ArrayList<>();
        for (CharacterAbilityCard cac : cards) {
            if (cac.level == 1) {
                availableCards.add(cac);
            } else {
                unavailableCards.add(cac);
            }
        }
        for (int i = 0; i < 26; i++)
        {
            availableCards.add(new AtAllCosts(this));
        }
    }

    @Override
    public abstract String toString();

    public abstract CharacterStandee getStandee(Scenario scenario);

    public void increaseExperience(int increase) {
        xp = Math.max(xp + increase, LEVELXP[level - 1]);
    }

    public int countMasteries() {
        int count = 0;
        for (boolean mastery : masteries) {
            if (mastery) {
                count++;
            }
        }
        return count;
    }

    public void increaseCheckmarks(int increase) {
        if (increase < 0 && checkmarks % 3 < -increase) {
            checkmarks = checkmarks - checkmarks % 3;
        } else {
            checkmarks += increase;
            if (checkmarks > 18) {
                checkmarks = 18;
            }
        }
    }

    public void levelUp() {
        if (level < camp.prosperity / 2 + 1) {
            xp = LEVELXP[level];
        }
        // to be done: gain card
        //to be done: gain perk
        level++;
    }
}