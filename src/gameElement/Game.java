package gameElement;

import Display.*;
import utility.RWFile;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a sleeve.
 * @author Kierian Tirlemont
 */
public class Game {

	/**
	 * The grid of the game.
	 */
	private TheGrid theGrid;
	/**
	 * The zen pawn's.
	 */
	private Pawn zenPawn;
	/**
	 * The current player.
	 */
	private Player currentPlayer;
	/**
	 * One of the player.
	 */
	private Player player1;
	/**
	 * One of the player.
	 */
	private Player player2;
	/**
	 * The selected mode.
	 */
	private Mode mode;
	/**
	 * Boolean which represent the selection of the option.
	 */
	private boolean option = true;
	/**
	 * The name of the file in the case of the continuation of
	 * a game.
	 */
	private String filename;
	/**
	 * This square represents the position of the pawn which is
	 * captured.
	 */
	private Square captureSquare;
	/**
	 * This attributes represents a draw at the end of the game.
	 * Use for in the case of a match.
	 */
	boolean draw = false;
	/**
	 * The path to get to find the class.
	 */
	private String dataPath;
	/**
	 * This attribute is used for the graphical interface, this is the
	 * object that allows to display the grid.
	 */
	private static DisplayMyGrid displayMyGrid;

	/**
	 * The constructor of the class which initialize the attributes.
	 * @param player1 The name of the first player.
	 * @param player2 The name of the second player.
	 * @param mode The selected mode by the user.
	 * @param option A boolean which represents the selection of the option.
	 */
	public Game(String player1, String player2, Mode mode, boolean option) {
		if(mode == null){
			throw new IllegalArgumentException("Game : One of the parameter/Player is null !");
		}
		this.mode = mode;
		this.theGrid = new TheGrid();
		this.zenPawn = new Pawn(theGrid.getSquare(5,5),Color.RED);
		this.theGrid.getSquare(5,5).setFree(false);
		this.filename = null;
		this.findDataPath();

		this.captureSquare = new Square(-1,-1);

		if(this.mode == Mode.HH){

			this.player1 = new HumanPlayer(Color.BLACK,player1);
			this.player2 = new HumanPlayer(Color.WHITE,player2);

			this.theGrid = this.player1.placePawns(this.theGrid);
			this.theGrid = this.player2.placePawns(this.theGrid);

			this.option = option;

			this.currentPlayer = this.player1;

		}else if(this.mode == Mode.HA){

			this.player1 = new HumanPlayer(Color.BLACK,player1);
			this.player2 = new AutoPlayer(Color.WHITE,player2);

			this.theGrid = this.player1.placePawns(this.theGrid);
			this.theGrid = this.player2.placePawns(this.theGrid);

			this.option = option;

			this.currentPlayer = this.player1;
		}

	}

	/**
	 * The second constructor of the class which is use in the case of
	 * continuation of a game that has been save.
	 * @param player1 The name of the first player.
	 * @param player2 The name of the second player.
	 * @param mode The selected mode by the user.
	 * @param option The boolean which represents the selection of the option.
	 * @param pawnsPlayer1 The array which contains the position of the pawns
	 *                     of the first player.
	 * @param pawnsPlayer2 The array which contains the position of the pawns
	 *                     of the second player.
	 * @param zenPawn The position of the zen pawn.
	 * @param fileName The name of the file.
	 */
	public Game(String player1, String player2, Mode mode, boolean option,int[][] pawnsPlayer1, int[][] pawnsPlayer2,
				int[] zenPawn, String fileName){

		if(mode == null || fileName == null){
			throw new IllegalArgumentException("Game : One of the parameter/Player is null !");
		}

		this.mode = mode;
		this.theGrid = new TheGrid();
		this.zenPawn = new Pawn(theGrid.getSquare(zenPawn[0],zenPawn[1]),Color.RED);
		this.theGrid.getSquare(zenPawn[0],zenPawn[1]).setFree(false);
		this.filename = fileName;
		this.option = option;

		this.captureSquare = new Square(-1,-1);
		this.findDataPath();

		if(this.mode == Mode.HH){

			this.player1 = new HumanPlayer(Color.BLACK,player1);
			this.player2 = new HumanPlayer(Color.WHITE,player2);

			this.theGrid = this.player1.placePawns(this.theGrid,pawnsPlayer1);
			this.theGrid = this.player2.placePawns(this.theGrid,pawnsPlayer2);

			this.currentPlayer = this.player1;

		}else if(this.mode == Mode.HA){

			this.player1 = new HumanPlayer(Color.BLACK,player1);
			this.player2 = new AutoPlayer(Color.WHITE,player2);

			this.theGrid = this.player1.placePawns(this.theGrid,pawnsPlayer1);
			this.theGrid = this.player2.placePawns(this.theGrid,pawnsPlayer2);

			this.currentPlayer = this.player1;
		}
	}

