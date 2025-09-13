
public class BuildingCost {
    public int lumberCost;
    public int metalCost;
    public int hideCost;
    public int prosperityRec;

    public BuildingCost(int lumber, int metal, int hide, int prosp) {
        lumberCost = lumber;
        metalCost = metal;
        hideCost = hide;
        prosperityRec = prosp;
    }

    @Override
    public String toString() {
        return "Lumber: " + lumberCost + "\n Metal: " + metalCost + "\n Hide: " + hideCost + "Prosperity Requirement: " + prosperityRec;
    }

}
