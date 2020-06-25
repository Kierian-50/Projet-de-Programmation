package Display;

import gameElement.Game;
import gameElement.Mode;
import gameElement.Pawn;
import gameElement.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is the listener of the graphical elements.
 * @author Kierian Tirlemont
 */
class Action {

    /**
     * This array list contains the possible square from a selected
     * pawn. This squares will be paint in green.
     */
    private static ArrayList<Square> greenSquare = new ArrayList<>();
    /**
     * The selected pawn by the player.
     */
    private static Pawn selectedPawn;
    /**
     * This boolean says if the user choose the mode HH or HA.
     * True : HA.
     */
    private static boolean oneBoolean = true;
    /**
     * This boolean says if the user has select a pawn.
     */
    private static boolean isSelected = false;
    /**
     * This boolean represents the selection of a final position
     * for a pawn.
     */
    private static boolean choosePosition = false;
    /**
     * This method represents the selection of the option by the
     * user.
     */
    private static boolean option = true;
    /**
     * The filename for a saved game.
     */
    private static String filename;

    /**
     * This method finds the selected button and calls the good
     * method which will do the correct action thanks to the
     * name of the button.
     * @param button The button.
     * @param buttonName The name of the button.
     */
    static void buttonListener(JButton button, String buttonName){ //déclaration de ta méthode
        //tu mets en écoute le boutton
        button.addActionListener(e -> {
            Mode mode;
            switch (buttonName){
                case "Square":
                    int[] XY = Technical.findThePosition(button);
                    if(!isSelected){
                        if(DisplayMyGrid.theGame.getZenPawn().getSquare().getX() == XY[0] &&
                                DisplayMyGrid.theGame.getZenPawn().getSquare().getY() == XY[1]){
                            selectedPawn = DisplayMyGrid.theGame.getZenPawn();
                            isSelected = true;
                        }else if(DisplayMyGrid.theGame.pawnBelongTo(XY[0],XY[1]) == DisplayMyGrid.theGame.getCurrentPlayer()){
                            selectedPawn = DisplayMyGrid.theGame.getCurrentPlayer().whichPawn(XY[0],XY[1]);
                            isSelected = true;
                        }
                        if(isSelected){
                            for(Square s : DisplayMyGrid.theGame.authorizeSquare(XY[0],XY[1])){
                                greenSquare.add(s);
                                if(DisplayMyGrid.theGame.isOption()){
                                    DisplayMyGrid.squares[s.getX()][s.getY()].setBackground(Color.GREEN);
                                }
                            }
                        }
                    }else{
                        choosePosition = true;
                    }

                    if(isSelected && choosePosition){
                        for(Square s : greenSquare){
                            if(s.getX() == XY[0] && s.getY() == XY[1]){
                                DisplayMyGrid.theGame.mainGameGui(selectedPawn.getSquare().getX(),
                                        selectedPawn.getSquare().getY(),XY[0],XY[1]);
                            }
                        }
                        DisplayMyGrid.initSquareColor();
                        greenSquare.clear();
                        isSelected = false;
                        choosePosition = false;
                    }

                    break;

                case "New game":
                    if(DisplayNewGame.firstPlayerName.getText().equals("") && oneBoolean){
                        DisplayNewGame.textErrorName.setText("Please enter your name !");
                        DisplayNewGame.textErrorName.setVisible(true);
                    }else if(DisplayNewGame.firstPlayerName.getText().equals("") ||
                            DisplayNewGame.secondPlayerName.getText().equals("") && !oneBoolean){
                        DisplayNewGame.textErrorName.setText("Please enter your names !");
                        DisplayNewGame.textErrorName.setVisible(true);
                    }else{
                        DisplayNewGame.newGameFrame.dispose();
                        if(oneBoolean){
                            DisplayNewGame.secondPlayerName.setText("bot");
                            mode = Mode.HA;
                        }else{
                            mode = Mode.HH;
                        }

                        Game game = new Game(DisplayNewGame.firstPlayerName.getText()
                                ,DisplayNewGame.secondPlayerName.getText(),mode,option);
                        game.startGui();
                    }
                    break;

                case "Quit":
                    System.exit(1);
                    break;

                case "Play":
                    MainMenu.mainMenu.dispose();
                    new DisplayNewGame();
                    break;

                case "Return main menu":
                    if(EndOfGame.endOfGameFrame != null){
                        EndOfGame.endOfGameFrame.dispose();
                    }else if(SavedGame.submenuSavedGame != null){
                        SavedGame.submenuSavedGame.dispose();
                    }
                    new MainMenu();
                    break;

                case "Save and leave":
                    DisplayMyGrid.gameFrame.dispose();
                    DisplayMyGrid.theGame.saveTheGame();
                    new MainMenu();
                    break;

                case "Saved game":
                    MainMenu.mainMenu.dispose();
                    new SavedGame();
                    break;

                case "Access saved game":
                    filename = button.getText();
                    SavedGame.submenuSavedGame.remove(SavedGame.createDisplaySavedGame);
                    SavedGame.submenuSavedGame.add(SavedGame.continueDeleteSavedGame);
                    SavedGame.submenuSavedGame.revalidate();
                    SavedGame.submenuSavedGame.repaint();
                    break;

                case "Launch saved game":
                    Technical.launchSavedGame(filename);
                    filename = null;
                    SavedGame.submenuSavedGame.dispose();
                    break;

                case "Delete saved game":
                    Technical.deleteSavedGame(filename);
                    filename = null;
                    SavedGame.submenuSavedGame.dispose();
                    new SavedGame();
                    break;

                case "Tutorial":
                    MainMenu.mainMenu.dispose();
                    new TutorialGui();
                    break;

                case "Continue":
                    if(TutorialGui.currentPage.equalsIgnoreCase("Page1")){
                        TutorialGui.tutorialGui.remove(TutorialGui.page1);
                        TutorialGui.tutorialGui.add(TutorialGui.page2);
                        TutorialGui.tutorialGui.revalidate();
                        TutorialGui.tutorialGui.repaint();
                        TutorialGui.currentPage = "Page2";
                    }else if(TutorialGui.currentPage.equalsIgnoreCase("Page2")){
                        TutorialGui.tutorialGui.remove(TutorialGui.page2);
                        TutorialGui.tutorialGui.add(TutorialGui.page3);
                        TutorialGui.tutorialGui.revalidate();
                        TutorialGui.tutorialGui.repaint();
                        TutorialGui.currentPage = "Page3";
                    }else if(TutorialGui.currentPage.equalsIgnoreCase("Page3")){
                        TutorialGui.tutorialGui.remove(TutorialGui.page3);
                        TutorialGui.tutorialGui.add(TutorialGui.page4);
                        TutorialGui.tutorialGui.revalidate();
                        TutorialGui.tutorialGui.repaint();
                        TutorialGui.currentPage = "Page4";
                    }else if(TutorialGui.currentPage.equalsIgnoreCase("Page4")) {
                        TutorialGui.tutorialGui.remove(TutorialGui.page4);
                        TutorialGui.tutorialGui.add(TutorialGui.page5);
                        TutorialGui.tutorialGui.revalidate();
                        TutorialGui.tutorialGui.repaint();
                        TutorialGui.currentPage = "Page5";
                    }else if(TutorialGui.currentPage.equalsIgnoreCase("Page5")){
                        TutorialGui.tutorialGui.remove(TutorialGui.page5);
                        TutorialGui.tutorialGui.add(TutorialGui.page6);
                        TutorialGui.tutorialGui.revalidate();
                        TutorialGui.tutorialGui.repaint();
                        TutorialGui.currentPage = "Page6";
                    }else if(TutorialGui.currentPage.equalsIgnoreCase("Page6")) {
                        TutorialGui.tutorialGui.remove(TutorialGui.page6);
                        TutorialGui.tutorialGui.add(TutorialGui.page7);
                        TutorialGui.tutorialGui.revalidate();
                        TutorialGui.tutorialGui.repaint();
                        TutorialGui.currentPage = "Page7";
                    }else if(TutorialGui.currentPage.equalsIgnoreCase("Page7")){
                        TutorialGui.tutorialGui.dispose();
                        new MainMenu();
                    }
                    break;

                default:
                    break;
            }
        });
    }

