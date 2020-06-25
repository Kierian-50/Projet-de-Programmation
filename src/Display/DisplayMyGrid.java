package Display;

import gameElement.Game;
import gameElement.Mode;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This method allows to display the grid of the current game.
 * This method uses the listener's class "Action".
 * @author Kierian Tirlemont
 */
public class DisplayMyGrid {

    /**
     * The name of the first player.
     */
    String playerName1;
    /**
     * The name of the second player.
     */
    String playerName2;
    /**
     * The selected mode by the user.
     */
    Mode mode;
    /**
     * If the user wants to select the option which allows
     * to print the possible square.
     */
    boolean option;
    /**
     * The frame which contains the grid of the game.
     */
    public static JFrame gameFrame;
    /**
     * The game which will be display on the frame.
     */
    static Game theGame;
    /**
     * The grid of JButton.
     */
    public static JButton[][] squares;
    /**
     * This boolean is used to know if the current game is a saved
     * game or not in order do delete the saved game at the end.
     */
    static boolean isSavedGame = false;
    /**
     * The name of the file if this is a saved game.
     */
    public static String filename;

    /**
     * The constructor of the class in the case of a saved game and
     * to continue this saved game.
     * @param player1 The name of the first player.
     * @param player2 The name of the second player.
     * @param mode The mode selected by the user.
     * @param option The boolean which represents the selection of the options.
     * @param pawnsPlayer1 The position of the pawns of the first player.
     * @param pawnsPlayer2 The position of the pawns of the second player
     * @param zenPawn The position the zen pawn.
     * @param fileName The name of the file which contains the saved game.
     */
    public DisplayMyGrid(String player1, String player2, Mode mode, boolean option,int[][] pawnsPlayer1,
                         int[][] pawnsPlayer2, int[] zenPawn, String fileName){
        theGame = new Game(player1,player2,mode,option,pawnsPlayer1,pawnsPlayer2,zenPawn,fileName);
        isSavedGame = true;
        filename = fileName;
    }

    /**
     * The constructor of the class in a normal case.
     * @param game The current game to print.
     */
    public DisplayMyGrid(Game game){
        theGame = game;
    }

    /**
     * This method allows to display the grid on the frame
     * with the current game.
     */
    public void displayGrid(){
        initWindows();
        displayThePawnsOnGridGui();
    }

    /**
     * This method initializes every frame's components.
     * And allows to set visible the frame.
     */
    private void initWindows(){
        gameFrame = new JFrame("Zen l'Initie : Game");
        squares = new JButton[11][11];
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        gameFrame.add(createGrid(),BorderLayout.CENTER);
        gameFrame.add(buttons(),BorderLayout.EAST);

        gameFrame.setMinimumSize(gameFrame.getSize());
        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    /**
     * This method allows to create the grid of JButtons.
     * @return The JPanel with the array of JButtons.
     */
    private JPanel createGrid(){

        JPanel ret;

        ret = new JPanel(new GridLayout(0, 12));
        ret.setBorder(new LineBorder(Color.BLACK));

        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(Color.decode("#710000"));
                } else {
                    b.setBackground(Color.decode("#E48130"));
                }
                squares[j][i] = b;
                Action.buttonListener(squares[j][i],"Square");
            }
        }

        ret.add(new JLabel(""));
        for (int i = 0; i < 11; i++) {
            ret.add(new JLabel(String.valueOf(i), SwingConstants.CENTER));
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                switch (j) {
                    case 0:
                        ret.add(new JLabel("" + (i),
                                SwingConstants.CENTER));
                    default:
                        ret.add(squares[j][i]);
                }
            }
        }
        return ret;
    }

    /**
     * This method allows to place the pawns on the grid of the
     * frame. Uses the image of pawns in the file Resources.
     */
    private void displayThePawnsOnGridGui(){
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if(!theGame.getTheGrid().getSquare(i,j).isFree()){
                    if (theGame.getPlayer2().whichPawn(i, j) != null) {
                        try {
                            Image img = ImageIO.read(getClass().getClassLoader().getResource("White_Pawn.png"));
                            squares[i][j].setIcon(new ImageIcon(img));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (theGame.getPlayer1().whichPawn(i, j) != null) {
                        try {
                            Image img = ImageIO.read(getClass().getClassLoader().getResource("Black_Pawn.png"));
                            squares[i][j].setIcon(new ImageIcon(img));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (theGame.getZenPawn().getSquare().getX() == i && theGame.getZenPawn().getSquare().getY() == j) {
                        try {
                            Image img = ImageIO.read(getClass().getClassLoader().getResource("Red_Pawn.png"));
                            squares[i][j].setIcon(new ImageIcon(img));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.err.print("ERROR");
                    }
                }
            }
        }
    }

    /**
     * This method is able to set the color of the background of
     * the buttons of the grid to remove the green background of
     * the possible square.
     */
    static void initSquareColor(){
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    squares[j][i].setBackground(Color.decode("#710000"));
                } else {
                    squares[j][i].setBackground(Color.decode("#E48130"));
                }

            }
        }
    }

    /**
     * This method creates the buttons "save and leave" and puts
     * it in a JPanel.
     * @return A JPanel with the button "save and leave".
     */
    private JPanel buttons(){
        JPanel ret = new JPanel();

        JButton saveAndLeave = new JButton("Save and leave");
        Action.buttonListener(saveAndLeave,"Save and leave");

        ret.add(saveAndLeave);

        return ret;
    }
}