	/**
	 * This method finds the absolute path for the jar.
	 */
	private void findDataPath(){

		String path = null;
		try {
			path = URLDecoder.decode(Game.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] data = path.split("/");
		path ="";
		for(int i=0;i<data.length-2;i++){
			path+=data[i]+"/";
		}

		path += "data/";
		this.dataPath = path;
	}

	/**
	 * This method is the center of the sleeve, it allows to play
	 * by calling the require element at the good moment.
	 */
	public void start() {
		System.out.println(description());
		System.out.println(this.player1.getName()+" has the blue pawn with the cross");
		System.out.println(this.player2.getName()+" has the green pawn with the circle\n");
		boolean exit = false;
		if(this.mode == Mode.HH){
			while(!isFinish(this.player1) && !isFinish(this.player2) && !exit){
				displayBoard();
				if(option){
					moveChosenPawnWithOption();
				}else{
					moveChosenPawnWithoutOption();
				}
				changeCurrent();
				exit = quit();
			}
		}else if(this.mode == Mode.HA){
			while(!isFinish(this.player1) && !isFinish(this.player2) && !exit){
				if(this.currentPlayer == this.player1){
					displayBoard();
					if(option){
						moveChosenPawnWithOption();
					}else{
						moveChosenPawnWithoutOption();
					}
					exit = quit();
				}else{
					movePawnAutoPlayer();
				}
				changeCurrent();
			}
		}

		if(!exit){
			endOfGame();
		}else{
			saveTheGame();
			System.out.println("Do not hesitate to continue the game later, " +
					"you can find it in the \"Saved game\" submenu" + "\n");
		}
	}

	/**
	 * This method allows to launch the game in graphical interface
	 * by creating the game's object to the displayMyGrid's class.
	 */
	public void startGui(){
		displayMyGrid = new DisplayMyGrid(this);
		displayMyGrid.displayGrid();
	}

	/**
	 * This method allows to launch the game in graphical interface
	 * by creating the game's object to the displayMyGrid's class.
	 * This method is used in the case of a saved game in gui.
	 * @param player1 The name of the first player.
	 * @param player2 The name of the second player.
	 * @param mode The selected mode.
	 * @param option The boolean which represents the option.
	 * @param pawnsPlayer1 The position of the pawns for the first player.
	 * @param pawnsPlayer2 The position of the pawns for the second player.
	 * @param zenPawn The position of the zen.
	 * @param fileName The name of the file.
	 */
	public static void startGui(String player1, String player2, Mode mode, boolean option,int[][] pawnsPlayer1,
						 int[][] pawnsPlayer2, int[] zenPawn, String fileName){
		displayMyGrid = new DisplayMyGrid(player1,player2,mode,option,pawnsPlayer1,pawnsPlayer2,zenPawn,fileName);
		displayMyGrid.displayGrid();
	}

	/**
	 * This method is the main loop of the game; when the user click
	 * on a JButton in the grid. This method is launch.
	 * @param oldX The x position of the pawn.
	 * @param oldY The y position of the pawn.
	 * @param newX The x position of the new square for the pawn.
	 * @param newY The y position of the new square for the pawn.
	 */
	public void mainGameGui(int oldX, int oldY, int newX, int newY){
		if(this.mode == Mode.HH){
			moveGui(newX,newY,oldX,oldY);
			DisplayMyGrid.gameFrame.dispose();
			Game.displayMyGrid.displayGrid();
			changeCurrent();
			if(isFinish(this.player1) || isFinish(this.player2)){
				DisplayMyGrid.gameFrame.dispose();
				new EndOfGame();
			}
		}else if(this.mode == Mode.HA){
			boolean isFinish = false;

			moveGui(newX,newY,oldX,oldY);
			if(isFinish(this.player1) || isFinish(this.player2)){
				DisplayMyGrid.gameFrame.dispose();
				isFinish = true;
				changeCurrent();
				new EndOfGame();
			}

			if(!isFinish){
				changeCurrent();
				movePawnAutoPlayer();
				DisplayMyGrid.gameFrame.dispose(); //update the frame
				Game.displayMyGrid.displayGrid(); //Update the frame
				if(isFinish(this.player1) || isFinish(this.player2)){
					DisplayMyGrid.gameFrame.dispose();
					new EndOfGame();
				}else{
					changeCurrent();
				}
			}
		}
	}

	/**
	 * This method allows to move a pawn and display a JOptionPane
	 * to say if the movement cause the capture of a pawn.
	 * @param oldX The x position of the pawn.
	 * @param oldY The y position of the pawn.
	 * @param newX The x position of the new square for the pawn.
	 * @param newY The y position of the new square for the pawn.
	 */
	private void moveGui(int newX,int newY,int oldX,int oldY){

		EnemyPawnCase possible = authorizeMovement(oldX,oldY,newX,newY);

		if(possible == EnemyPawnCase.CAPTURE){
			captureThePawn(newX,newY);
			if((this.mode != Mode.HA && this.currentPlayer != this.player2) || this.mode == Mode.HH) {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,"You captured an enemy pawn !");
			}else if(this.mode == Mode.HA && this.currentPlayer == this.player1){
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,"You captured an enemy pawn !");
			}
		}
		move(newX,newY,oldX,oldY);
	}

	/**
	 * This method allows to write in a file the current game.
	 * We put every attributes in a Arraylist and send it to a
	 * method which can write in a file. In mode HH, I put the
	 * next current player at first to respect the order of the
	 * players. But this is not possible in HA mode.
	 */
	public void saveTheGame(){
		ArrayList<String> toWrite = new ArrayList<String>();
		toWrite.add(String.valueOf(this.mode));
		toWrite.add(String.valueOf(this.option));
		toWrite.add(this.zenPawn.getSquare().getX()+":"+this.zenPawn.getSquare().getY()+":");
		//Write at first the future current player
		if(this.mode == Mode.HH){
			if(this.currentPlayer == this.player1){
				toWrite.add(this.player1.getName());
				for(Pawn p : this.player1.getPawnsPlayer()){
					if(p.getSquare().getX() != -1 && p.getSquare().getY() != -1){
						toWrite.add(p.getSquare().getX()+":"+p.getSquare().getY()+":");
					}
				}
			}else if(this.currentPlayer == this.player2){
				toWrite.add(this.player2.getName());
				for(Pawn p : this.player2.getPawnsPlayer()){
					if(p.getSquare().getX() != -1 && p.getSquare().getY() != -1){
						toWrite.add(p.getSquare().getX()+":"+p.getSquare().getY()+":");
					}
				}
			}

			toWrite.add("end:");

			if(this.currentPlayer == this.player1){
				toWrite.add(this.player2.getName());
				for(Pawn p : this.player2.getPawnsPlayer()){
					if(p.getSquare().getX() != -1 && p.getSquare().getY() != -1){
						toWrite.add(p.getSquare().getX()+":"+p.getSquare().getY()+":");
					}
				}
			}else if(this.currentPlayer == this.player2){
				toWrite.add(this.player1.getName());
				for(Pawn p : this.player1.getPawnsPlayer()){
					if(p.getSquare().getX() != -1 && p.getSquare().getY() != -1){
						toWrite.add(p.getSquare().getX()+":"+p.getSquare().getY()+":");
					}
				}
			}
			toWrite.add("end:");
		}else{
			toWrite.add(this.player1.getName());
			for(Pawn p : this.player1.getPawnsPlayer()){
				if(p.getSquare().getX() != -1 && p.getSquare().getY() != -1){
					toWrite.add(p.getSquare().getX()+":"+p.getSquare().getY()+":");
				}
			}
			toWrite.add("end:");
			toWrite.add(this.player2.getName());
			for(Pawn p : this.player2.getPawnsPlayer()){
				if(p.getSquare().getX() != -1 && p.getSquare().getY() != -1){
					toWrite.add(p.getSquare().getX()+":"+p.getSquare().getY()+":");
				}
			}
			toWrite.add("end:");
		}

		//Creation of the file
		if(filename != null){
			RWFile.writeFile(toWrite,dataPath+this.filename);
		}else{
			int i = 0;
			String fileName = dataPath+"saved_game"+i+".txt";
			while (RWFile.isFile(fileName)){
				i++;
				fileName = dataPath+"saved_game"+i+".txt";
			}
			System.out.println(fileName);
			RWFile.createFile(fileName);
			RWFile.writeFile(toWrite,fileName);
		}
	}

	/**
	 * This method is able to ask to the user if he
	 * wants to continue the game of he want to stop.
	 * @return A boolean which represents the choice
	 * 		   of the user.
	 */
	private boolean quit(){
		boolean ret = false;
		Scanner sc = new Scanner(System.in);

		System.out.println("\nPress a key on your keyboard to continue or write \"Exit\" to leave the game");
		String str = sc.nextLine();

		if(str.equalsIgnoreCase("Exit")){
			ret = true;
		}

		return ret;
	}

	/**
	 * This method allows to create a copy of the game board.
	 * So, this copy could modify without problem for the game.
	 * @return A two dimension array which is a copy of the current
	 * 		   game board.
	 */
	private Square[][] createCopy(){
		Square[][] ret	= new Square[11][11];
		for(int y = 0; y< 11; y++){
			for(int x = 0; x< 11; x++){
				ret[y][x]=new Square(x,y);
				if(!this.theGrid.getSquare(y,x).isFree()){
					ret[y][x].setIsFree(false);
				}
			}
		}
		return ret;
	}

	/**
	 * This method allows to print the position of each square and puts a different
	 * color for the square which has a pawn on it.
	 * @param board The board that we must print.
	 */
	public void displayBoard(Square[][] board){
		for(Square[] squares : board){
			for(Square s : squares){
				if(!s.isFree()){
					System.out.print("("+s.getX()+" ; "+s.getY()+")"+ "\t|\t");
				}else{
					System.out.print("("+s.getX()+" ; "+s.getY()+")" + "\t|\t");
				}
			}
			System.out.println(" ");
		}
	}

	/**
	 * This method allows to know if the game is finish or not.
	 * It asks to others methods the number of pawn on the board
	 * and the number of a random chain if the numbers are the same
	 * it means that the game is the finish.
	 * @param player The player where we see if he has finish.
	 * @return True : if the game is finish, false : else.
	 */
	boolean isFinish(Player player){
		boolean ret = false;
		boolean find = false;
		int indexPawn = 0;

		while(!find && indexPawn < player.getPawnsPlayer().length){
			if(player.getPawnsPlayer()[indexPawn].getSquare().getX() != -1 &&
					player.getPawnsPlayer()[indexPawn].getSquare().getY() != -1){
				find = true;
			}else{
				indexPawn++;
			}
		}

		if(countTheNumberPawnForAColor(player) == isAFinalChainRec(player.getPawnsPlayer()[indexPawn],
				0,createCopy(),player.getColor())){
			ret = true;
		}else{
			ret = false;
		}

		return ret;
	}

	/**
	 * This method allows to count the number of pawn for one player.
	 * @param player The player which you want to know the number of pawn on the board.
	 * @return The number of pawn for the player on the board.
	 */
	private int countTheNumberPawnForAColor(Player player){
		int ret = 0;

		for(Pawn p : player.getPawnsPlayer()){
			if(p.getSquare().getX() != -1 && p.getSquare().getY() != -1){
				ret++;
			}
		}

		if(this.zenPawn.getSquare().getX() != -1 && this.zenPawn.getSquare().getY() != -1){
			ret++;
		}

		return ret;
	}

	/**
	 * This method allows to calculate the number of pawn of the chain of a color.
	 * @param pawn The pawn where we will see around if there are others pawns.
	 * @param nbPawn The number of pawn in the chain
	 * @param pawnColor The color of the pawns which we count the number of pawn in the chain.
	 *                  I need to use it because I can't use the color of the pawn to deduce
	 *                  the color of the chain that we search because there is the pawn which
	 *                  has no color/ is a special pawn.
	 * @param copyTheGrid The game board which is a copy because this copy could modify without
	 *                    problem for the game.
	 * @return The number of pawn in the chain.
	 */
	private int isAFinalChainRec(Pawn pawn, int nbPawn, Square[][] copyTheGrid, Color pawnColor){
		int theNumberOfPawn = nbPawn;
		int x = pawn.getSquare().getX();
		int y = pawn.getSquare().getY();
		ArrayList<Pawn> mark = new ArrayList<Pawn>();
		boolean allFound = false;

		while(!allFound){
			if(x == 0 && y == 0){
				if(!copyTheGrid[x+1][y].isFree()){

					if((pawn.getSquare().getX()+1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y).whichPawn(x+1,y).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y)+")");
						mark.add(pawnBelongTo(x+1,y).whichPawn(x+1,y));
					}
					copyTheGrid[x+1][y].setIsFree(true);

				}else if(!copyTheGrid[x][y+1].isFree()){

					if(pawn.getSquare().getX() == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x)+";"+(y+1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x,y+1).whichPawn(x,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x,y+1).whichPawn(x,y+1));
					}
					copyTheGrid[x][y+1].setIsFree(true);

					//System.out.println("Pion(0;1)");
				}else if(!copyTheGrid[x+1][y+1].isFree()){

					if((pawn.getSquare().getX()+1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y+1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y+1).whichPawn(x+1,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x+1,y+1).whichPawn(x+1,y+1));
					}
					copyTheGrid[x+1][y+1].setIsFree(true);

				}else{
					//System.out.println("Pas de pion autour");
					allFound = true;
				}
			}else if(x == 10 && y == 0){
				if(!copyTheGrid[x-1][y].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y).whichPawn(x-1,y).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y)+")");
						mark.add(pawnBelongTo(x-1,y).whichPawn(x-1,y));
					}
					copyTheGrid[x-1][y].setIsFree(true);

				}else if(!copyTheGrid[x][y+1].isFree()){

					if(pawn.getSquare().getX() == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x)+";"+(y+1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x,y+1).whichPawn(x,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x,y+1).whichPawn(x,y+1));
					}
					copyTheGrid[x][y+1].setIsFree(true);

				}else if(!copyTheGrid[x-1][y+1].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y+1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y+1).whichPawn(x-1,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x-1,y+1).whichPawn(x-1,y+1));
					}
					copyTheGrid[x-1][y+1].setIsFree(true);

				}else{
					//System.out.println("Pas de pion autour");
					allFound = true;
				}
			}else if(x == 0 && y == 10){
				if(!copyTheGrid[x+1][y].isFree()){

					if((pawn.getSquare().getX()+1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y).whichPawn(x+1,y).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y)+")");
						mark.add(pawnBelongTo(x+1,y).whichPawn(x+1,y));
					}
					copyTheGrid[x+1][y].setIsFree(true);

				}else if(!copyTheGrid[x][y-1].isFree()){

					if(pawn.getSquare().getX() == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x,y-1).whichPawn(x,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x,y-1).whichPawn(x,y-1));
					}
					copyTheGrid[x][y-1].setIsFree(true);

				}else if(!copyTheGrid[x+1][y-1].isFree()){

					if((pawn.getSquare().getX()+1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y-1).whichPawn(x+1,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x+1,y-1).whichPawn(x+1,y-1));
					}
					copyTheGrid[x+1][y-1].setIsFree(true);

				}else{
					//System.out.println("Pas de pion autour");
					allFound = true;
				}
			}else if(x == 10 && y == 10){
				if(!copyTheGrid[x-1][y].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y).whichPawn(x-1,y).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y)+")");
						mark.add(pawnBelongTo(x-1,y).whichPawn(x-1,y));
					}
					copyTheGrid[x-1][y].setIsFree(true);

				}else if(!copyTheGrid[x][y-1].isFree()){

					if(pawn.getSquare().getX() == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x,y-1).whichPawn(x,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x,y-1).whichPawn(x,y-1));
					}
					copyTheGrid[x][y-1].setIsFree(true);

				}else if(!copyTheGrid[x-1][y-1].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y-1).whichPawn(x-1,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x-1,y-1).whichPawn(x-1,y-1));
					}
					copyTheGrid[x-1][y-1].setIsFree(true);

				}else{
					//System.out.println("Pas de pion autour");
					allFound = true;
				}
			}else if(y == 0){
				if(!copyTheGrid[x-1][y].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y).whichPawn(x-1,y).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y)+")");
						mark.add(pawnBelongTo(x-1,y).whichPawn(x-1,y));
					}
					copyTheGrid[x-1][y].setIsFree(true);

				}else if(!copyTheGrid[x+1][y].isFree()){

					if((pawn.getSquare().getX()+1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y).whichPawn(x+1,y).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y)+")");
						mark.add(pawnBelongTo(x+1,y).whichPawn(x+1,y));
					}
					copyTheGrid[x+1][y].setIsFree(true);

				}else if(!copyTheGrid[x][y+1].isFree()){

					if(pawn.getSquare().getX() == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x)+";"+(y+1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x,y+1).whichPawn(x,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x,y+1).whichPawn(x,y+1));
					}
					copyTheGrid[x][y+1].setIsFree(true);

				}else if(!copyTheGrid[x-1][y+1].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y+1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y+1).whichPawn(x-1,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x-1,y+1).whichPawn(x-1,y+1));
					}
					copyTheGrid[x-1][y+1].setIsFree(true);

				}else if(!copyTheGrid[x+1][y+1].isFree()){

					if((pawn.getSquare().getX()+1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y+1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y+1).whichPawn(x+1,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x+1,y+1).whichPawn(x+1,y+1));
					}
					copyTheGrid[x+1][y+1].setIsFree(true);

				}else{
					//System.out.println("Pas de pion autour");
					allFound = true;
				}
			}else if(y == 10){
				if(!copyTheGrid[x-1][y].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y).whichPawn(x-1,y).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y)+")");
						mark.add(pawnBelongTo(x-1,y).whichPawn(x-1,y));
					}
					copyTheGrid[x-1][y].setIsFree(true);

				}else if(!copyTheGrid[x+1][y].isFree()){

					if((pawn.getSquare().getX()+1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y).whichPawn(x+1,y).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y)+")");
						mark.add(pawnBelongTo(x+1,y).whichPawn(x+1,y));
					}
					copyTheGrid[x+1][y].setIsFree(true);

				}else if(!copyTheGrid[x][y-1].isFree()){

					if(pawn.getSquare().getX() == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x,y-1).whichPawn(x,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x,y-1).whichPawn(x,y-1));
					}
					copyTheGrid[x][y-1].setIsFree(true);

				}else if(!copyTheGrid[x-1][y-1].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y-1).whichPawn(x-1,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x-1,y-1).whichPawn(x-1,y-1));
					}
					copyTheGrid[x-1][y-1].setIsFree(true);

				}else if(!copyTheGrid[x+1][y-1].isFree()){

					if((pawn.getSquare().getX()+1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y-1).whichPawn(x+1,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x+1,y-1).whichPawn(x+1,y-1));
					}
					copyTheGrid[x+1][y-1].setIsFree(true);

				}else{
					//System.out.println("Pas de pion autour");
					allFound = true;
				}
			}else if(x == 0){
				if(!copyTheGrid[x][y-1].isFree()){

					if(pawn.getSquare().getX() == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x,y-1).whichPawn(x,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x,y-1).whichPawn(x,y-1));
					}
					copyTheGrid[x][y-1].setIsFree(true);

				}else if(!copyTheGrid[x][y+1].isFree()){

					if(pawn.getSquare().getX() == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x)+";"+(y+1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x,y+1).whichPawn(x,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x,y+1).whichPawn(x,y+1));
					}
					copyTheGrid[x][y+1].setIsFree(true);

				}else if(!copyTheGrid[x+1][y].isFree()){

					if((pawn.getSquare().getX()+1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y).whichPawn(x+1,y).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y)+")");
						mark.add(pawnBelongTo(x+1,y).whichPawn(x+1,y));
					}
					copyTheGrid[x+1][y].setIsFree(true);

				}else if(!copyTheGrid[x+1][y-1].isFree()){

					if((pawn.getSquare().getX()+1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y-1).whichPawn(x+1,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x+1,y-1).whichPawn(x+1,y-1));
					}
					copyTheGrid[x+1][y-1].setIsFree(true);

				}else if(!copyTheGrid[x+1][y+1].isFree()){

					if((pawn.getSquare().getX()+1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y+1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y+1).whichPawn(x+1,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x+1,y+1).whichPawn(x+1,y+1));
					}
					copyTheGrid[x+1][y+1].setIsFree(true);

				}else{
					//System.out.println("Pas de pion autour");
					allFound = true;
				}
			}else if(x == 10){
				if(!copyTheGrid[x][y-1].isFree()){

					if(pawn.getSquare().getX() == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x,y-1).whichPawn(x,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x,y-1).whichPawn(x,y-1));
					}
					copyTheGrid[x][y-1].setIsFree(true);

				}else if(!copyTheGrid[x][y+1].isFree()){

					if(pawn.getSquare().getX() == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x)+";"+(y+1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x,y+1).whichPawn(x,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x,y+1).whichPawn(x,y+1));
					}
					copyTheGrid[x][y+1].setIsFree(true);

				}else if(!copyTheGrid[x-1][y].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y).whichPawn(x-1,y).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y)+")");
						mark.add(pawnBelongTo(x-1,y).whichPawn(x-1,y));
					}
					copyTheGrid[x-1][y].setIsFree(true);

				}else if(!copyTheGrid[x-1][y-1].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y-1).whichPawn(x-1,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x-1,y-1).whichPawn(x-1,y-1));
					}
					copyTheGrid[x-1][y-1].setIsFree(true);

				}else if(!copyTheGrid[x-1][y+1].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y+1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y+1).whichPawn(x-1,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x-1,y+1).whichPawn(x-1,y+1));
					}
					copyTheGrid[x-1][y+1].setIsFree(true);

				}else{
					//System.out.println("Pas de pion autour");
					allFound = true;
				}
			}else{
				if(!copyTheGrid[x][y-1].isFree()){

					if(pawn.getSquare().getX() == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x,y-1).whichPawn(x,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x,y-1).whichPawn(x,y-1));
					}
					copyTheGrid[x][y-1].setIsFree(true);

				}else if(!copyTheGrid[x][y+1].isFree()){

					if(pawn.getSquare().getX() == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x)+";"+(y+1)+")");
						mark.add(this.zenPawn);
						copyTheGrid[x][y+1].setIsFree(true);
					}else if(pawnBelongTo(x,y+1).whichPawn(x,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x,y+1).whichPawn(x,y+1));
					}
					copyTheGrid[x][y+1].setIsFree(true);

				}else if(!copyTheGrid[x-1][y].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y).whichPawn(x-1,y).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y)+")");
						mark.add(pawnBelongTo(x-1,y).whichPawn(x-1,y));
					}
					copyTheGrid[x-1][y].setIsFree(true);

				}else if(!copyTheGrid[x+1][y].isFree()){

					if((x+1) == this.zenPawn.getSquare().getX() &&
							(y) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y).whichPawn(x+1,y).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y)+")");
						mark.add(pawnBelongTo(x+1,y).whichPawn(x+1,y));
					}
					copyTheGrid[x+1][y].setIsFree(true);

				}else if(!copyTheGrid[x-1][y-1].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y-1).whichPawn(x-1,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x-1,y-1).whichPawn(x-1,y-1));
					}
					copyTheGrid[x-1][y-1].setIsFree(true);

				}else if(!copyTheGrid[x+1][y+1].isFree()){

					if((pawn.getSquare().getX()+1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y+1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y+1).whichPawn(x+1,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x+1,y+1).whichPawn(x+1,y+1));
					}
					copyTheGrid[x+1][y+1].setIsFree(true);

				}else if(!copyTheGrid[x-1][y+1].isFree()){

					if((pawn.getSquare().getX()-1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()+1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x-1)+";"+(y+1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x-1,y+1).whichPawn(x-1,y+1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x-1)+";"+(y+1)+")");
						mark.add(pawnBelongTo(x-1,y+1).whichPawn(x-1,y+1));
					}
					copyTheGrid[x-1][y+1].setIsFree(true);

				}else if(!copyTheGrid[x+1][y-1].isFree()){

					if((pawn.getSquare().getX()+1) == this.zenPawn.getSquare().getX() &&
							(pawn.getSquare().getY()-1) == this.zenPawn.getSquare().getY()){
						//System.out.println("Pion ("+(x+1)+";"+(y-1)+")");
						mark.add(this.zenPawn);
					}else if(pawnBelongTo(x+1,y-1).whichPawn(x+1,y-1).getColor() == pawnColor){
						//System.out.println("Pion ("+(x+1)+";"+(y-1)+")");
						mark.add(pawnBelongTo(x+1,y-1).whichPawn(x+1,y-1));
					}
					copyTheGrid[x+1][y-1].setIsFree(true);

				}else{
					//System.out.println("Pas de pion autour");
					allFound = true;
				}
			}
		}

		for(Pawn p : mark){
			theNumberOfPawn = isAFinalChainRec(p,theNumberOfPawn+1,copyTheGrid,pawnColor);
		}

		return theNumberOfPawn;
	}

	/**
	 * This method allows to the auto player to move a pawn.
	 */
	private void movePawnAutoPlayer(){

		int[] positionPawnMove = this.currentPlayer.pawnToMove();
		Square[] possibleSquare = authorizeSquare(positionPawnMove[0],positionPawnMove[1]);
		Square finalPosition = this.currentPlayer.selectedPosition(possibleSquare);

		EnemyPawnCase possible = authorizeMovement(positionPawnMove[0],positionPawnMove[1],finalPosition.getX(),finalPosition.getY());

		if(possible == EnemyPawnCase.CAPTURE){
			captureThePawn(finalPosition.getX(),finalPosition.getY());
			System.out.println("The bot captured one of your pawn !");
		}

		move(finalPosition.getX(),finalPosition.getY(),positionPawnMove[0],positionPawnMove[1]);

	}

	/**
	 * This method allows to ask to the user the position of the pawn
	 * that he want to move and the final position of the selected pawn.
	 * This method works with the option "see the possible movement of a
	 * pawn". Indeed, this method will says the possible square to the user.
	 */
	private void moveChosenPawnWithOption(){

		if(this.currentPlayer == this.player1){
			System.out.println(this.currentPlayer.getName()+" plays with the blue cross\n");
		}else{
			System.out.println(this.currentPlayer.getName()+" plays with the green circle\n");
		}

		System.out.println("Which pawn do you want to move ?");
		int[] positionPawnMove = this.currentPlayer.pawnToMove();
		while (pawnBelongTo(positionPawnMove[0],positionPawnMove[1]) != this.currentPlayer &&
				(zenPawn.getSquare().getX() != positionPawnMove[0] ||
						zenPawn.getSquare().getY() != positionPawnMove[1])){
			System.out.println("This is not your pawn, please choose another pawn !");
			positionPawnMove = this.currentPlayer.pawnToMove();
		}
		Square[] possibleSquare = authorizeSquare(positionPawnMove[0],positionPawnMove[1]);
		System.out.println("You can see the possible square from this pawn : \n");
		for(Square s : possibleSquare){
			System.out.println("("+s.getX()+" ; "+s.getY()+")");
		}
		System.out.println("\nPlease, enter the final position of the selected pawn ");
		int[] finalPosition = this.currentPlayer.selectedPosition();
		EnemyPawnCase possible = authorizeMovement(positionPawnMove[0],positionPawnMove[1],finalPosition[0],finalPosition[1]);
		while(possible != EnemyPawnCase.CAPTURE && possible != EnemyPawnCase.ALLOW){
			System.out.println("This square is forbidden for this pawn, please choose another " +
					"square !");
			finalPosition = this.currentPlayer.selectedPosition();
			possible = authorizeMovement(positionPawnMove[0],positionPawnMove[1],finalPosition[0],finalPosition[1]);
		}

		if(possible == EnemyPawnCase.CAPTURE){
			captureThePawn(finalPosition[0],finalPosition[1]);
			System.out.println("You captured an enemy pawn !");
		}

		move(finalPosition[0],finalPosition[1],positionPawnMove[0],positionPawnMove[1]);
	}

	/**
	 * This method allows to ask to the user the position of the pawn
	 * that he want to move and the final position of the selected pawn.
	 * This method works without the option "see the possible movement of a
	 * pawn".
	 */
	private void moveChosenPawnWithoutOption(){

		if(this.currentPlayer == this.player1){
			System.out.println(this.currentPlayer.getName()+" plays with the blue cross\n");
		}else{
			System.out.println(this.currentPlayer.getName()+" plays with the green circle\n");
		}

		System.out.println("Which pawn do you want to move ?");
		int[] positionPawnMove = this.currentPlayer.pawnToMove();
		while (pawnBelongTo(positionPawnMove[0],positionPawnMove[1]) != this.currentPlayer &&
				(zenPawn.getSquare().getX() != positionPawnMove[0] ||
						zenPawn.getSquare().getY() != positionPawnMove[1])){
			System.out.println("This is not your pawn, please choose another pawn !");
			positionPawnMove = this.currentPlayer.pawnToMove();
		}
		System.out.println("\nPlease, enter the final position of the selected pawn");
		int[] finalPosition = this.currentPlayer.selectedPosition();
		EnemyPawnCase possible = authorizeMovement(positionPawnMove[0],positionPawnMove[1],finalPosition[0],finalPosition[1]);
		while(possible != EnemyPawnCase.CAPTURE && possible != EnemyPawnCase.ALLOW){
			System.out.println("This square is forbidden for this pawn, please choose another " +
					"square !");
			finalPosition = this.currentPlayer.selectedPosition();
			possible = authorizeMovement(positionPawnMove[0],positionPawnMove[1],finalPosition[0],finalPosition[1]);
		}

		if(possible == EnemyPawnCase.CAPTURE){
			captureThePawn(finalPosition[0],finalPosition[1]);
			System.out.println("You captured an enemy pawn !");
		}

		move(finalPosition[0],finalPosition[1],positionPawnMove[0],positionPawnMove[1]);
	}

	/**
	 * This method counts the number of pawn on a line.
	 * [0] = X
	 * [1] = Y
	 * [2] = XY to the top (y = 0)
	 * [3] = XY to the bottom (y = 10)
	 * @return The number of pawn on a line.
	 */
	private int[] numberOfPawn(int xPositionPawn, int yPositionPawn){
		int ret[] = new int[4];
		ret[0] = 0;
		ret[1] = 0;
		ret[2] = 0;
		ret[3] = 0;
		int x = 0;
		int y = 0;

		//X
		y = yPositionPawn;

		for(x = 0; x<11 ; x++){
			if(!this.theGrid.getMyGrid()[x][y].isFree()){
				ret[0]++;
			}
		}

		//Y
		x = xPositionPawn;

		for(y = 0; y<11; y++){
			if(!this.theGrid.getMyGrid()[x][y].isFree()){
				ret[1]++;
			}
		}

		y = yPositionPawn;
		x = xPositionPawn;
		while(x > 0 && y < 10){
			x--;
			y++;
		}

		while(x <= 10 && y >= 0){
			if(!this.theGrid.getMyGrid()[x][y].isFree()){
				ret[2]++;
			}
			x++;
			y--;
		}

		//diagonal to the bottom
		y = yPositionPawn;
		x = xPositionPawn;
		while(x > 0 && y > 0){
			y--;
			x--;
		}

		while(y <= 10 && x <= 10){
			if(!this.theGrid.getMyGrid()[x][y].isFree()){
				ret[3]++;
			}
			x++;
			y++;
		}

		return ret;
	}

	/**
	 * This method allows to know if the movement is possible with the others
	 * pawns. It could be our pawn, the enemy pawn or the zen pawn.
	 * @param oldX The x position of the pawn.
	 * @param oldY The y position of the pawn.
	 * @param newX The new x position of the pawn.
	 * @param newY The new y position of the pawn.
	 * @return ALLOW : if the movement is possible thought the others pawn.
	 * 		   FORBIDDEN : if the movement isn't possible due to an enemy pawn.
	 * 		   CAPTURE : if the movement result in the capture of the enemy pawn
	 * 		   or of the zen pawn.
	 */
	private EnemyPawnCase enemyPawnMove(int oldX, int oldY, int newX, int newY){
		EnemyPawnCase ret = null;
		EnemyPawnCase isPawnNewSquare = null;
		EnemyPawnCase isThereAPawn = null;

		boolean busy = false;
		int numberSquareX = oldX - newX;
		int numberSquareY = oldY - newY;
		int i = 1;

		if(!this.theGrid.getSquare(newX,newY).isFree()){
			if(newX == this.zenPawn.getSquare().getX() && newY == this.zenPawn.getSquare().getY()){
				if(newX == oldX && newY == oldY){
					isPawnNewSquare = EnemyPawnCase.FORBIDDEN;
				}else{
					isPawnNewSquare = EnemyPawnCase.CAPTURE;
				}
			}else if(this.currentPlayer != this.player1){
				if(this.player1.whichPawn(newX,newY) != null){
					isPawnNewSquare = EnemyPawnCase.CAPTURE;
				}else{
					isPawnNewSquare = EnemyPawnCase.FORBIDDEN;
				}
			}else if(this.currentPlayer != this.player2){
				if(this.player2.whichPawn(newX,newY) != null){
					isPawnNewSquare = EnemyPawnCase.CAPTURE;
				}else{
					isPawnNewSquare = EnemyPawnCase.FORBIDDEN;
				}
			}
		}else{
			isPawnNewSquare = EnemyPawnCase.ALLOW;
		}


		if(isPawnNewSquare != EnemyPawnCase.FORBIDDEN){
			if(numberSquareX == 0){
				if(newY < oldY){
					while(!busy && newY+i<oldY){
						//Check if the square is free
						if(!this.theGrid.getSquare(newX,newY+i).isFree()){
							//Check if it's the zen
							if(newX == this.zenPawn.getSquare().getX() && newY+i == this.zenPawn.getSquare().getY()){
								isThereAPawn = EnemyPawnCase.ALLOW;
								//Check if this a allied pawn
							}else if(this.currentPlayer != this.player1){
								if(this.player1.whichPawn(newX,newY+i) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}else if(this.player2 != this.currentPlayer){
								if(this.player2.whichPawn(newX,newY+i) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}
						}else{
							isThereAPawn = EnemyPawnCase.ALLOW;
						}
						i++;
					}
				}else if(newY > oldY){
					while(!busy && newY-i>oldY){
						//Check if the square is free
						if(!this.theGrid.getSquare(newX,newY-i).isFree()){
							//Check if it's the zen
							if(newX == this.zenPawn.getSquare().getX() && newY-i == this.zenPawn.getSquare().getY()){
								isThereAPawn = EnemyPawnCase.ALLOW;
								//Check if this a allied pawn
							}else if(this.currentPlayer != this.player1){
								if(this.player1.whichPawn(newX,newY-i) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}else if(this.player2 != this.currentPlayer){
								if(this.player2.whichPawn(newX,newY-i) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}
						}else{
							isThereAPawn = EnemyPawnCase.ALLOW;
						}
						i++;
					}
				}
			}else if(numberSquareY==0){
				if(newX < oldX){
					while(!busy && newX+i<oldX){
						if(!this.theGrid.getSquare(newX+i,newY).isFree()){
							//Check if it's the zen
							if(newX+i == this.zenPawn.getSquare().getX() && newY == this.zenPawn.getSquare().getY()){
								isThereAPawn = EnemyPawnCase.ALLOW;
								//Check if it's a allied pawn
							}else if(this.currentPlayer != this.player1){
								if(this.player1.whichPawn(newX+i,newY) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}else if(this.currentPlayer != this.player2){
								if(this.player2.whichPawn(newX+i,newY) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}
						}else{
							isThereAPawn = EnemyPawnCase.ALLOW;
						}
						i++;
					}
				}else if(newX > oldX){
					while(!busy && newX-i>oldX){
						if(!this.theGrid.getSquare(newX-i,newY).isFree()){
							//Check if it's the zen
							if(newX-i == this.zenPawn.getSquare().getX() && newY == this.zenPawn.getSquare().getY()){
								isThereAPawn = EnemyPawnCase.ALLOW;
								//Check if it's a allied pawn
							}else if(this.currentPlayer != this.player1){
								if(this.player1.whichPawn(newX-i,newY) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}else if(this.currentPlayer != this.player2){
								if(this.player2.whichPawn(newX-i,newY) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}
						}else{
							isThereAPawn = EnemyPawnCase.ALLOW;
						}
						i++;
					}
				}
			}else if(numberSquareX != 0 && numberSquareY !=0){
				if(newX > oldX && newY > oldY){
					while(!busy && (newX-i>oldX || newY-i>oldY)){
						if(!this.theGrid.getSquare(newX-i,newY-i).isFree()){
							//Check if it's the zen
							if(newX-i == this.zenPawn.getSquare().getX() && newY-i == this.zenPawn.getSquare().getY()){
								isThereAPawn = EnemyPawnCase.ALLOW;
								//check if it's a allied pawn
							}else if(this.currentPlayer != this.player1){
								if(this.player1.whichPawn(newX-i,newY-i) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}else if(this.currentPlayer != this.player2){
								if(this.player2.whichPawn(newX-i,newY-i) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}
						}else{
							isThereAPawn = EnemyPawnCase.ALLOW;
						}
						i++;
					}
				}else if(newX < oldX && newY < oldY){
					while(!busy && (newX+i < oldX || newY+i < oldY)){
						if(!this.theGrid.getSquare(newX+i,newY+i).isFree()){
							//check if it's the zen
							if(newX+i == this.zenPawn.getSquare().getX() && newY+i == this.zenPawn.getSquare().getY()){
								isThereAPawn = EnemyPawnCase.ALLOW;
								//check if it's a allied pawn
							}else if(this.currentPlayer != this.player1){
								if(this.player1.whichPawn(newX+i,newY+i) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}else if(this.currentPlayer != this.player2){
								if(this.player2.whichPawn(newX+i,newY+i) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}
						}else{
							isThereAPawn = EnemyPawnCase.ALLOW;
						}
						i++;
					}
				}else if(newX>oldX && newY<oldY){
					while(!busy && (newX-i > oldX || newY+i < oldY)){
						if(!this.theGrid.getSquare(newX-i,newY+i).isFree()){
							//check if it's the zen
							if(newX-i == this.zenPawn.getSquare().getX() && newY+i == this.zenPawn.getSquare().getY()){
								isThereAPawn = EnemyPawnCase.ALLOW;
								//check if it's a allied pawn
							}else if(this.currentPlayer != this.player1){
								if(this.player1.whichPawn(newX-i,newY+i) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}else if(this.currentPlayer != this.player2){
								if(this.player2.whichPawn(newX-i,newY+i) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}
						}else{
							isThereAPawn = EnemyPawnCase.ALLOW;
						}
						i++;
					}
				}else if(newX < oldX && newY > oldY){
					while(!busy && (newX+i < oldX || newY-i > oldY)) {
						if(!this.theGrid.getSquare(newX+i,newY-i).isFree()){
							//check if it's the zen
							if(newX+i == this.zenPawn.getSquare().getX() && newY-i == this.zenPawn.getSquare().getY()){
								isThereAPawn = EnemyPawnCase.ALLOW;
								//check if it's a allied pawn
							}else if(this.currentPlayer != this.player1){
								if(this.player1.whichPawn(newX+i,newY-i) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}else if(this.currentPlayer != this.player2){
								if(this.player2.whichPawn(newX+i,newY-i) != null){
									isThereAPawn = EnemyPawnCase.FORBIDDEN;
									busy = true;
								}else{
									isThereAPawn = EnemyPawnCase.ALLOW;
								}
							}
						}else{
							isThereAPawn = EnemyPawnCase.ALLOW;
						}
						i++;
					}
				}
			}
		}

		//Case : The length of the movement is equal to one
		if((numberSquareX >= -1 && numberSquareX <= 1) && (numberSquareY >= -1 && numberSquareY <= 1)){
			isThereAPawn = EnemyPawnCase.ALLOW;
		}

		if(isPawnNewSquare == EnemyPawnCase.FORBIDDEN || isThereAPawn == EnemyPawnCase.FORBIDDEN){
			ret = EnemyPawnCase.FORBIDDEN;
		}else if(isPawnNewSquare == EnemyPawnCase.CAPTURE && isThereAPawn == EnemyPawnCase.ALLOW){
			ret = EnemyPawnCase.CAPTURE;
		}else if(isPawnNewSquare == EnemyPawnCase.ALLOW && isThereAPawn == EnemyPawnCase.ALLOW){
			ret = EnemyPawnCase.ALLOW;
		}


		return ret;
	}

	/**
	 * This method finds every possibles square from an original pawn.
	 * @param oldX The x position of the pawn.
	 * @param oldY The y position of the pawn.
	 * @return The list of the authorize square.
	 */
	public Square[] authorizeSquare(int oldX,int oldY){

		int[] nbPawnLine = numberOfPawn(oldX, oldY);
		Square[] thePossibleSquare = new Square[nbPawnLine.length*2];
		/*
		 * [0] = X
		 * [1] = Y
		 * [2] = XY to the top (y = 0)
		 * [3] = XY to the bottom (y = 10)
		 */

		/*
		* X
		 */
		if(oldX-nbPawnLine[0]<0){
			thePossibleSquare[0] = null;
		}else{
			thePossibleSquare[0] = this.theGrid.getSquare(oldX-nbPawnLine[0],oldY);
		}
		if(oldX+nbPawnLine[0]>10){
			thePossibleSquare[1] = null;
		}else{
			thePossibleSquare[1] = this.theGrid.getSquare(oldX+nbPawnLine[0],oldY);
		}

		/*
		* Y
		 */
		if(oldY-nbPawnLine[1]<0){
			thePossibleSquare[2] = null;
		}else{
			thePossibleSquare[2] = this.theGrid.getSquare(oldX,oldY-nbPawnLine[1]);
		}
		if(oldY+nbPawnLine[1]>10){
			thePossibleSquare[3] = null;
		}else{
			thePossibleSquare[3] = this.theGrid.getSquare(oldX,oldY+nbPawnLine[1]);
		}
		/*
		* XY the axe to the top (y = 0)
		 */
		if(oldX-nbPawnLine[2]<0 || oldY+nbPawnLine[2]>10){
			thePossibleSquare[4] = null;
		}else{
			thePossibleSquare[4] = this.theGrid.getSquare(oldX-nbPawnLine[2],oldY+nbPawnLine[2]);
		}
		if(oldX+nbPawnLine[2]>10 || oldY-nbPawnLine[2]<0){
			thePossibleSquare[5] = null;
		}else{
			thePossibleSquare[5] = this.theGrid.getSquare(oldX+nbPawnLine[2],oldY-nbPawnLine[2]);
		}

		/*
		* XY the axe to the bottom (y=10)
		 */
		if(oldX-nbPawnLine[3]<0 || oldY-nbPawnLine[3]<0){
			thePossibleSquare[6] = null;
		}else{
			thePossibleSquare[6] = this.theGrid.getSquare(oldX-nbPawnLine[3],oldY-nbPawnLine[3]);
		}
		if(oldX+nbPawnLine[3]>10 || oldY+nbPawnLine[3]>10){
			thePossibleSquare[7] = null;
		}else{
			thePossibleSquare[7] = this.theGrid.getSquare(oldX+nbPawnLine[3],oldY+nbPawnLine[3]);
		}

		int nbSolution = 0;
		Square[] finalSolution = new Square[nbPawnLine.length*2];
		EnemyPawnCase ok;
		for(Square s : thePossibleSquare){
			if(s != null){
				if((s.getX() >= 0 && s.getY() >= 0) && (s.getY() <= 10 && s.getX() <= 10)){
					ok = enemyPawnMove(oldX,oldY,s.getX(),s.getY());
					if(ok == EnemyPawnCase.ALLOW){
						finalSolution[nbSolution] = s;
						nbSolution++;
					}else if(ok == EnemyPawnCase.CAPTURE){
						finalSolution[nbSolution] = s;
						nbSolution++;
					}
				}
			}
		}

		Square[] ret = new Square[nbSolution];

		int i = 0;
		for(Square s : finalSolution){
			if(s != null){
				ret[i] = s;
				i++;
			}
		}

		return ret;
	}

	/**
	 * This method allows to know if the movement from a certain pawn
	 * to a position is possible. It also says if the movement results
	 * to the capture of an enemy pawn.
	 * @param oldX The x position of the pawn.
	 * @param oldY The y position of the pawn.
	 * @param newX The new x position of the pawn.
	 * @param newY The new y position of the pawn.
	 * @return ALLOW : if the movement is allow.
	 * 		   FORBIDDEN : if the movement is forbidden.
	 * 		   CAPTURE : if the movement results to the capture of one of
	 * 		   the enemy pawn.
	 */
	private EnemyPawnCase authorizeMovement(int oldX, int oldY, int newX, int newY){
		EnemyPawnCase ret = null;
		boolean findInTheArray = false;
		Square[] authorizeSquare = authorizeSquare(oldX,oldY);

		for(Square s : authorizeSquare){
			if(newX == s.getX() && newY == s.getY()){
				findInTheArray = true;
			}
		}

		if(findInTheArray){
			if(!this.theGrid.getSquare(newX,newY).isFree()){
				ret = EnemyPawnCase.CAPTURE;
			}else{
				ret = EnemyPawnCase.ALLOW;
			}
		}else{
			ret = EnemyPawnCase.FORBIDDEN;
		}

		return ret;
	}

	/**
	 * This method creates a description of the game and return it in a String.
	 * @return A String with the description.
	 */
	private String description(){
		String ret = "Good luck for this new game !";
		return ret;
	}

	/**
	 * This method changes the current player.
	 */
	public void changeCurrent() {
		if(this.currentPlayer == this.player1){
			this.currentPlayer = this.player2;
		}else{
			this.currentPlayer = this.player1;
		}
	}

	/**
	 * This method prints in the console the grid with the pawn.
	 */
	public void displayBoard(){
		Pawn thePawn = null;
		System.out.print("   |  ");
		for(int i = 0 ; i<11; i++){
			if(i == 10){
				System.out.print(i+" |  ");
			}else{
				System.out.print(i+"  |  ");
			}
		}
		System.out.println("\n----------------------------------------------------------" +
				"------------");
		for(int y = 0; y< 11; y++){
			for(int x = 0; x< 12; x++){
				if(x == 0){
					if(y==10){
						System.out.print(y+" |  ");
					}else{
						System.out.print(y+"  |  ");
					}
				}else{
					if(this.theGrid.getMyGrid()[x-1][y].isFree()){
						System.out.print("   |  ");
					}else {
						if (this.player1.whichPawn(x - 1, y) != null) {
							thePawn = this.player1.whichPawn(x - 1, y);
							if (thePawn.getColor() == Color.BLACK) {
								System.out.print("X"  + "  |  ");
							} else if (thePawn.getColor() == Color.WHITE) {
								System.out.print("O" + "  |  ");
							}
						} else if (this.player2.whichPawn(x - 1, y) != null) {
							thePawn = this.player2.whichPawn(x - 1, y);
							if (thePawn.getColor() == Color.BLACK) {
								System.out.print("X" + "  |  ");
							} else if (thePawn.getColor() == Color.WHITE) {
								System.out.print("O" + "  |  ");
							}
						} else if (this.zenPawn.getSquare().getX() == x-1 && this.zenPawn.getSquare().getY() == y) {
							System.out.print("Z" +"  |  ");
						} else {
							System.err.print("ERROR");
						}
					}
				}
			}
			System.out.println("\n----------------------------------------------------------" +
					"------------");
		}
	}


	/**
	 * This method allows to print the position of the square and
	 * put a special color the busy square.
	 */
	public void displayBoolean(){
		Pawn thePawn = null;
		System.out.print("\t|\t");
		for(int i = 0 ; i<11; i++){
			System.out.print(i+"\t|\t");
		}
		System.out.println("\n----------------------------------------------------------" +
				"-----------------------------------");
		for(int y = 0; y< 11; y++){
			for(int x = 0; x< 12; x++){
				if(x == 0){
					System.out.print(y+"\t|\t");
				}else{
					if(this.theGrid.getMyGrid()[x-1][y].isFree()){
						System.out.print("\033[33m(" + this.theGrid.getMyGrid()[x-1][y].getX() + " ; " +
								this.theGrid.getMyGrid()[x-1][y].getY() + ")\033[0m" + "\t|\t");

					}else{
						System.out.print("\033[34m(" + this.theGrid.getMyGrid()[x-1][y].getX() + " ; " +
								this.theGrid.getMyGrid()[x-1][y].getY() + ")\033[0m" + "\t|\t");
					}
				}
			}
			System.out.println("\n------------------------------------------------------" +
					"---------------------------------------");
		}
	}

	/**
	 * This method allows to move a pawn.
	 * @param newX The new x position of the pawn.
	 * @param newY The new y position of the pawn.
	 * @param oldX The current x position of the pawn.
	 * @param oldY The  current y position of the pawn.
	 */
	public void move(int newX, int newY,int oldX, int oldY){

		Pawn pawnMove = this.currentPlayer.whichPawn(oldX,oldY);

		if((pawnMove == null) && ((zenPawn.getSquare().getX() != oldX) && (zenPawn.getSquare().getY() != oldY))){
			throw new IllegalArgumentException("ERROR : move : the square is empty");
		}else{
			//The pawn is the zen
			if(this.zenPawn.getSquare().getX() == oldX && this.zenPawn.getSquare().getY() == oldY){
				if(!this.theGrid.getSquare(newX,newY).isFree() && this.currentPlayer.whichPawn(newX,newY) == null){
					captureThePawn(newX,newY);
				}
				this.zenPawn.setSquare(this.theGrid.getSquare(newX,newY));
				this.theGrid.getMyGrid()[oldX][oldY].setFree(true);
				this.theGrid.getMyGrid()[newX][newY].setFree(false);
			}else{
				//The pawn is not the zen but a pawn of one of the player
				Player playerBelongPawn = pawnBelongTo(oldX,oldY);
				if(playerBelongPawn == this.currentPlayer){
					Pawn pawnToMove = this.currentPlayer.whichPawn(oldX,oldY);
					pawnToMove.setSquare(this.theGrid.getSquare(newX,newY));
					this.theGrid.getMyGrid()[oldX][oldY].setFree(true);
					this.theGrid.getMyGrid()[newX][newY].setFree(false);
				}else{
					throw new IllegalArgumentException("ERROR : move : You can't move this pawn, this is not yours !");
				}
			}
		}
	}

	/**
	 * This method allows to know who owns a pawn.
	 * @param x The x position of the pawn.
	 * @param y The y position of the pawn.
	 * @return The player owner of the pawn.
	 */
	public Player pawnBelongTo(int x,int y){
		Player ret = null;
		boolean find = false;
		int i = 0;

		while(!find && i<player1.getPawnsPlayer().length){
			if(this.player1.getPawnsPlayer()[i].getSquare().getX() == x && this.player1.getPawnsPlayer()[i].getSquare().getY() == y){
				ret = this.player1;
				find = true;
			}
			i++;
		}
		i = 0;
		while(!find && i<player2.getPawnsPlayer().length){
			if(this.player2.getPawnsPlayer()[i].getSquare().getX() == x && this.player2.getPawnsPlayer()[i].getSquare().getY() == y){
				ret = this.player2;
				find = true;
			}
			i++;
		}

		return ret;
	}

	/**
	 * This method allows to capture a pawn.
	 * @param newX The x position of the pawn to capture.
	 * @param newY The y position of the pawn to capture.
	 */
	private void captureThePawn(int newX, int newY){
		if(this.theGrid.getSquare(newX,newY).isFree()){
			throw new IllegalArgumentException("ERROR : captureThePawn : The square is empty !");
		}else{
			//Check if it's the zen
			if(newX == this.zenPawn.getSquare().getX() && newY == this.zenPawn.getSquare().getY()){
				this.zenPawn.setSquare(this.captureSquare);
			}else{
				//For the normal pawn
				if(pawnBelongTo(newX,newY) == this.player1){
					this.player1.whichPawn(newX,newY).setSquare(this.captureSquare);
				}else{
					this.player2.whichPawn(newX,newY).setSquare(this.captureSquare);
				}
			}
		}
	}

	/**
	 * This method make the requires actions when the sleeve is finish.
	 */
	private void endOfGame() {
		Player winner = isWinner();

		if(this.currentPlayer != winner){
			System.out.println("The winner of the game is "+winner.getName());
			System.out.println("The score is "+countTheCurrentScore());
		}else{
			System.out.print("Draw");
			this.draw = true;
		}

		if(this.filename != null){
			Technical.deleteSavedGame(this.filename);
		}
	}

	/**
	 * This method is able to count the number of pawn outside the main chain.
	 * It doesn't work if the game is not finish or is the game ended by a draw.
	 * @return The number of pawns outside the main chains.
	 */
	public int countTheCurrentScore(){
		int ret = -1;
		ArrayList<Integer> scorePerPawn = new ArrayList<Integer>();
		Player winner = isWinner();

		//check that it's not a draw
		if(this.currentPlayer != winner){
			ret = 0;
			//Count the score according to the winner
			if(winner == this.player1){
				for(Pawn p : this.player2.getPawnsPlayer()){
					//Avoid java exception
					if(p.getSquare() != this.captureSquare){
						int scoreAPawn = isAFinalChainRec(p,0,createCopy(),this.player2.getColor());
						scorePerPawn.add(scoreAPawn);
					}
				}
				//Don't forget to add the zen
				if(this.zenPawn.getSquare() != this.captureSquare){
					scorePerPawn.add(isAFinalChainRec(this.zenPawn,0,createCopy(),this.player2.getColor()));
				}
			}else{
				for(Pawn p : this.player1.getPawnsPlayer()){
					if(p.getSquare() != this.captureSquare){
						int scoreAPawn = isAFinalChainRec(p,0,createCopy(),this.player1.getColor());
						scorePerPawn.add(scoreAPawn);
					}
				}
				if(this.zenPawn.getSquare() != this.captureSquare){
					scorePerPawn.add(isAFinalChainRec(this.zenPawn,0,createCopy(),this.player2.getColor()));
				}
			}
			int max = 0;
			//Find the number of pawn in the main chain
			for(int i : scorePerPawn){
				if(i > max){
					max = i;
				}
			}
			//Count the number of pawn which is not in the main chain
			for(int i : scorePerPawn){
				if(i < max){
					ret++;
				}
			}
		}

		return ret;
	}

	/**
	 * This class find the winner of the game.
	 * @return The winner of the game.
	 */
	public Player isWinner(){
		Player ret = null;

		if(isFinish(this.player1)){
			ret = this.player1;
		}else if(isFinish(this.player2)){
			ret = this.player2;
		}

		return ret;
	}

	/**
	 * This method is the getter of the attribute currentPlayer
	 * @return The player's object
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}


	/**
	 * Getter of the attribute theGrid.
	 * @return The grid of the game.
	 */
	public TheGrid getTheGrid() {
		return theGrid;
	}

	/**
	 * Getter of the attribute ZenPawn.
	 * @return The zen's pawn.
	 */
	public Pawn getZenPawn() {
		return zenPawn;
	}

	/**
	 * Getter of the attribute player1.
	 * @return The first player.
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * Getter of the attribute player2.
	 * @return The second player.
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * Getter of the attribute mode.
	 * @return The mode of the game.
	 */
	public Mode getMode() {
		return mode;
	}

	/**
	 * Getter of the attribute mode.
	 * @return The mode of the game.
	 */
	public boolean isOption() {
		return option;
	}


}