import java.util.ArrayList;

public class Reinforcement extends CharacterSummon {

    @Override
    public void createSummonStandee(CharacterStandee cs, int number) {
        createSummonStandee(cs, 1, 2, null, null, 0, 0, number, new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Reinforcement";
    }

    /*
    @Override
    public void move() {
        control movement not auto
    }
     */

}
