import javax.swing.*;
import java.util.ArrayList;

public class WO13 extends Event {
    public WO13(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO13.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO13.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 45, 235, 45, 485);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.distributeThings(20, "Gold");
        camp.distributeThings(3, "Metal");
        if (camp.findEventActive("Winter Outpost", 14) != -1) {
            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.remove(camp.findEventActive("Winter Outpost", 14)));
        }
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.distributeThings(30, "Gold");
        camp.distributeThings(3, "Metal");
        if (camp.findEventActive("Winter Outpost", 14) != -1) {
            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.remove(camp.findEventActive("Winter Outpost", 14)));
        }
    }
}