public class Hide22 extends LootCard {
    public Hide22(Campaign camp) {
        super( "/Users/saahilherrero/Downloads/Images/Loot Cards/Hide22.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        c.hide += 1 + enhancements;
        if (camp.activeCharacters.size() < 3) {
            c.hide++;
        }
    }

    @Override
    public String toString() {
        return (2 + enhancements) + "2 Hide";
    }
}
