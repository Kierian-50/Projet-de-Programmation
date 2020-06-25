package Display;

import gameElement.Player;

import javax.swing.*;
import java.awt.*;

/**
 * This method displays a frame after the game to print the
 * winner and the buttons to return in the main menu.
 * @author Kierian Tirlemont.
 */
public class EndOfGame {
    /**
     * The frame which display the name of the winner.
     */
    static JFrame endOfGameFrame;

    /**
     * The constructor of the class which initializes the frame
     * and his components.
     */
    public EndOfGame(){

        if(DisplayMyGrid.isSavedGame){
            Technical.deleteSavedGame(DisplayMyGrid.filename);
        }

        endOfGameFrame = new JFrame("New game");
        endOfGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        endOfGameFrame.add(finalPageGame());

        endOfGameFrame.setMinimumSize(endOfGameFrame.getSize());
        endOfGameFrame.pack();
        endOfGameFrame.setVisible(true);
    }

    /**
     * This method creates a container which contains every JPanels.
     * @return The container which contains every elements of the page.
     */
    private Container finalPageGame(){
        Container ret = new Container();
        ret.setLayout(new BorderLayout());

        ret.add(printTheWinner(),BorderLayout.CENTER);

        return ret;
    }

    /**
     * This method finds the winner and count the score. And
     * displays it on the frame.
     * @return A JPanel with every components.
     */
    private JPanel printTheWinner(){
        JPanel ret = new JPanel();
        ret.setLayout(new GridLayout(3,1));
        JLabel labelNameWinner = new JLabel();
        JLabel labelScoreWinner = new JLabel();
        Player winner = DisplayMyGrid.theGame.isWinner();

        if(DisplayMyGrid.theGame.getCurrentPlayer() == winner){
            winner = null;
        }

        if(winner == null){
            labelNameWinner.setText("Draw !");
        }else{
            labelNameWinner.setText("The winner is "+winner.getName());
            labelScoreWinner.setText("The score is "+ DisplayMyGrid.theGame.countTheCurrentScore());

        }

        JButton button = new JButton("Return to the menu");
        Action.buttonListener(button,"Return main menu");
        ret.add(labelNameWinner);
        ret.add(labelScoreWinner);
        ret.add(button);

        return ret;
    }

}
