public class Hide23 extends LootCard {
    public Hide23(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Hide23.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        c.hide += 1 + enhancements;
        if (camp.activeCharacters.size() < 4) {
            c.hide++;
        }
    }

    @Override
    public String toString() {
        return (2 + enhancements) + "3 Hide";
    }
}
