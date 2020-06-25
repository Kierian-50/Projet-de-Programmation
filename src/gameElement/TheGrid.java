package gameElement;

/**
 * This class represents the grid with an two dimensions array.
 * This class creates the grid.
 * @author Kierian Tirlemont
 */
public class TheGrid {

	/**
	 * The total of the number of square in the grid.
	 */
	private final int NUMBERSQUARE = 121;

	/**
	 * The number of square on the side.
	 */
	private final int NUMBERSIDE = 11;

	/**
	 * The grid of the sleeve.
	 */
	private Square[][] myGrid;

	/**
	 * This method is the constructor of the class which initializes the attributes.
	 */
	public TheGrid() {
		createTheGrid();
	}

	/**
	 * This method creates the the grid of the game.
	 */
	private void createTheGrid() {
		this.myGrid = new Square[this.NUMBERSIDE][this.NUMBERSIDE];
		for(int y = 0; y< this.NUMBERSIDE; y++){
			for(int x = 0; x< this.NUMBERSIDE; x++){
				this.myGrid[y][x]=new Square(y,x);
			}
		}
	}

	/**
	 * Getter of the attribute theGrid.
	 * @return The grid of square.
	 */
	public Square[][] getMyGrid() {
		return myGrid;
	}

	/**
	 * This method allows to find the a square with his position.
	 * @param x The x position of the square.
	 * @param y The y position of the square.
	 * @return The object square which correspond to the position.
	 */
	public Square getSquare(int x,int y){
		return this.myGrid[x][y];
	}
}