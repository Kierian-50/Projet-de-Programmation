package gameElement;

/**
 * This class represents an auto player.
 * @author Kierian Tirlemont
 */
public class AutoPlayer extends Player {

	/**
	 * The constructor of the class which initializes the attributes.
	 * @param name The name of the player.
	 * @param color The color of the pawn of the player.
	 */
	public AutoPlayer(Color color,String name) {
		super(name,color);
	}

	/**
	 * This method allows to choose randomly a pawn to move among the pawn of the player.
	 * @return The position of the pawn which will be move.
	 * int nombreAleatoire = Min + (int)(Math.random() * ((Max - Min) + 1));
	 */
	@Override
	public int[] pawnToMove() {
		int[] ret = new int[2];
		int indexOfThePawn = (int)(Math.random() * ((11) + 1));
		Pawn chosenPawn = super.getPawnsPlayer()[indexOfThePawn];
		while (chosenPawn.getSquare().getX() == -1 || chosenPawn.getSquare().getY() == -1){
			indexOfThePawn = (int)(Math.random() * ((11) + 1));
			chosenPawn = super.getPawnsPlayer()[indexOfThePawn];
		}

		ret[0] = chosenPawn.getSquare().getX();
		ret[1] = chosenPawn.getSquare().getY();

		return ret;
	}

	/**
	 * This method allows to find the final position.
	 * @return Return the selected position in a array of int.
	 */
	@Override
	public int[] selectedPosition() {
		return new int[0];
	}

	/**
	 * This method allows to find the final position in an array
	 * of possible squares.
	 * @param squares The array of possibles squares.
	 * @return The selected final position.
	 */
	@Override
	public Square selectedPosition(Square[] squares) {
		int indexFinalPosition = (int)(Math.random() * ((squares.length)));
		return squares[indexFinalPosition];
	}
}