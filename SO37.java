import javax.swing.*;
import java.util.ArrayList;

public class SO37 extends Event {
    public SO37(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO37.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO37.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 222, 55, 45, 383);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        System.out.println("Gain one random side scenario (to be done)");
        if (camp.findEventActive("Summer Outpost", 38) != -1) {
            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.remove(camp.findEventActive("Summer Outpost", 38)));
        }
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        System.out.println("Gain one random side scenario (to be done)");
        camp.distributeThings(2, "Hide");
        if (camp.findEventActive("Summer Outpost", 38) != -1) {
            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.remove(camp.findEventActive("Summer Outpost", 38)));
        }
    }

}
