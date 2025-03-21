import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Board extends JPanel implements ActionListener {

    private JButton[] spaces = new JButton[20];

    public Board() {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(6,20, 612, 612);
        this.setLayout(null);
        initializeSquares();
    }

    private void initializeSquares() {

        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = new JButton();
            if (i % 2 == 0) {
                spaces[i].setBackground(new Color(255, 70, 0));
            } else {
                spaces[i].setBackground(new Color(0, 120, 200));
            }

            spaces[i].setFont(new Font("Rufscript", Font.BOLD, 70));

            spaces[i].setOpaque(true);
            spaces[i].setEnabled(false);
            spaces[i].addActionListener(this);
        }

        for (int i = 0; i <= 4; i++) {
            spaces[i].setBounds(i * 100 + 6, 6, 100, 100);
            this.add(spaces[i]);
        }
        for (int i = 5; i <= 9; i++) {
            spaces[i].setBounds(506, (i - 5) * 100 + 6, 100, 100);
            this.add(spaces[i]);
        }
        spaces[10].setBounds(506, 506, 100, 100);
        spaces[11].setBounds(406, 506, 100, 100);
        spaces[12].setBounds(306, 506, 100, 100);
        spaces[13].setBounds(206, 506, 100, 100);
        spaces[14].setBounds(106, 506, 100, 100);
        spaces[15].setBounds(6, 506, 100, 100);
        spaces[16].setBounds(6, 406, 100, 100);
        spaces[17].setBounds(6, 306, 100, 100);
        spaces[18].setBounds(6, 206, 100, 100);
        spaces[19].setBounds(6, 106, 100, 100);

        for (int i = 0; i < spaces.length; i++) {
            this.add(spaces[i]);
        }

        JLabel labelDares = new JLabel("DARES!") {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                AffineTransform aT = g2.getTransform();
                Shape oldShape = g2.getClip();
                double x = getWidth() / 2.0;
                double y = getHeight() / 2.0;
                aT.rotate(Math.toRadians(-35), x, y);
                g2.setTransform(aT);
                g2.setClip(oldShape);
                super.paintComponent(g);
            }
        };
        labelDares.setForeground(Color.WHITE);
        labelDares.setBackground(Color.RED);
        labelDares.setOpaque(true);
        labelDares.setHorizontalAlignment(SwingConstants.CENTER);
        labelDares.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
        labelDares.setBounds(179, 277, 263, 55);
        this.add(labelDares);
    }

    public void actionPerformed(ActionEvent click) {
        Object source = click.getSource();
        for (int i = 0; i < spaces.length; i++) {
            if (source == spaces[i]) {
                spaces[i].setEnabled(false);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public JButton[] getSpaces() {
        return spaces;
    }
}
