package Display;

import javax.swing.*;
import java.awt.*;

/**
 * This class allows to display a frame to set the
 * game. This frame will launch the game with the
 * option.
 * @author Kierian Tirlemont
 */
class DisplayNewGame {

    /**
     * This attributes represents the frame which display the possible
     * option and set the game.
     */
    static JFrame newGameFrame;
    /**
     * This JTextField represents the first name.
     */
    static JTextField firstPlayerName;
    /**
     * This JTextField represents the second name.
     */
    static JTextField secondPlayerName;
    /**
     * This label display a short text to know what to enter.
     * But this label is an attribute because we must remove it
     * if the user chooses to play in solo.
     */
    static JLabel textSecondPlayer;
    /**
     * The text in the case of the creation of a game without
     * entering the required name.
     */
    static JLabel textErrorName;

    /**
     * The constructor of the class with creates the frame.
     */
    DisplayNewGame(){
        initWindows();
    }

    /**
     * This method allows to initialize the frame and his components
     * and allows the display of the frame.
     */
    private void initWindows(){
        newGameFrame = new JFrame("Zen l'Initie : New game");
        newGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        newGameFrame.add(createNewGameFrame());

        newGameFrame.setMinimumSize(newGameFrame.getSize());
        newGameFrame.pack();
        newGameFrame.setVisible(true);
    }

    /**
     * This method creates the main component of the frame with
     * a container.
     * @return A container with every JPanels.
     */
    private Container createNewGameFrame(){
        Container ret = new Container();
        ret.setLayout(new BorderLayout());

        JPanel radioButtons = createRadioButtonSoloPairs();
        JPanel testPlayersNames = createJTextFieldNamePlayers();

        ret.add(radioButtons,BorderLayout.NORTH);
        ret.add(testPlayersNames,BorderLayout.CENTER);
        ret.add(southContainer(),BorderLayout.SOUTH);

        return ret;
    }

    /**
     * This method creates the JTextfield to allows the user
     * to write her names.
     * @return A JPanel with every components that allows to the
     * user to write his name.
     */
    private JPanel createJTextFieldNamePlayers(){
        JPanel ret = new JPanel();

        JLabel textFirstPlayer = new JLabel("Enter the name of the first player :");
        firstPlayerName = new JTextField();
        textSecondPlayer = new JLabel("Enter the name of the second player :");
        secondPlayerName = new JTextField();
        textSecondPlayer.setVisible(false);
        secondPlayerName.setVisible(false);
        ret.setLayout(new GridLayout(4,0));
        ret.add(textFirstPlayer);
        ret.add(firstPlayerName);
        ret.add(textSecondPlayer);
        ret.add(secondPlayerName);

        return ret;
    }

    /**
     * This method is able to create the radio buttons to
     * choose the mode : HH, HA.
     * @return The JPanel with the components which allows to
     * the user to choose the mode.
     */
    private JPanel createRadioButtonSoloPairs(){
        JPanel ret = new JPanel();

        ButtonGroup buttonGroupNumberPlayers = new ButtonGroup();

        JLabel text = new JLabel("How many players wants to play ?");
        JLabel empty = new JLabel();
        JRadioButton solo = new JRadioButton("Solo");
        Action.radioListener(solo,"Solo");
        buttonGroupNumberPlayers.add(solo);
        solo.setSelected(true);

        JRadioButton two = new JRadioButton("Two");
        Action.radioListener(two,"Two");
        buttonGroupNumberPlayers.add(two);

        ret.setLayout(new GridLayout(2,2));
        ret.add(text);
        ret.add(empty);
        ret.add(solo);
        ret.add(two);

        return ret;
    }

    /**
     * This method creates the check box which allows to the
     * user to choose if he wants the options for his game.
     * @return A JPanel with the components around the checkbox.
     */
    private JPanel createCheckBoxOption(){
        JPanel ret = new JPanel();

        JCheckBox option = new JCheckBox("Would you like that the possible square for a pawn would be print ?");
        option.setSelected(true);
        Action.checkListener(option,"Option");

        ret.setLayout(new GridLayout(2,1,5,5));
        ret.add(option);

        return ret;
    }

    /**
     * This method creates the buttons that allows to
     * creates and launch the game.
     * @return A JPanel with the buttons and a error text in
     * case of a error.
     */
    private JPanel playButton(){
        JPanel ret = new JPanel();

        textErrorName = new JLabel();
        textErrorName.setForeground(Color.RED);
        textErrorName.setVisible(false);

        JButton play = new JButton("Play");
        Action.buttonListener(play,"New game");
        ret.setLayout(new GridLayout(2,1));
        ret.add(textErrorName);
        ret.add(play);

        return ret;
    }

    /**
     * This method calls both method that creates the JPanel
     * of the south of the frame.
     * @return A JPanel which contains the elements of the south.
     */
    private JPanel southContainer(){
        JPanel ret = new JPanel();
        ret.setLayout(new GridLayout(2,1));
        ret.add(createCheckBoxOption());
        ret.add(playButton());
        return ret;
    }

}
