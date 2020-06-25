package gameElement;

import Display.MenuCommandLine;
import utility.RWFile;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a game with several sleeve.
 * @author Kierian Tirlemont
 */
public class Match {

	/**
	 * The name of the file of the game in a case of the user
	 * save the game.
	 */
	private String filenameGame;
	/**
	 * The name of the file of the match with the information
	 * about the current game..
	 */
	private String filename;
	/**
	 * The counter for the first player.
	 */
	private int counterScorePlayer1;
	/**
	 * The counter for the second player.
	 */
	private int counterScorePlayer2;
	/**
	 * The sleeves of the game.
	 */
	private ArrayList<Game> sleeves;
	/**
	 * The second player.
	 */
	private String playerName2;
	/**
	 * The first player.
	 */
	private String playerName1;
	/**
	 * The selected mode.
	 */
	private Mode mode;
	/**
	 * The boolean which says if the option is activated.
	 * The option is the display of the possible squares for a pawn.
	 */
	private boolean option;
	/**
	 * The scanner of the class.
	 */
	private Scanner sc = new Scanner(System.in);
	/**
	 * The name of the file of the game that has been save and
	 * must be launch as a first game. This attribute is only
	 * use in the case of the user launch a saved match with a
	 * game which is not finish.
	 */
	private String fileCurrentGame;

	/**
	 * The path to arrive to the class/jar.
	 */
	private String dataPath;

	/**
	 * The constructor of the class which initializes the attributes.
	 * @param option The boolean which says if the option is activated.
	 * @param mode The selected mode.
	 * @param playerName1 The name of the first player.
	 * @param playerName2 The name of the second player.
	 */
	public Match(String playerName1, String playerName2, Mode mode, boolean option){
		if(mode == null){
			throw new IllegalArgumentException("Match : One of the argument is null !");
		}
		this.mode = mode;
		this.playerName1 = playerName1;
		this.playerName2 = playerName2;
		this.option = option;
		this.sleeves = new ArrayList<>();
		this.counterScorePlayer1 = 0;
		this.counterScorePlayer2 = 0;
		findDataPath();
	}

	/**
	 * The second constructor of the class, uses in the case of the
	 * continuation of a match.
	 * @param playerName1 The name of the first player.
	 * @param playerName2 The name of the second player.
	 * @param mode The selected mode by the users.
	 * @param option The boolean which represents the option which is
	 *               the display of the possible squares for a pawn.
	 * @param scorePlayer1 The score of the first player.
	 * @param scorePlayer2 The score of the second player.
	 * @param filename The name of the file for the settings of the match.
	 *                 data/saved_match+i+.txt
	 * @param fileCurrentGame The name of the file for the setting of the
	 *                        current game which is not finish.
	 *                        data/saved_game_for_match"+j+".txt
	 *
	 */
	public Match(String playerName1, String playerName2, Mode mode, boolean option, int scorePlayer1, int scorePlayer2,
				 String filename,String fileCurrentGame){
		if(mode == null){
			throw new IllegalArgumentException("Match : One of the argument is null !");
		}
		this.mode = mode;
		this.playerName1 = playerName1;
		this.playerName2 = playerName2;
		this.option = option;
		this.sleeves = new ArrayList<Game>();
		this.counterScorePlayer1 = scorePlayer1;
		this.counterScorePlayer2 = scorePlayer2;
		this.filename = filename;
		this.fileCurrentGame = fileCurrentGame;
		findDataPath();
	}

