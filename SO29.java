import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SO29 extends Event {
    public SO29(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO29.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO29.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 78, 89, 293, 206);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        threat(10, new ArrayList<>());
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

        threat(15, untargetable);
    }

    private void optionBHelper(JDialog dialog, int index, int num, ArrayList<Integer> untargetable) {
        JButton building = new JButton(camp.unlockedBuildings.get(num).toString());
        building.setBounds(100, 30 * index, 180, 30);
        building.addActionListener(_ ->
        {
            untargetable.add(camp.unlockedBuildings.get(num).number);
            building.setVisible(false);
            if (untargetable.size() >= 2) {
                dialog.dispose();
            }
        });
        dialog.add(building);
    }

    private void threat(int attackVal, ArrayList<Integer> untargetable) {
        camp.outpostAttack(true, true, untargetable, true, attackVal + camp.defense, 2, new ArrayList<>(Arrays.asList(37, 85, 35, 39, 67, 90, 21, 17, 65, 83, 72, 42, 74, 81, 5, 86, 98, 84, 12, 34, 88, 24)));
        camp.distributeThings(3, "Hide");
    }
}