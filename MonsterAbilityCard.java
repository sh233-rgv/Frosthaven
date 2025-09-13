public abstract class MonsterAbilityCard {
    public String imagePath;
    public int initiative;
    public boolean shuffle;
    public ScenarioGrid grid;

    public MonsterAbilityCard(String ip, int i, boolean s) {
        imagePath = ip;
        initiative = i;
        shuffle = s;
    }

    @Override
    public abstract String toString();
}
