import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO30 extends Event {
    public WO30(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO30.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO30.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 166, 133, 172, 410);
    }

    @Override
    public void optionA() {
        camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        effect("Gold");
    }

    @Override
    public void optionB() {
        camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        effect("Morale");
    }

    public void effect(String reward) {
        ArrayList<String> herbs = new ArrayList<>(Arrays.asList("Axenut", "Arrowvine", "Corpsecap", "Flamefruit", "Rockroot", "Snowthistle"));
        ArrayList<String> herbsNeeded = new ArrayList<>();
        herbsNeeded.add(herbs.remove((int) (Math.random() * herbs.size())));
        herbsNeeded.add(herbs.remove((int) (Math.random() * herbs.size())));
        herbsNeeded.add(herbs.remove((int) (Math.random() * herbs.size())));
        int num = camp.loseAnyNumThings(herbsNeeded.get(0));
        num += camp.loseAnyNumThings(herbsNeeded.get(1));
        num += camp.loseAnyNumThings(herbsNeeded.get(2));
        if (reward.equals("Gold")) {
            camp.distributeThings(-num * 5, "Gold");
        } else {
            camp.increaseMorale(-num);
        }
    }
}