import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WO8 extends Event {
    public WO8(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO8.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO8.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 104, 176, 212, 323);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        ArrayList<ArrayList<Integer>> targets = new ArrayList<>();
        JDialog dialog = new JDialog((Frame) null, "Choose side to be attacked", true);
        dialog.setLayout(null);
        dialog.setSize(300, 160);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        JButton leftSide = new JButton("Left Side");
        leftSide.setBounds(100, 30, 100, 30);
        leftSide.addActionListener(_ ->
        {
            targets.add(new ArrayList<>(Arrays.asList(5, 12, 37, 34, 39, 24, 65, 81, 35, 72, 21, 44, 83, 42)));
            dialog.dispose();
        });
        dialog.add(leftSide);

        JButton rightSide = new JButton("Right Side");
        rightSide.setBounds(100, 100, 100, 30);
        rightSide.addActionListener(_ ->
        {
            targets.add(new ArrayList<>(Arrays.asList(98, 17, 85, 74, 67, 88, 90, 84)));
            dialog.dispose();
        });
        dialog.add(rightSide);

        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();

        camp.outpostAttack(20, 4, targets.getFirst(), true);
        camp.distributeThings(2, "Hide");
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        boolean[] leftSideAttack = new boolean[1];
        JDialog dialog = new JDialog((Frame) null, "Choose side to be attacked first", true);
        dialog.setLayout(null);
        dialog.setSize(300, 160);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        JButton leftSide = new JButton("Left Side");
        leftSide.setBounds(100, 30, 100, 30);
        leftSide.addActionListener(_ ->
        {
            leftSideAttack[0] = true;
            dialog.dispose();
        });
        dialog.add(leftSide);

        JButton rightSide = new JButton("Right Side");
        rightSide.setBounds(100, 100, 100, 30);
        rightSide.addActionListener(_ ->
        {
            leftSideAttack[0] = false;
            dialog.dispose();
        });
        dialog.add(rightSide);

        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();

        ArrayList<Integer> leftTargets = new ArrayList<>(Arrays.asList(5, 12, 37, 34, 39, 24, 65, 81, 35, 72, 21, 44, 83, 42));
        for (int j = leftTargets.size() - 1; j >= 0; j--) {
            if (!(camp.findBuildingInUnlocked(leftTargets.get(j)) != -1 && camp.unlockedBuildings.get(camp.findBuildingInUnlocked(leftTargets.get(j))).level != 0 &&
                    !camp.unlockedBuildings.get(camp.findBuildingInUnlocked(leftTargets.get(j))).wrecked)) {
                leftTargets.remove(j);
            }
        }
        ArrayList<Integer> rightTargets = new ArrayList<>(Arrays.asList(98, 17, 85, 74, 67, 88, 90, 84));
        for (int j = rightTargets.size() - 1; j >= 0; j--) {
            if (!(camp.findBuildingInUnlocked(rightTargets.get(j)) != -1 && camp.unlockedBuildings.get(camp.findBuildingInUnlocked(rightTargets.get(j))).level != 0 &&
                    !camp.unlockedBuildings.get(camp.findBuildingInUnlocked(rightTargets.get(j))).wrecked)) {
                rightTargets.remove(j);
            }
        }
        ArrayList<Integer> targets = new ArrayList<>();
        while (!rightTargets.isEmpty() || !leftTargets.isEmpty()) {
            if (rightTargets.isEmpty()) {
                targets.add(leftTargets.removeFirst());
            } else if (leftTargets.isEmpty()) {
                targets.add(rightTargets.removeFirst());
            } else if (leftSideAttack[0]) {
                targets.add(leftTargets.removeFirst());
                leftSideAttack[0] = false;
            } else {
                targets.add(rightTargets.removeFirst());
                leftSideAttack[0] = true;
            }
        }
        camp.outpostAttack(20, 8, targets);
        camp.distributeThings(2, "Hide");
    }
}