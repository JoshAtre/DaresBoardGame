import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class Main extends JFrame {

    private DareGame dares;
    private static Main frame;

    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setSize(650,670);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        TextField textField = new TextField("START");
        textField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.magenta);
        textField.setEditable(false);
        textField.setBounds(30,30,58,30);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        layeredPane.setBounds(6, 6, 632, 644);
        layeredPane.add(textField);
        panel.add(layeredPane);

        dares = new DareGame();
        layeredPane.add(dares.getBoard(), new Integer(0));
        layeredPane.setVisible(true);
    }

    public DareGame getDares() {
        return dares;
    }

    public Main getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        frame = new Main();
        frame.setVisible(true);

        DareGame dares = frame.getDares();
        dares.game();
    }
}