	/**
	 * This method is able to find the path of the class
	 * and set the attribute dataPath.
	 */
	private void findDataPath(){
		String path = null;
		try {
			path = URLDecoder.decode(Match.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
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
	 * This method is the launcher of the match.
	 * In every cases, this is the only launcher to launch
	 * the match.
	 */
	public void start(){
		System.out.println(description());
		boolean exit = false;

		if(this.fileCurrentGame != null){
			launchTheCurrentGame();
			exit = quit();
		}

		while(counterScorePlayer1<7 && counterScorePlayer2<7 && !exit){
			Game game = new Game(playerName1,playerName2,this.mode,this.option);
			this.sleeves.add(game);
			game.start();
			countTheScore();
			exit = saveTheGameForTheMatch(game);
			if(!exit){
				System.out.println(this.playerName1+" : "+this.counterScorePlayer1+"\n" +
						this.playerName2+" : "+this.counterScorePlayer2);
				exit = quit();
			}
		}
		if(!exit){
			endOfMatch();
		}else{
			saveTheMatch();
			System.out.println("Do not hesitate to continue the match later, " +
					"you can find it in the \"Saved match\" submenu" + "\n");
		}
	}

	/**
	 * In the case of a not finish game for the match,
	 * this method allows to launch a not finish game
	 * in the match.
	 */
	private void launchTheCurrentGame(){
		System.out.println(this.fileCurrentGame);
		ArrayList<String> configuration = RWFile.readFile(dataPath+this.fileCurrentGame);
		boolean found = false;

		this.mode = Mode.valueOf(configuration.get(0));
		this.option = Boolean.parseBoolean(configuration.get(1));

		String[] stringZenPosition = configuration.get(2).split(":");
		int[] zenPosition = new int[2];
		zenPosition[0] = Integer.parseInt(stringZenPosition[0]);
		zenPosition[1] = Integer.parseInt(stringZenPosition[1]);

		String player1 = configuration.get(3);

		int[][] pawnsPlayer1 = new int[12][2];
		int[][] pawnsPlayer2 = new int[12][2];

		int i = 4;
		int j = 0;
		while(!found){
			String[] stringPosition = configuration.get(i).split(":");
			if(stringPosition[0].equalsIgnoreCase("end")){
				found = true;
			}else{
				pawnsPlayer1[j][0] = Integer.parseInt(stringPosition[0]);
				pawnsPlayer1[j][1] = Integer.parseInt(stringPosition[1]);
			}
			i++;
			j++;
		}
		found = false;

		String player2 = configuration.get(i);
		i++;
		j=0;

		while (!found){
			String[] stringPosition = configuration.get(i).split(":");
			if(stringPosition[0].equalsIgnoreCase("end")){
				found = true;
			}else{
				pawnsPlayer2[j][0] = Integer.parseInt(stringPosition[0]);
				pawnsPlayer2[j][1] = Integer.parseInt(stringPosition[1]);
			}
			i++;
			j++;
		}
		System.out.println("I see that you continue an old match. In this match, you can't leave the first \n" +
                "game because it's already a backup and it would not save what you'll do but don't worry in the\n " +
                "next games of this match you could exit the match and have a backup." +
                "\nWe apologize for the inconvenience.");
		Game currentGame = new Game(player1,player2,this.mode,this.option,pawnsPlayer1,pawnsPlayer2,zenPosition,
				this.fileCurrentGame);
		sleeves.add(currentGame);
		currentGame.start();
		countTheScore();
		System.out.println(""+this.playerName1+" : "+this.counterScorePlayer1+"\n" +
				this.playerName2+" : "+this.counterScorePlayer2+"");
		MenuCommandLine.deleteSavedGameForMatch(this.fileCurrentGame);
		this.fileCurrentGame = null;
	}

	/**
	 * This method allows to save a game in a match.
	 * This is a intelligent method, this is to say that
	 * the user don't demand the save, when he left the current
	 * game, the method enters in the if...else... and save the
	 * current game. Else, the method doesn't work and return
	 * false.
	 * @param game The current game to save.
	 * @return The boolean which says if the game has been saved
	 * 			because the user has left the game.
	 */
	private boolean saveTheGameForTheMatch(Game game){
		boolean ret = false;
		if(!game.draw && game.isWinner() == null){
			game.saveTheGame();
			int i = 0;
			String filenameGame = "saved_game"+i+".txt";
			while(RWFile.isFile(dataPath+filenameGame)){
				i++;
				filenameGame = "saved_game"+i+".txt";
			}
			if(!filenameGame.equals(dataPath+"saved_game0.txt")) {
				i--;
				filenameGame = "saved_game" + i + ".txt";
			}

			int j = 0;
			String finalName = "saved_game_for_match"+j+".txt";
			while(RWFile.isFile(dataPath+finalName)){
				j = j + 1;
				finalName = "saved_game_for_match"+j+".txt";
			}

			RWFile.renameFile(dataPath+finalName,dataPath+filenameGame);
			this.filenameGame = finalName;
			ret = true;
		}
		return ret;
	}

	/**
	 * Ask to the user if he wants to left the match.
	 * @return The boolean if the user wants to left the match.
	 */
	private boolean quit(){
		boolean ret = false;

		System.out.println("\nPress a key on your keyboard to continue or write \"Exit\" to leave the match");
		String str = sc.nextLine();

		if(str.equalsIgnoreCase("Exit")){
			ret = true;
		}

		return ret;
	}

	/**
	 * This method allows to count the score of the last game
	 * and add it to the attributes which counts the score for
	 * the two players.
	 */
	private void countTheScore(){
		int index = this.sleeves.size()-1;
		Player winner = this.sleeves.get(index).isWinner();
		int score = this.sleeves.get(index).countTheCurrentScore();
		if(winner != null){
			if(winner.getName().equalsIgnoreCase(this.playerName1)){
				this.counterScorePlayer1 += score;
			}else if (winner.getName().equalsIgnoreCase(this.playerName2)){
				this.counterScorePlayer2 += score;
			}
		}
	}

	/**
	 * This method allows to save the match.
	 */
	private void saveTheMatch(){
		ArrayList<String> toWrite = new ArrayList<String>();
		toWrite.add(String.valueOf(this.mode));
		toWrite.add(String.valueOf(this.option));
		toWrite.add(this.playerName1);
		toWrite.add(String.valueOf(this.counterScorePlayer1));
		toWrite.add(this.playerName2);
		toWrite.add(String.valueOf(this.counterScorePlayer2));
		if(this.filenameGame != null){
			toWrite.add(this.filenameGame);
		}else{
			toWrite.add("null");
		}

		//Creation of the file
		if(filename != null){
			RWFile.writeFile(toWrite,dataPath+this.filename);
		}else{
			int i = 0;
			String fileName = dataPath+"saved_match"+i+".txt";
			while (RWFile.isFile(fileName)){
				i++;
				fileName = dataPath+"saved_match"+i+".txt";
			}
			RWFile.createFile(fileName);
			RWFile.writeFile(toWrite,fileName);
		}
	}

	/**
	 * This method make the requires actions when the match is finish.
	 */
	private void endOfMatch(){
		String winner = null;
		if(counterScorePlayer1>=7){
			winner = playerName1;
		}else if(counterScorePlayer2>=7){
			winner = playerName2;
		}
		System.out.println("\nThe winner of the match is "+winner);

		if(this.filename != null){
			MenuCommandLine.deleteSavedMatch(this.filename);
		}
	}

	/**
	 * This method returns the description of the game.
	 * @return The description of the game.
	 */
	private String description(){
		String ret;
		ret =  "Welcome in this new match. The goal here is to have seven points before your opponent. The point are\n";
		ret += "count with the pawn of the looser. We count the number of pawns outside the main chain and we add it to\n";
		ret += "your score.\nGood luck.";
		return ret;
	}

	/**
	 * This method is the getter of the attribute sleeves.
	 * @return The sleeves of the game.
	 */
	public ArrayList<Game> getSleeves() {
		return this.sleeves;
	}

}