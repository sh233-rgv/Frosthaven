public class Metal23 extends LootCard {
    public Metal23(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Metal23.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        c.metal += 1 + enhancements;
        if (camp.activeCharacters.size() < 4) {
            c.metal++;
        }
    }

    @Override
    public String toString() {
        return (2 + enhancements) + "3 Metal";
    }
}
