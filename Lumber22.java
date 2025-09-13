public class Lumber22 extends LootCard {
    public Lumber22(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Lumber22.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        c.lumber += 1 + enhancements;
        if (camp.activeCharacters.size() < 3) {
            c.lumber++;
        }
    }

    @Override
    public String toString() {
        return (2 + enhancements) + "2 Lumber";
    }
}
