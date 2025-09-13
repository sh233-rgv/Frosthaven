public class Metal22 extends LootCard {
    public Metal22(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Metal22.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        c.metal += 1 + enhancements;
        if (camp.activeCharacters.size() < 3) {
            c.metal++;
        }
    }

    @Override
    public String toString() {
        return (2 + enhancements) + "2 Metal";
    }
}
