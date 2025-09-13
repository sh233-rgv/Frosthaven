import javax.swing.*;
import java.util.ArrayList;

public class SO38 extends Event {
    public SO38(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO38.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO38.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 176, 118, 45, 483);
    }

    @Override
    public void optionA() {
        camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.distributeThings(20, "Gold");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        System.out.println("Gain one random side scenario (to be done)");
        camp.distributeThings(20, "Gold");
        camp.distributeThings(2, "Lumber");
        camp.distributeThings(2, "Metal");
        camp.distributeThings(2, "Hide");
        camp.increaseSoldiers(1);
        if (camp.findEventActive("Summer Outpost", 37) != -1) {
            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.remove(camp.findEventActive("Summer Outpost", 37)));
        }
    }
}
