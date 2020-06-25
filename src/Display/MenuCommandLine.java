package Display;

import gameElement.Game;
import gameElement.Match;
import gameElement.Mode;
import utility.RWFile;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class allows to print a welcome menu for the user, and add several functions for the user :
 * The possibility to have a tutorial.
 * The possibility to launch a saved game.
 * The possibility to turn on or off the option which print the possible square of a pawn.
 * @author Kierian Tirlemont
 */
public class MenuCommandLine {

    /**
     * The name of the file of a not finish game in a match
     */
    private static String fileNameGameForMatch;
    /**
     * The scanner
     */
    private Scanner sc = new Scanner(System.in);
    /**
     * The boolean which represent the option
     * which allows to print the possible squares from a pawn.
     */
    private boolean option = true;
    /**
     * The name of the first player.
     */
    private String namePlayer1;
    /**
     * The name of the second player.
     */
    private String namePlayer2;
    /**
     * The selected mode by the user.
     */
    private Mode mode;
    /**
     * The position of the zen : this is use for the saved game.
     */
    private int[] zenPosition;
    /**
     * The position of the pawns of the first player : this is use for the saved game.
     */
    private int[][] pawnsPlayer1 = new int[12][2];
    /**
     * The position of the pawns of the second player : this is use for the saved game.
     */
    private int[][] pawnsPlayer2 = new int[12][2];
    /**
     * The absolute path to arrive to the data file.
     */
    private static String dataPath;

    /**
     * The empty constructor that set the value of the attribute dataPath.
     */
    public MenuCommandLine(){
        findDataPath();
    }

