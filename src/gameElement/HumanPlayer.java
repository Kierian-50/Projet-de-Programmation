package gameElement;

import java.util.Scanner;

/**
 * This class represents a human player.
 * @author Kierian Tirlemont
 */
public class HumanPlayer extends Player {

	/**
	 * The constructor of the class which initializes the attributes.
	 * @param name The name of the human player.
	 * @param color The color of the pawns of the player.
	 */
	HumanPlayer(Color color, String name) {
		super(name,color);
	}

	/**
	 * This method only ask the pawn that the player want to move.
	 * @return The position of the pawn.
	 */
	@Override
	public int[] pawnToMove() {
		int[] ret = new int[2];
		Scanner sc = new Scanner(System.in);

		ret[0] = -1;
		ret[1] = -1;

		while(ret[0]<0 || ret[0]>= 11){
			System.out.println("Please enter the x position of the pawn that you want to move");
			String xPositionPawn = sc.nextLine();
			if(xPositionPawn == null){
				System.exit(0);
			}
			try {

				ret[0] = Integer.parseInt(xPositionPawn);
				if(ret[0]<0 || ret[0]>= 11){
					throw new NumberFormatException("chooseTheMovementConsole : The value "+xPositionPawn+" entered is not correct");
				}

			}catch(NumberFormatException e){
				System.out.println("Please enter a correct number between 0 and 10 !");
			}
		}

		while(ret[1]<0 || ret[1]>= 11){
			System.out.println("Please enter the y position of the pawn that you want to move");
			String yPositionPawn = sc.nextLine();
			if(yPositionPawn == null){
				System.exit(0);
			}
			try {

				ret[1] = Integer.parseInt(yPositionPawn);
				if(ret[1]<0 || ret[1]>= 11){
					throw new NumberFormatException("chooseTheMovementConsole : The value "+yPositionPawn+" entered is not correct");
				}

			}catch(NumberFormatException e){
				System.out.println("Please enter a correct number between 0 and 10 !");
			}
		}

		return ret;
	}

	/**
	 * This method only ask the final position of the pawn.
	 * @return The selected pawn.
	 */
	@Override
	public int[] selectedPosition() {
		int[] ret = new int[2];
		Scanner sc = new Scanner(System.in);

		ret[0] = -1;
		ret[1] = -1;

		while(ret[0]<0 || ret[0]>= 11){
			System.out.println("Please enter the x position of the final position of the pawn");
			String xPositionPawn = sc.nextLine();
			if(xPositionPawn == null){
				System.exit(0);
			}
			try {

				ret[0] = Integer.parseInt(xPositionPawn);
				if(ret[0]<0 || ret[0]>= 11){
					throw new NumberFormatException("chooseTheMovementConsole : The value "+xPositionPawn+" entered is not correct");
				}

			}catch(NumberFormatException e){
				System.out.println("Please enter a correct number between 0 and 10 !");
			}
		}

		while(ret[1]<0 || ret[1]>= 11){
			System.out.println("Please enter the y position of the final position of the pawn");
			String yPositionPawn = sc.nextLine();
			if(yPositionPawn == null){
				System.exit(0);
			}
			try {

				ret[1] = Integer.parseInt(yPositionPawn);
				if(ret[1]<0 || ret[1]>= 11){
					throw new NumberFormatException("chooseTheMovementConsole : The value "+yPositionPawn+" entered is not correct");
				}

			}catch(NumberFormatException e){
				System.out.println("Please enter a correct number between 0 and 10 !");
			}
		}

		return ret;
	}

	/**
	 * This method allows to find the final position in an array
	 * of possible squares.
	 * @param squares The array of possibles squares.
	 * @return The selected final position.
	 */
	@Override
	public Square selectedPosition(Square[] squares) {
		return null;
	}

}