    /**
     * This method is the listener of the radioButton. When the
     * method is called, it finds the correct action with the name
     * of the radiobutton and do the required action.
     * @param radioButton The radioButton.
     * @param nameRadioButton The name of the radio button.
     */
    static void radioListener(JRadioButton radioButton, String nameRadioButton){
        radioButton.addActionListener(e -> {
            switch (nameRadioButton){
                case "Solo":
                    DisplayNewGame.secondPlayerName.setVisible(false);
                    DisplayNewGame.textSecondPlayer.setVisible(false);
                    oneBoolean = true;
                    break;
                case "Two":
                    DisplayNewGame.secondPlayerName.setVisible(true);
                    DisplayNewGame.textSecondPlayer.setVisible(true);
                    oneBoolean = false;
                    break;

                case "Option":

                    break;

                default:
                    break;
            }

        });
    }

    /**
     * This method is the listener of of the checkbox. When
     * the method is called, it does the required action.
     * @param checkBox The checkbox.
     * @param nameCheckBox The name of the checkbox.
     */
    static void checkListener(JCheckBox checkBox, String nameCheckBox){
        checkBox.addActionListener(e -> {
            switch (nameCheckBox){
                case "Option":
                    if(checkBox.isSelected()){
                        option = true;
                    }else{
                        option = false;
                    }
                    break;

                default:
                    break;
            }

        });
    }

}
