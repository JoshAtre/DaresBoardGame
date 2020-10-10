import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.concurrent.TimeUnit;

public class Basic extends JFrame implements ActionListener {
    private static final Font MAIN_FONT = new Font("Purisa", Font.BOLD, 18);

    //private JMenuItem mnuNewGame = new JMenuItem("  New Game");
    private JMenuItem mnuExit = new JMenuItem("    Quit");

    private JPanel pnlSouth = new JPanel();
    private JPanel pnlBottom = new JPanel();
    private JPanel pnlPlayingField = new JPanel();

    private JButton[][] spaces = new JButton[5][5];
    private Pair<String, Integer>[] dares = new Pair[25];
    private int player1Position;
    private int player2Position;
    private int player1Points;
    private int player2Points;
    private int num = 1;

    private enum Player {PLAYER1, PLAYER2}


    public Basic() {
        dares[0] = new Pair<>("Call A Chinese Restaurant And Order A Pizza", 1);
        dares[1] = new Pair<>("Put Chocolate Syrup On A Pickle And Eat It", 2);
        dares[2] = new Pair<>("Call Your Crush And Explain The Rules Of Monopoly To Them", 4);
        dares[3] = new Pair<>("Eat A Teaspoon Of Either Mustard, Soy Sauce Or Hot Sauce", 3);
        dares[4] = new Pair<>("Color One Of Your Front Teeth Black", 3);
        dares[5] = new Pair<>("Call Your Mom And Tell Her You Can’t Find A Girlfriend In A Very Panicked Voice", 4);
        dares[6] = new Pair<>("Lick the floor", 3);
        dares[7] = new Pair<>("Say 'I love you' to the first person you see other than the person you are playing with", 2);
        dares[8] = new Pair<>("Do 40 pushups in less than a minute", 4);
        dares[9] = new Pair<>("Continuously talk for 3 minutes without stopping", 1);
        dares[10] = new Pair<>("Eat a whole piece of paper", 2);
        dares[11] = new Pair<>("Lick a car tire", 3);
        dares[12] = new Pair<>("Jump into a dumpster", 2);
        dares[13] = new Pair<>("Eat a raw egg", 3);
        dares[14] = new Pair<>("Like every post of someone that the other player chooses on Instagram", 4);
        dares[15] = new Pair<>("Pick your friend’s nose.", 4);
        dares[16] = new Pair<>("Spin around 10 times and try to walk straight.", 1);
        dares[17] = new Pair<>("Let the other person choose an item for you to brush your teeth with.", 3);
        dares[18] = new Pair<>("Take an embarrassing selfie and post it as your profile picture.", 2);
        dares[19] = new Pair<>("Put soap from the soap dispenser on your head and walk around the whole campus.", 4);
        dares[20] = new Pair<>("Ask Sos if you can use public instance variables.", 4);
        dares[21] = new Pair<>("Take gum off the bottom of your desk and chew it.", 4);
        dares[22] = new Pair<>("Fill your mouth with water, and the other person must tell the funniest joke they know. \nIf you spit out the water, you have to eat a spoonful of dirt.", 3);
        dares[23] = new Pair<>("Go outside and pick exactly 40 blades of grass with a pair of tweezers.", 3);
        dares[24] = new Pair<>("Open your front door and howl like a wolf for 30 seconds.", 3);

        JFrame window = new JFrame("DARES Board Game");
        window.setSize(800, 480);
        window.setLocation(300, 180);
        window.setResizable(true);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setting Panel layouts and properties
        JPanel pnlNorth = new JPanel();
        pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlNorth.setBackground(new Color(70, 70, 70));
        pnlSouth.setBackground(new Color(190, 190, 190));

        JPanel pnlTop = new JPanel();
        pnlTop.setBackground(new Color(190, 190, 190));
        pnlBottom.setBackground(new Color(190, 190, 190));

        pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER));


        // adding menu items to menu bar
        JMenuBar mnuMain = new JMenuBar();
        JMenuItem mnuGameTitle = new JMenuItem("DARES!  ");
        mnuMain.add(mnuGameTitle);
        mnuGameTitle.setEnabled(false);
        mnuGameTitle.setFont(MAIN_FONT);
        /*mnuMain.add(mnuNewGame);
        mnuNewGame.setFont(MAIN_FONT);*/
        mnuMain.add(mnuExit);
        mnuExit.setFont(MAIN_FONT);

        //mnuNewGame.addActionListener(this);
        mnuExit.addActionListener(this);

        pnlNorth.add(mnuMain);
        showGame();

        window.add(pnlNorth, BorderLayout.NORTH);
        window.add(pnlSouth, BorderLayout.CENTER);

        setUpPlayingField();

        window.setVisible(true);
        game();
    }

    public void actionPerformed(ActionEvent click) {
        Object source = click.getSource();

        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces.length; j++) {
                if (source == spaces[i][j]) {
                    spaces[i][j].setEnabled(false);
                }
            }
        }
        if (source == mnuExit) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?",
                    "Quit", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } /*else if (source == mnuNewGame) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new game?"
                    , "New Game?", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                new Basic();
            } else {
                showGame();
            }
        }*/
        pnlSouth.setVisible(false);
        pnlSouth.setVisible(true);
    }

    private void game() {
        JOptionPane.showMessageDialog(null, "Welcome to DARES! The rules are simple: " +
                "1) Two player game. 2) Roll the dice and move that many spaces on the board.\n" +
                "3) Each square on the board corresponds to a dare, and each dare has a point value based " +
                "on its difficulty (1-4).\n4) If you get to the end of the board, you go back to the beginning. " +
                "5) First to 10 points wins!");

        RollDice rollDice = new RollDice();
        JFrame window = new JFrame();
        window.setTitle("Dice Roll");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(rollDice);
        window.pack();

        while (true) {
            JOptionPane.showMessageDialog(null, "Roll the dice, Player 1");
            rollDice.getRollButton().setEnabled(true);
            window.setVisible(true);

            while (rollDice.getRollButton().isEnabled()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int player1Move = rollDice.getNumSteps();
            player1Position += player1Move;
            player1Position %= 25;
            Pair<Integer, Integer> p1 = positionToRowCol(player1Position);

            for (int i = 0; i < spaces.length; i++) {
                for (int j = 0; j < spaces.length; j++) {
                    if (spaces[i][j].getText().equals("1")) {
                        spaces[i][j].setText("");
                    } else if (spaces[i][j].getText().equals("1, 2")) {
                        spaces[i][j].setText("2");
                    }
                }
            }

            if (spaces[p1.getKey()][p1.getValue()].getText().equals("2")) {
                spaces[p1.getKey()][p1.getValue()].setText("1, 2");
            } else {
                spaces[p1.getKey()][p1.getValue()].setText("1");
            }

            spaces[p1.getKey()][p1.getValue()].setEnabled(true);

            if (num == 1) {
                JOptionPane.showMessageDialog(null, "Click on the space where you landed for your dare");
                num++;
            }

            while (spaces[p1.getKey()][p1.getValue()].isEnabled()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            JOptionPane.showMessageDialog(null, dares[player1Position].getKey() +
                    "\nDifficulty: " + dares[player1Position].getValue());

            int reply = JOptionPane.showConfirmDialog(null, "Did you complete the dare, Player 1?", "", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                addPoints(Player.PLAYER1, dares[player1Position].getValue());
                if (isWinner()) {
                    JOptionPane.showMessageDialog(null, "Player 1 wins!");
                    break;
                }

                if (player1Points == 1) {
                    JOptionPane.showMessageDialog(null, "Player 1, you now have " + player1Points + " point");
                } else {
                    JOptionPane.showMessageDialog(null, "Player 1, you now have " + player1Points + " points");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wow, I guess you chickened out on this one");
            }


            JOptionPane.showMessageDialog(null, "Roll the dice, Player 2");
            rollDice.getRollButton().setEnabled(true);

            while (rollDice.getRollButton().isEnabled()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int player2Move = rollDice.getNumSteps();
            player2Position += player2Move;
            player2Position %= 25;
            Pair<Integer, Integer> p2 = positionToRowCol(player2Position);

            for (int i = 0; i < spaces.length; i++) {
                for (int j = 0; j < spaces.length; j++) {
                    if (spaces[i][j].getText().equals("2")) {
                        spaces[i][j].setText("");
                    } else if (spaces[i][j].getText().equals("1, 2")) {
                        spaces[i][j].setText("1");
                    }
                }
            }

            if (spaces[p2.getKey()][p2.getValue()].getText().equals("1")) {
                spaces[p2.getKey()][p2.getValue()].setText("1, 2");
            } else {
                spaces[p2.getKey()][p2.getValue()].setText("2");
            }

            spaces[p2.getKey()][p2.getValue()].setEnabled(true);

            while (spaces[p2.getKey()][p2.getValue()].isEnabled()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            JOptionPane.showMessageDialog(null, dares[player2Position].getKey() +
                    "\nDifficulty: " + dares[player2Position].getValue());

            reply = JOptionPane.showConfirmDialog(null, "Did you complete the dare, Player 2?", "", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                addPoints(Player.PLAYER2, dares[player2Position].getValue());
                if (isWinner()) {
                    JOptionPane.showMessageDialog(null, "Player 2 wins!");
                    break;
                }

                if (player2Points == 1) {
                    JOptionPane.showMessageDialog(null, "Player 2, you now have " + player2Points + " point");
                } else {
                    JOptionPane.showMessageDialog(null, "Player 2, you now have " + player2Points + " points");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wow, I guess you chickened out on this one");
            }
        }
    }

    private void addPoints(Player player, int numPoints) {
        if (player == Player.PLAYER1) {
            player1Points += numPoints;
        } else {
            player2Points += numPoints;
        }
    }

    private boolean isWinner() {
        return player1Points >= 10 || player2Points >= 10;
    }

    private void showGame() {
        pnlSouth.setLayout(new BorderLayout());
        pnlSouth.add(pnlPlayingField, BorderLayout.CENTER);
        pnlPlayingField.requestFocus();
    }

    private void setUpPlayingField() {
        pnlPlayingField.removeAll();
        pnlPlayingField.setLayout(new GridLayout(spaces.length, spaces.length, 2, 2));
        pnlPlayingField.setBackground(Color.black);
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces.length; j++) {
                spaces[i][j] = new JButton();

                if ((i + j) % 2 == 0) {
                    spaces[i][j].setBackground(new Color(255, 70, 0));
                }
                if ((i + j) % 2 == 1) {
                    spaces[i][j].setBackground(new Color(0, 120, 200));
                }

                spaces[i][j].setFont(new Font("Rufscript", Font.BOLD, 70));

                spaces[i][j].setOpaque(true);
                spaces[i][j].addActionListener(this);
                pnlPlayingField.add(spaces[i][j]);
                spaces[i][j].setEnabled(false);
            }
        }
    }

    private Pair<Integer, Integer> positionToRowCol(int position) {
        int column = position % spaces.length - 1;
        if (column == -1) {
            column = spaces.length - 1;
        }

        int row = position / spaces.length;
        if (column == spaces.length - 1) {
            row = (position - 1) / spaces.length;
        }

        return new Pair<>(row, column);
    }

    public static void main(String[] args) {
        new Basic();
    }
}