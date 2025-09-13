
public class LuckyDice extends Item {
    public LuckyDice() {
        super(0, "Loss", 30,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, new Item[0], "Small");
    }

    @Override
    public String toString() {
        return "Lucky Dice";
    }
}
