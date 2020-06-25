package Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This class represents the main menu of the application.
 * Displays the main menu with the buttons and the logo of
 * the game.
 * @author Kierian Tirlemont
 */
public class MainMenu {

    /**
     * The JFrame which contains the buttons and the logo.
     */
    static JFrame mainMenu;

    /**
     * The constructor of the class which creates the elements of the frame.
     */
    public MainMenu(){
        mainMenu = new JFrame("Zen l'initie : welcome menu");
        mainMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainMenu.add(containerMainMenu());

        mainMenu.getContentPane().setBackground(Color.ORANGE);
        mainMenu.setMinimumSize(mainMenu.getSize());
        mainMenu.pack();
        mainMenu.setVisible(true);
    }

    /**
     * This method creates the container which contains the JPanels.
     * @return A container with the graphical element.
     */
    private Container containerMainMenu(){
        Container ret = new Container();
        ret.setLayout(new BorderLayout());

        ret.add(logoZen(),BorderLayout.NORTH);
        ret.add(createTheButton(),BorderLayout.CENTER);

        return ret;
    }

    /**
     * Creates a label which contains the logo of the game.
     * @return A label which contains the logo of the game.
     */
    private JPanel logoZen(){
        JPanel ret = new JPanel();
        ret.setLayout(new BorderLayout(1,1));
        JLabel labelLogo = new JLabel();
        try {
            Image img = ImageIO.read(getClass().getClassLoader().getResource("Logo_Zen.png"));
            labelLogo.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ret.add(labelLogo);
        return ret;
    }

    /**
     * Creates buttons for the main menu.
     * @return Buttons for the main menu.
     */
    private JPanel createTheButton(){
        JPanel ret = new JPanel();
        ret.setLayout(new GridLayout(4,1,10,10));

        JButton buttonPlay = new JButton("Play");
        Action.buttonListener(buttonPlay,"Play");
        JButton buttonSavedGame = new JButton("Saved game");
        Action.buttonListener(buttonSavedGame,"Saved game");
        JButton buttonTutorial = new JButton("Tutorial");
        Action.buttonListener(buttonTutorial,"Tutorial");
        JButton buttonQuit = new JButton("Quit");
        Action.buttonListener(buttonQuit,"Quit");

        ret.add(buttonPlay);
        ret.add(buttonSavedGame);
        ret.add(buttonTutorial);
        ret.add(buttonQuit);

        return ret;
    }

}