    /**
     * This method is able to find the path of the class
     * and set the attribute dataPath.
     */
    private void findDataPath(){
        String path = null;
        try {
            path = URLDecoder.decode(MenuCommandLine.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
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
     * Print the main menu/welcome page.
     */
    public void printFirstMenu(){
        clearScreen();
        System.out.println(" ________  _______   ________           ___       ___  ________   ___  _________  ___  _______      \n" +
                "|\\_____  \\|\\  ___ \\ |\\   ___  \\        |\\  \\     |\\  \\|\\   ___  \\|\\  \\|\\___   ___\\\\  \\|\\  ___ \\     \n" +
                " \\|___/  /\\ \\   __/|\\ \\  \\\\ \\  \\       \\ \\  \\    \\ \\  \\ \\  \\\\ \\  \\ \\  \\|___ \\  \\_\\ \\  \\ \\   __/|    \n" +
                "     /  / /\\ \\  \\_|/_\\ \\  \\\\ \\  \\       \\ \\  \\    \\ \\  \\ \\  \\\\ \\  \\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\_|/__  \n" +
                "    /  /_/__\\ \\  \\_|\\ \\ \\  \\\\ \\  \\       \\ \\  \\____\\ \\  \\ \\  \\\\ \\  \\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\_|\\ \\ \n" +
                "   |\\________\\ \\_______\\ \\__\\\\ \\__\\       \\ \\_______\\ \\__\\ \\__\\\\ \\__\\ \\__\\   \\ \\__\\ \\ \\__\\ \\_______\\\n" +
                "    \\|_______|\\|_______|\\|__| \\|__|        \\|_______|\\|__|\\|__| \\|__|\\|__|    \\|__|  \\|__|\\|_______|\n" +
                "                                                                                                    \n" +
                "\n");
        System.out.println("Welcome Page"+"\n");
        System.out.println("Welcome in the game Zen l'Initie developed by Kierian Tirlemont" + "\n");
        System.out.println("- Play");
        System.out.println("- Option");
        System.out.println("- Quit");
        System.out.println("\nPlease write the name of the submenu that you want to visit :");
        String select = sc.nextLine();

        while(!select.equals("Play") && !select.equals("Option") && !select.equals("Quit")){
            System.out.println("This submenu "+select+" is not available." + "\n");
            System.out.println("Please write the name of the submenu that you want to visit :");
            select = sc.nextLine();
        }

        switch (select){
            case "Play":
                submenuPlay();
                break;

            case "Option":
                submenuOption();
                break;

            case "Quit":
                System.out.println("Thank you for visiting our game! Come back when you want !" + "\n");
                System.exit(1);
                break;

            default:
                break;
        }
    }

    /**
     * The submenu when you click on play in the main menu.
     */
    private void submenuPlay(){
        clearScreen();

        System.out.println("WelcomePage/Play"+"\n");
        System.out.println("- New game");
        System.out.println("- New match");
        System.out.println("- Saved game");
        System.out.println("- Saved match");
        System.out.println("- Tutorial");
        System.out.println("- Return");
        System.out.println("\nPlease write the name of the submenu that you want to visit :");
        String select = sc.nextLine();

        while(!select.equals("New game") && !select.equals("Saved game")
                && !select.equals("Return") && !select.equals("Tutorial") && !select.equals("New match")
                && !select.equals("Saved match")){
            System.out.println("This submenu "+select+" is not available." + "\n");
            System.out.println("Please write the name of the submenu that you want to visit :");
            select = sc.nextLine();
        }

        switch (select){
            case "New game":
                submenuNewGame();
                break;

            case "New match":
                submenuNewMatch();
                break;

            case "Saved game":
                printSavedGameSubmenu();
                break;

            case "Saved match":
                submenuSavedMatch();
                break;

            case "Tutorial":
                submenuTutorial();
                submenuPlay();
                break;

            case "Return":
                printFirstMenu();
                break;

            default:
                break;
        }

    }

    /**
     * This method prints the submenu for the saved match.
     */
    private void submenuSavedMatch(){
        clearScreen();
        System.out.println("WelcomePage/Play/SavedMatch"+"\n");
        System.out.println("Bellow, you can see every saved match that you can continue\n");

        int positionInTheFile = dataPath.split("/").length;

        int i = 0;
        String filename = dataPath+"saved_match"+i+".txt";
        String[] partsFilename;

        while(RWFile.isFile(filename)){
            partsFilename = filename.split("/");
            System.out.println("- "+partsFilename[positionInTheFile]);
            i++;
            filename = dataPath+"saved_match"+i+".txt";
        }
        System.out.println("- Return");
        System.out.println("Would you like to continue or delete a saved game ?");
        System.out.println("If you want to return to the play menu write \"Return\" else write the entire " +
                "name of the file");

        String enterFilename;
        enterFilename = sc.nextLine();
        while (!RWFile.isFile(dataPath+enterFilename) && !enterFilename.equals("Return")) {
            System.out.println(enterFilename+" does not exist. Please write the complete name " +
                    "of the saved game that you want to continue or delete");
            enterFilename = sc.nextLine();
        }

        switch (enterFilename){
            case "Return":
                submenuPlay();
                break;

            default:
                accessToASavedMatch(enterFilename);
                break;
        }
    }

    /**
     * This method allows to know if the user wants to delete
     * or continue the game.
     * @param savedMatch The name of the saved file that the user
     *                   wants to access.
     */
    private void accessToASavedMatch(String savedMatch){
        clearScreen();
        System.out.println("WelcomePage/Play/SavedGame/"+savedMatch+"\n");
        System.out.println("Would you like to : ");
        System.out.println("- Continue this game");
        System.out.println("- Delete this game");
        System.out.println("\nPlease write \"Delete\" or \"Continue\" according to your desires : ");

        String enter = sc.nextLine();
        while(!enter.equals("Delete") && !enter.equals("Continue")){
            System.out.println("Please enter your choice by writing \"Delete\" or \"Continue\"");
            enter = sc.nextLine();
        }

        switch (enter){
            case "Delete":
                deleteSavedMatch(savedMatch);
                submenuPlay();
                break;

            case "Continue":
                continueSavedMatch(savedMatch);
                break;

            default:
                break;
        }
    }

    /**
     * The method which represents the continuation of a match.
     * Prepare the match, and launch it.
     * @param savedMatch The saved match that the user wants to
     *                   continue.
     */
    private void continueSavedMatch(String savedMatch){
        ArrayList<String> configuration = RWFile.readFile(dataPath+savedMatch);

        this.mode = Mode.valueOf(configuration.get(0));
        this.option = Boolean.parseBoolean(configuration.get(1));
        this.namePlayer1 = configuration.get(2);
        int scorePlayer1 = Integer.parseInt(configuration.get(3));
        this.namePlayer2 = configuration.get(4);
        int scorePlayer2 = Integer.parseInt(configuration.get(5));

        if(!configuration.get(6).equals("null")){
            fileNameGameForMatch = configuration.get(6);
        }else{
            fileNameGameForMatch = null;
        }


        Match match = new Match(this.namePlayer1,this.namePlayer2,this.mode,this.option, scorePlayer1,
                scorePlayer2,savedMatch, fileNameGameForMatch);
        match.start();

        System.out.println("\nPress the enter key on your keyboard to return to the main menu");
        sc.nextLine();
        printFirstMenu();
    }

    /**
     * This method allows to delete a saved match.
     * @param savedMatch The saved match that the user want to
     *                   delete.
     */
    public static void deleteSavedMatch(String savedMatch){
        int i = 0;
        String numberOfTheDeleteFile = savedMatch.replaceAll("\\D+","");
        int numberSavedGame = Integer.parseInt(numberOfTheDeleteFile);
        String filename = dataPath+"saved_match"+i+".txt";

        while(RWFile.isFile(filename)){
            i++;
            filename = dataPath+"saved_match"+i+".txt";
        }

        RWFile.deleteFile(dataPath+savedMatch);
        if((i-1) != numberSavedGame){
            RWFile.renameFile(dataPath+savedMatch,dataPath+"saved_match"+(i-1)+".txt");
        }
        if(RWFile.isFile(dataPath+fileNameGameForMatch)){
            deleteSavedGameForMatch(dataPath+fileNameGameForMatch);
        }
    }

    /**
     * This method allows to delete the game saved in a match.
     * Only use in the match's class after the end of the first
     * that has been save game.
     * @param savedMatch The name of the file which will be delete.
     */
    public static void deleteSavedGameForMatch(String savedMatch){
        int i = 0;
        String numberOfTheDeleteFile = savedMatch.replaceAll("\\D+","");
        int numberSavedGame = Integer.parseInt(numberOfTheDeleteFile);
        String filename = dataPath+"saved_game_for_match"+i+".txt";

        while(RWFile.isFile(filename)){
            i++;
            filename = dataPath+"saved_game_for_match"+i+".txt";
        }

        RWFile.deleteFile(dataPath+savedMatch);
        if((i-1) != numberSavedGame){
            RWFile.renameFile(savedMatch,dataPath+"saved_game_for_match"+(i-1)+".txt");
        }
    }

    /**
     * The submenu which allows the creation of a new match.
     */
    private void submenuNewMatch(){
        clearScreen();
        System.out.println("WelcomePage/Play/NewMatch"+"\n");

        System.out.println("Do you want to play alone or in pairs ?");
        System.out.println("Please enter \"One\" or \"Two\" according to your desires : ");
        String select = sc.nextLine();

        while(!select.equals("One") && !select.equals("Two")){
            System.out.println("Please enter your choice by writing \"One\" or \"Two\" ");
            select = sc.nextLine();
        }

        if(select.equals("One")){
            System.out.println("\nPlease enter your name: ");
            this.namePlayer1 = sc.nextLine();
            while (this.namePlayer1.equalsIgnoreCase("bot")){
                System.out.println("Sorry, this name is forbidden, choose another please.");
                this.namePlayer1 = sc.nextLine();
            }
            this.namePlayer2 = "bot";
            this.mode = Mode.HA;
        }else if(select.equals("Two")){
            System.out.println("\nPlease enter the name of the first player: ");
            this.namePlayer1 = sc.nextLine();
            System.out.println("\nPlease enter the name of the second player: ");
            this.namePlayer2 = sc.nextLine();
            this.mode = Mode.HH;
        }

        Match match = new Match(this.namePlayer1,this.namePlayer2,this.mode,this.option);
        match.start();

        System.out.println("\nPress a key on your keyboard to return to the main menu");
        sc.nextLine();
        printFirstMenu();
    }

    /**
     * The submenu when you select Tutorial in play submenu.
     * It allows to create the tutorial.
     */
    private void submenuTutorial(){
        clearScreen();
        System.out.println("WelcomePage/Play/Tutorial"+"\n");
        TutorialCommandLine.tutorial();

    }

    /**
     * The submenu when you select the option in the main menu.
     */
    private void submenuOption(){
        clearScreen();
        System.out.println("WelcomePage/Option"+"\n");

        System.out.println("Would you like that the possible square for a pawn would be print ?\n(Recommended " +
                "for beginner)");
        System.out.println("Please enter your choice by writing \"Yes\" or \"No\" ");

        String select = sc.nextLine();
        while(!select.equals("Yes") && !select.equals("No")){
            System.out.println("Please enter your choice by writing \"Yes\" or \"No\" ");
            select = sc.nextLine();
        }

        switch (select){
            case "Yes":
                this.option = true;
                break;

            case "No":
                this.option = false;
                break;

            default:
                break;
        }

        printFirstMenu();
    }

    /**
     * The submenu when you select new game in play menu.
     * It allows the creation of the game.
     */
    private void submenuNewGame(){

        clearScreen();
        System.out.println("WelcomePage/Play/NewGame"+"\n");

        System.out.println("Do you want to play alone or in pairs ?");
        System.out.println("Please enter \"One\" or \"Two\" according to your desires : ");
        String select = sc.nextLine();

        while(!select.equals("One") && !select.equals("Two")){
            System.out.println("Please enter your choice by writing \"One\" or \"Two\" ");
            select = sc.nextLine();
        }

        if(select.equals("One")){
            System.out.println("\nPlease enter your name: ");
            this.namePlayer1 = sc.nextLine();
            while (this.namePlayer1.equalsIgnoreCase("bot")){
                System.out.println("Sorry, this name is forbidden, choose another please.");
                this.namePlayer1 = sc.nextLine();
            }
            this.namePlayer2 = "bot";
            this.mode = Mode.HA;
        }else if(select.equals("Two")){
            System.out.println("\nPlease enter the name of the first player: ");
            this.namePlayer1 = sc.nextLine();
            System.out.println("\nPlease enter the name of the second player: ");
            this.namePlayer2 = sc.nextLine();
            this.mode = Mode.HH;
        }

        Game game = new Game(this.namePlayer1,this.namePlayer2,this.mode,this.option);
        game.start();

        System.out.println("\nPress a key on your keyboard to return to the main menu");
        sc.nextLine();

        printFirstMenu();
    }

    /**
     * This method allows to clear the screen of the console.
     */
    private static void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "\n\n");
    }

    /**
     * The submenu when you select saved menu.
     */
    private void printSavedGameSubmenu(){
        clearScreen();
        System.out.println("WelcomePage/Play/SavedGame"+"\n");
        System.out.println("Bellow, you can see every saved game that you can continue\n");

        int positionInTheFile = dataPath.split("/").length;

        int i = 0;
        String filename = dataPath+"saved_game"+i+".txt";
        String[] partsFilename;

        while(RWFile.isFile(filename)){
            partsFilename = filename.split("/");
            System.out.println("- "+partsFilename[positionInTheFile]);
            i++;
            filename = dataPath+"saved_game"+i+".txt";
        }
        System.out.println("- Return");
        System.out.println("Would you like to continue or delete a saved game ?");
        System.out.println("If you want to return to the play menu write \"Return\" else write the entire " +
                "name of the file");

        String enterFilename;
        enterFilename = sc.nextLine();
        while (!RWFile.isFile(dataPath+enterFilename) && !enterFilename.equals("Return")) {
            System.out.println(enterFilename+" does not exist. Please write the complete name " +
                    "of the saved game that you want to continue or delete");
            enterFilename = sc.nextLine();
        }


        switch (enterFilename){
            case "Return":
                submenuPlay();
                break;

            default:
                accessToASavedGame(enterFilename);
                break;
        }
    }

    /**
     * When the user has chosen a game that he want to delete or continue.
     * @param savedGame The game that the user when to continue.
     */
    private void accessToASavedGame(String savedGame){
        clearScreen();
        System.out.println("WelcomePage/Play/SavedGame/"+savedGame+"\n");
        System.out.println("Would you like to : ");
        System.out.println("- Continue this game");
        System.out.println("- Delete this game");
        System.out.println("\nPlease write \"Delete\" or \"Continue\" according to your desires : ");

        String enter = sc.nextLine();
        while(!enter.equals("Delete") && !enter.equals("Continue")){
            System.out.println("Please enter your choice by writing \"Delete\" or \"Continue\"");
            enter = sc.nextLine();
        }

        switch (enter){
            case "Delete":
                Technical.deleteSavedGame(savedGame);
                submenuPlay();
                break;

            case "Continue":
                continueSavedGame(savedGame);
                break;

            default:
                break;
        }
    }



    /**
     * This method allows to continue a saved game.
     * @param savedGame The saved game that the user want to continue.
     */
    private void continueSavedGame(String savedGame){
        readConfiguration(savedGame);
        Game game = new Game(this.namePlayer1,this.namePlayer2,this.mode,this.option,this.pawnsPlayer1,
                this.pawnsPlayer2,zenPosition, savedGame);
        game.start();
        System.out.println("\nPress a key on your keyboard to return to the main menu");
        sc.nextLine();
        initializeTheAttribute();
        printFirstMenu();
    }

    /**
     * This method allows to read the configuration of a file which contains the
     * information about the saved game.
     * @param savedGame The saved game that the user want to continue.
     */
    private void readConfiguration(String savedGame){
        ArrayList<String> configuration = RWFile.readFile(dataPath+savedGame);
        boolean found = false;

        this.mode = Mode.valueOf(configuration.get(0));
        this.option = Boolean.parseBoolean(configuration.get(1));

        String[] stringZenPosition = configuration.get(2).split(":");
        this.zenPosition = new int[2];
        this.zenPosition[0] = Integer.parseInt(stringZenPosition[0]);
        this.zenPosition[1] = Integer.parseInt(stringZenPosition[1]);

        this.namePlayer1 = configuration.get(3);

        int i = 4;
        int j = 0;
        while(!found){
            String[] stringPosition = configuration.get(i).split(":");
            if(stringPosition[0].equalsIgnoreCase("end")){
                found = true;
            }else{
                this.pawnsPlayer1[j][0] = Integer.parseInt(stringPosition[0]);
                this.pawnsPlayer1[j][1] = Integer.parseInt(stringPosition[1]);
            }
            i++;
            j++;
        }
        found = false;

        this.namePlayer2 = configuration.get(i);
        i++;
        j=0;

        while (!found){
            String[] stringPosition = configuration.get(i).split(":");
            if(stringPosition[0].equalsIgnoreCase("end")){
                found = true;
            }else{
                this.pawnsPlayer2[j][0] = Integer.parseInt(stringPosition[0]);
                this.pawnsPlayer2[j][1] = Integer.parseInt(stringPosition[1]);
            }
            i++;
            j++;
        }
    }

    /**
     * This method allows to initialize the attribute.
     */
    private void initializeTheAttribute(){
        this.pawnsPlayer1 = null;
        this.pawnsPlayer2 = null;
        this.zenPosition = null;
        this.mode = null;
        this.namePlayer1 = null;
        this.namePlayer2 = null;
        this.option = true;
    }
}
