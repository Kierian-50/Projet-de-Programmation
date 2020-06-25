package gameElement;

/**
 * This class represents a pawn. This class is extends of the movement's class.
 * @author Kierian Tirlemont
 */
public class Pawn {

	/**
	 * The position of the pawn in the grid.
	 */
	private Square square;
	/**
	 * The color of the pawn.
	 */
	private Color color;


	/**
	 * The constructor of the class which initializes the attributes.
	 * @param color The color of the pawn.
	 * @param square The square where the pawn is situated.
	 */
	public Pawn(Square square, Color color) {
		if(square == null || color == null){
			throw new IllegalArgumentException("Pawn : One of the argument is null");
		}
		this.square = square;
		this.color = color;
	}

	/**
	 * This method allows to change the square of the pawn.
	 * @param square The new square.
	 */
	public void setSquare(Square square) {
		if(square == null){
			throw new IllegalArgumentException("setSquare : One of the parameter is null !");
		}
		this.square = square;
	}


	/**
	 * This method returns the value of the attribute in a string.
	 * @return The value of the attribute.
	 */
	@Override
	public String toString() {
		return "Pawn{" +
				"square=" + square +
				", color=" + color +
				'}';
	}

	/**
	 * The getter of the attribute square.
	 * @return The square where is the pawn.
	 */
	public Square getSquare() {
		return square;
	}

	/**
	 * The getter of the attribute color.
	 * @return The color of the pawn.
	 */
	public Color getColor() {
		return color;
	}
}