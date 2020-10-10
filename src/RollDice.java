import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RollDice extends JPanel {
    private Die leftDie;
    private Die rightDie;
    private int numSteps;
    private JButton rollButton;

    public RollDice() {
        leftDie = new Die();
        rightDie = new Die();

        rollButton = new JButton("Roll Dice");
        rollButton.setFont(new Font("Serif", Font.BOLD, 16));
        rollButton.addActionListener(new RollListener());
        rollButton.setEnabled(true);

        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new GridLayout(1, 2, 4, 0));
        dicePanel.add(leftDie);
        dicePanel.add(rightDie);

        this.setLayout(new BorderLayout());
        this.add(rollButton, BorderLayout.NORTH);
        this.add(dicePanel, BorderLayout.CENTER);
    }
    private class RollListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            leftDie.roll();
            rightDie.roll();
            rollButton.setEnabled(false);

            int leftValue = leftDie.getValue();
            int rightValue = rightDie.getValue();
            numSteps = leftValue + rightValue;
        }
    }

    public JButton getRollButton() {
        return rollButton;
    }

    public int getNumSteps() {
        return numSteps;
    }
}

class Die extends JPanel {
    private int value;

    public Die() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(100,100));
        roll();
    }

    public void roll() {
        int val = (int) (Math.random() * 6) + 1;
        setValue(val);
    }

    private void setValue(int spots) {
        value = spots;
        repaint();
    }

    public int getValue() {
        return value;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();

        if (value == 1) {
            placeDotOnDie(g, w / 2, h / 2);
        } else if (value == 2) {
            placeDotOnDie(g, w / 4, h / 4);
            placeDotOnDie(g, 3 * w / 4, 3 * h / 4);
        } else if (value == 3) {
            placeDotOnDie(g, w / 2, h / 2);
            placeDotOnDie(g, w / 4, h / 4);
            placeDotOnDie(g, 3 * w / 4, 3 * h / 4);
        } else if (value == 4) {
            placeDotOnDie(g, w / 4, h / 4);
            placeDotOnDie(g, 3 * w / 4, 3 * h / 4);
            placeDotOnDie(g, 3 * w / 4, h / 4);
            placeDotOnDie(g, w / 4, 3 * h / 4);
        } else if (value == 5) {
            placeDotOnDie(g, w / 2, h / 2);
            placeDotOnDie(g, w / 4, h / 4);
            placeDotOnDie(g, 3 * w / 4, 3 * h / 4);
            placeDotOnDie(g, 3 * w / 4, h / 4);
            placeDotOnDie(g, w / 4, 3 * h / 4);
        } else if (value == 6) {
            placeDotOnDie(g, w / 4, h / 4);
            placeDotOnDie(g, 3 * w / 4, 3 * h / 4);
            placeDotOnDie(g, 3 * w / 4, h / 4);
            placeDotOnDie(g, w / 4, 3 * h / 4);
            placeDotOnDie(g, w / 4, h / 2);
            placeDotOnDie(g, 3 * w / 4, h / 2);
        }
    }

    private void placeDotOnDie(Graphics g, int x, int y) {
        int diameter = 9;
        g.fillOval(x - diameter/2, y - diameter/2, diameter, diameter);
    }
}