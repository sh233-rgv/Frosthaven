public class Lumber1 extends LootCard {
    public Lumber1(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Lumber1.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        c.lumber += 1 + enhancements;
    }

    @Override
    public String toString() {
        return (1 + enhancements) + "Lumber";
    }
}
