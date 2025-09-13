public class Coin1 extends LootCard {
    public Coin1(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/1Coin.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        c.gold += (1 + enhancements) * camp.goldConversion[camp.scenarioLevel];
    }

    @Override
    public String toString() {
        return (1 + enhancements) + " Coin";
    }
}
