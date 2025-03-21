// import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DareGame {
    private Board board;
    private Pair<String, Integer>[] dares = new Pair[20];
    private int player1Position;
    private int player2Position;
    private int player1Points;
    private int player2Points;
    private int num = 1;

    private static final Font BIG_FONT = new Font(null, Font.PLAIN, 36);
    private static final Font SMALL_FONT = new Font(null, Font.PLAIN, 26);

    private enum Player {PLAYER1, PLAYER2}
    private String[] chickenOutMsgs = {"Wow, I guess you chickened out on this one",
        "Coward!", "You chicken!"};


    public DareGame() {
        board = new Board();
        board.setBackground(new Color(0, 0, 0));

        initializeDares();
    }

    public void game() {
        JOptionPane.showMessageDialog(null, "Welcome to DARES! The rules are simple: " +
                "1) Two player game. 2) Roll the dice and move that many spaces on the board.\n" +
                "3) Each square on the board corresponds to a dare, and each dare has a point value based " +
                "on its difficulty (1-4).\n4) First to 10 points wins!");

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
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int player1Move = rollDice.getNumSteps();
            player1Position += player1Move;
            player1Position %= dares.length;
            JButton[] spaces = board.getSpaces();

            for (int i = 0; i < spaces.length; i++) {
                spaces[i].setFont(BIG_FONT);
                if (spaces[i].getText().equals("1")) {
                    spaces[i].setText("");
                } else if (spaces[i].getText().equals("1, 2")) {
                    spaces[i].setText("2");
                }
            }

            if (spaces[player1Position].getText().equals("2")) {
                spaces[player1Position].setFont(SMALL_FONT);
                spaces[player1Position].setText("1, 2");
            } else {
                spaces[player1Position].setText("1");
            }

            spaces[player1Position].setEnabled(true);

            Main frame = new Main();

            if (num == 1) {
                JOptionPane.showMessageDialog(null, "Click on the space where you landed for your dare");
                num++;
            }
            frame.getFrame().setVisible(true);

            while (spaces[player1Position].isEnabled()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
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
                JOptionPane.showMessageDialog(null, getChickenMsg());
            }


            JOptionPane.showMessageDialog(null, "Roll the dice, Player 2");
            rollDice.getRollButton().setEnabled(true);
            window.setVisible(true);

            while (rollDice.getRollButton().isEnabled()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int player2Move = rollDice.getNumSteps();
            player2Position += player2Move;
            player2Position %= dares.length;

            for (int i = 0; i < spaces.length; i++) {
                spaces[i].setFont(BIG_FONT);
                if (spaces[i].getText().equals("2")) {
                    spaces[i].setText("");
                } else if (spaces[i].getText().equals("1, 2")) {
                    spaces[i].setText("1");
                }
            }

            if (spaces[player2Position].getText().equals("1")) {
                spaces[player2Position].setFont(SMALL_FONT);
                spaces[player2Position].setText("1, 2");
            } else {
                spaces[player2Position].setText("2");
            }

            spaces[player2Position].setEnabled(true);

            if (num == 2) {
                JOptionPane.showMessageDialog(null, "Click on the space where you landed for your dare");
                num++;
            }
            frame.getFrame().setVisible(true);

            while (spaces[player2Position].isEnabled()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
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
                JOptionPane.showMessageDialog(null, getChickenMsg());
            }
        }
    }

    public Board getBoard() {
        return  board;
    }

    // Private
    //
    private void initializeDares() {
        dares[0] = new Pair<>("Call a Chinese restaurant and order a pizza.", 1);
        dares[1] = new Pair<>("Put chocolate syrup on a pickle and eat it.", 2);
        dares[2] = new Pair<>("Call your crush and explain the rules of monopoly to them.", 4);
        dares[3] = new Pair<>("Eat a teaspoon of either mustard, soy sauce or hot sauce.", 3);
        dares[4] = new Pair<>("Color one of your front teeth black.", 3);
        dares[5] = new Pair<>("Call your Mom and tell her you can’t find a girlfriend in a very panicked voice.", 4);
        dares[6] = new Pair<>("Lick the floor.", 3);
        dares[7] = new Pair<>("Say 'I love you' to the first person you see other than the person you are playing with.", 2);
        dares[8] = new Pair<>("Do 40 pushups in less than a minute.", 4);
        dares[9] = new Pair<>("Continuously talk for 3 minutes without stopping.", 1);
        dares[10] = new Pair<>("Eat a whole piece of paper.", 2);
        dares[11] = new Pair<>("Go outside and pick exactly 40 blades of grass with a pair of tweezers.", 3);
        dares[12] = new Pair<>("Take gum off the bottom of your desk and chew it.", 4);
        dares[13] = new Pair<>("Eat a raw egg.", 3);
        dares[14] = new Pair<>("Like every post of someone that the other player chooses on Instagram.", 4);
        dares[15] = new Pair<>("Ask Sos if you can use public instance variables", 4);
        dares[16] = new Pair<>("Spin around 10 times and try to walk straight.", 1);
        dares[17] = new Pair<>("Let the other person choose an item for you to brush your teeth with.", 3);
        dares[18] = new Pair<>("Take an embarrassing selfie and post it as your profile picture.", 2);
        dares[19] = new Pair<>("Put soap from the soap dispenser on your head and walk around the whole campus.", 4);
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

    private String getChickenMsg() {
        Random random = new Random();
        int index = random.nextInt(chickenOutMsgs.length);
        return chickenOutMsgs[index];
    }
}
