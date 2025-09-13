public class Coin3 extends LootCard {
    public Coin3(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/3Coin.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        c.gold += (3 + enhancements) * camp.goldConversion[camp.scenarioLevel];
    }

    @Override
    public String toString() {
        return (3 + enhancements) + " Coin";
    }
}
