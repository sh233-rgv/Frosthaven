public class Lumber23 extends LootCard {
    public Lumber23(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Lumber23.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        c.lumber += 1 + enhancements;
        if (camp.activeCharacters.size() < 4) {
            c.lumber++;
        }
    }

    @Override
    public String toString() {
        return (2 + enhancements) + "3 Lumber";
    }
}
