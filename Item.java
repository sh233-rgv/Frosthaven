public abstract class Item {
    public int addedNeg1s;
    public String useEffect;
    public int goldCost;
    public int lumberCost;
    public int metalCost;
    public int hideCost;
    public int arrowvineCost;
    public int axenutCost;
    public int corpsecapCost;
    public int flamefruitCost;
    public int rockrootCost;
    public int snowthistleCost;
    public Item[] itemCost;
    public String slot;

    public Item(int addedNeg1s, String useEffect, int goldCost, int lumberCost, int metalCost, int hideCost,
                int arrowvineCost, int axenutCost, int corpsecapCost, int flamefruitCost,
                int rockrootCost, int snowthistleCost, Item[] itemCost, String slot) {
        this.addedNeg1s = addedNeg1s;
        this.useEffect = useEffect;
        this.goldCost = goldCost;
        this.lumberCost = lumberCost;
        this.metalCost = metalCost;
        this.hideCost = hideCost;
        this.arrowvineCost = arrowvineCost;
        this.axenutCost = axenutCost;
        this.corpsecapCost = corpsecapCost;
        this.flamefruitCost = flamefruitCost;
        this.rockrootCost = rockrootCost;
        this.snowthistleCost = snowthistleCost;
        this.itemCost = itemCost;
        this.slot = slot;
    }

    public int sellCost() {
        return goldCost / 2 + 2 * (lumberCost + metalCost + hideCost + arrowvineCost + axenutCost
                + corpsecapCost + flamefruitCost + rockrootCost + snowthistleCost + itemCost.length);
    }

    @Override
    public abstract String toString();


}
