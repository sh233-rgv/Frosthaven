public class Coin2 extends LootCard {
    public Coin2(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/2Coin.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        c.gold += (2 + enhancements) * camp.goldConversion[camp.scenarioLevel];
    }

    @Override
    public String toString() {
        return (2 + enhancements) + " Coin";
    }
}
