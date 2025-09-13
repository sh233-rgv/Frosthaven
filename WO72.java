import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WO72 extends Event {
    public WO72(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO72.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO72.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 78, 74, 293, 205);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(35, new ArrayList<>());
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        ArrayList<Integer> untargetable = new ArrayList<>();
        JDialog dialog = new JDialog((Frame) null, "Choose untargetable buildings", true);
        dialog.setLayout(null);
        dialog.setSize(380, 600);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        int index = 0;
        for (int i = 0; i < camp.unlockedBuildings.size(); i++) {
            if (camp.unlockedBuildings.get(i).level != 0 && !camp.unlockedBuildings.get(i).wrecked) {
                optionBHelper(dialog, index, i, untargetable);
                index++;
            }
        }
        dialog.setVisible(true);

        threat(40, untargetable);
    }

    private void optionBHelper(JDialog dialog, int index, int num, ArrayList<Integer> untargetable) {
        JButton building = new JButton(camp.unlockedBuildings.get(num).toString());
        building.setBounds(100, 30 * index, 180, 30);
        building.addActionListener(e ->
        {
            untargetable.add(camp.unlockedBuildings.get(num).number);
            building.setVisible(false);
            if (untargetable.size() >= 2) {
                dialog.dispose();
            }
        });
        dialog.add(building);
    }

    public void threat(int attackVal, ArrayList<Integer> untargetable) {
        if (camp.findBuildingInUnlocked(65) != -1) {
            camp.unlockedBuildings.get(camp.findBuildingInUnlocked(65)).wrecked = true;
        }
        camp.outpostAttack(true, true, untargetable, true, attackVal, 3, camp.getClosestTargeting(65));
        camp.distributeThings(3, "Lumber");
    }
}