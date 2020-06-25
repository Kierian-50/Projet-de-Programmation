package gameElement;

/**
 * This class represents a square of the grid.
 * @author Kierian Tirlemont
 */
public class Square {

	/**
	 * This attribute represents the position of the square according the x axe.
	 */
	private int x;
	/**
	 * This attribute correspond to the position of the square accord the y axe.
	 */
	private int y;
	/**
	 * This boolean says if the square is free/empty.
	 */
	private boolean isFree;

	/**
	 * The constructor of the class which initializes the attributes.
	 * @param x The position of the square according the x axe.
	 * @param y The position of the square according the y axe.
	 */
	public Square(int x, int y) {

		this.x = x;
		this.y = y;
		this.isFree = true;

	}

	/**
	 * This method set the value of the attribute isFree.
	 * @param isFree This boolean says if the square is free/empty.
	 */
	public void setIsFree(boolean isFree) {
		this.isFree = isFree;
	}

	/**
	 * Getter of the attribute isFree.
	 * @return The occupation of square.
	 */
	public boolean isFree() {
		return isFree;
	}

	/**
	 * Getter of the attribute x.
	 * @return The position of the square according to the x axe.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter of the attribute y.
	 * @return The position of the square according to the y axe.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter of the attribute isFree.
	 * @param free The new value of the attribute isFree.
	 */
	public void setFree(boolean free) {
		isFree = free;
	}

	/**
	 * Setter of the attribute x.
	 * @param x The new position.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Setter of the attribute y.
	 * @param y The new position.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * This method returns the value of the attribute of an object Square.
	 * @return The value of the attribute of an object Square.
	 */
	@Override
	public String toString() {
		return "Square{" +
				"x=" + x +
				", y=" + y +
				", isFree=" + isFree +
				'}';
	}
}