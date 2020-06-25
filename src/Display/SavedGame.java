package Display;

import utility.RWFile;

import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * This class represents the frame with the saved game.
 * Display the possible saved game and propose to continue
 * or delete a saved game.
 * @author Kierian Tirlemont
 */
class SavedGame {

    /**
     * The frame.
     */
    static JFrame submenuSavedGame;
    /**
     * The list of JButtons which contains the name of the saved game.
     */
    private ArrayList<JButton> savedGameButtons;
    /**
     * The container which contains the name of the saved game.
     */
    static Container createDisplaySavedGame;
    /**
     * The container which contains the buttons that allows to continue
     * or delete the saved game.
     */
    static Container continueDeleteSavedGame;

    /**
     * The absolute path to find the class.
     */
    private static String dataPath;

    /**
     * The constructor of the class which initializes the attributes
     * and display the possible saved game at first.
     */
    SavedGame(){
        savedGameButtons = new ArrayList<>();
        submenuSavedGame = new JFrame("Zen l'Initie : Saved game");

        findDataPath();

        submenuSavedGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createDisplaySavedGame = createSavedGame();
        continueDeleteSavedGame = accessToSavedGame();

        submenuSavedGame.add(createDisplaySavedGame);

        submenuSavedGame.setMinimumSize(submenuSavedGame.getSize());
        submenuSavedGame.pack();
        submenuSavedGame.setVisible(true);
    }

    /**
     * This method is able to find the path of the class
     * and set the attribute dataPath.
     */
    private void findDataPath(){
        String path = null;
        try {
            path = URLDecoder.decode(SavedGame.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String[] data = path.split("/");
        path ="";
        for(int i=0;i<data.length-2;i++){
            path+=data[i]+"/";
        }

        path += "data/";
        dataPath = path;
    }

    /**
     * This method allows to create a container which contains
     * the name of the saved game.
     * @return A container with the saved game.
     */
    private Container createSavedGame(){
        Container ret = new Container();
        ret.setLayout(new BorderLayout());

        JPanel buttonsSavedGame = createTheButtonForSavedGame();

        ret.add(buttonsSavedGame);

        return ret;
    }

    /**
     * This method allows to create the buttons with the names
     * of the saved game.
     * @return A JPanel with the buttons.
     */
    private JPanel createTheButtonForSavedGame(){
        JPanel ret = new JPanel();

        int positionInTheFile = dataPath.split("/").length;

        int i = 0;
        String filename = dataPath+"saved_game"+i+".txt";
        String[] partsFilename;

        while(RWFile.isFile(filename)){
            partsFilename = filename.split("/");

            JButton b = new JButton(partsFilename[positionInTheFile]);
            Action.buttonListener(b,"Access saved game");
            savedGameButtons.add(b);

            i++;
            filename = dataPath+"saved_game"+i+".txt";

        }
        ret.setLayout(new GridLayout(i+1,1,5,5));
        for(JButton b : savedGameButtons){
            ret.add(b);
        }
        JButton returnButton = new JButton("Return");
        Action.buttonListener(returnButton,"Return main menu");
        ret.add(returnButton);

        return ret;
    }

    /**
     * This method allows to create the container with the button
     * that displays the possibility to delete or continue a saved
     * game.
     * @return A container which contains the buttons "continue" and
     * "delete"
     */
    private Container accessToSavedGame(){
        Container ret = new Container();
        ret.setLayout(new GridLayout(2,1));

        JButton buttonContinue = new JButton("Continue");
        Action.buttonListener(buttonContinue,"Launch saved game");
        JButton buttonDelete = new JButton("Delete");
        Action.buttonListener(buttonDelete,"Delete saved game");


        ret.add(buttonContinue);
        ret.add(buttonDelete);

        return ret;
    }

}
