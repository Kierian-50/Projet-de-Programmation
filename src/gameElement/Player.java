package gameElement;

import java.util.Arrays;

/**
 * This class represents a player. This class is abstract.
 * @author Kierian Tirlemont
 */
public abstract class Player {

	/**
	 * The pawns of the player.
	 */
	private Pawn[] pawnsPlayer;
	/**
	 * The color of the player.
	 */
	private Color color;
	/**
	 * The name of the player.
	 */
	private String name;

	/**
	 * The constructor of the class which initializes the attributes.
	 * @param name The name of the player.
	 * @param color The color of the pawns of the player.
	 */
	public Player(String name, Color color) {
	    if(color == null){
	        throw new IllegalArgumentException("Player : Error : The parameter can't be null !");
        }
		this.color = color;
		this.name = name;
		this.pawnsPlayer = new Pawn[12];
	}

	/**
	 * This method only ask the pawn that the player want to move.
	 * @return The position of the pawn.
	 */
	public abstract int[] pawnToMove();

	/**
	 * This method only ask the final position of the pawn.
	 * @return The selected pawn.
	 */
	public abstract int[] selectedPosition();

	/**
	 * This method allows to find the final position in an array
	 * of possible squares.
	 * @param squares The array of possibles squares.
	 * @return The selected final position.
	 */
	public abstract Square selectedPosition(Square[] squares);

	/**
	 * This method allows to find a pawn of the player.
	 * @param x The x position of the pawn.
	 * @param y The y position of the pawn.
	 * @return The pawn or null.
	 */
	public Pawn whichPawn(int x,int y){
		Pawn ret = null;

		for (Pawn p : pawnsPlayer) {
		    if(p.getSquare().getX() != -1 || p.getSquare().getY() != -1){
                if(p.getSquare().getX() == x && p.getSquare().getY() == y){
                    ret = p;
                }
            }
		}

		return ret;
	}

	/**
	 * This method allows to place the pawns of a player.
	 * @param theGrid The grid of the game.
	 * @param pawnsPlayer The pawns of the player with the position of each pawns.
	 * @return The grid with the pawns.
	 */
	TheGrid placePawns(TheGrid theGrid, int[][] pawnsPlayer){

		/*
		* pawnsPlayer[i][0] = x position for the i pawn.
		* pawnsPlayer[i][1] = y position for the i pawn.
		 */

		int i = 0;

		if(this.color == Color.BLACK){

			for(int[] tab : pawnsPlayer){
                if(tab[0]>=0 && tab[0]<=10 && tab[1]>=0 && tab[1]<=10) {
                    theGrid.getSquare(tab[0], tab[1]).setFree(false);
                    this.pawnsPlayer[i] = new Pawn(theGrid.getSquare(tab[0], tab[1]), this.color);
                    i++;
                }else{
                    this.pawnsPlayer[i] = new Pawn(theGrid.getSquare(-1,-1),this.color);
                }
			}

		}else if(this.color == Color.WHITE){

			for(int[] tab : pawnsPlayer){
			    if(tab[0]>=0 && tab[0]<=10 && tab[1]>=0 && tab[1]<=10) {
                    theGrid.getSquare(tab[0], tab[1]).setFree(false);
                    this.pawnsPlayer[i] = new Pawn(theGrid.getSquare(tab[0], tab[1]), this.color);
                    i++;
                }else{
			        this.pawnsPlayer[i] = new Pawn(theGrid.getSquare(-1,-1),this.color);
                }
			}
		}

		return theGrid;
	}

	/**
	 * This method put the pawn of the player in the grid.
	 * @param theGrid The grid of the game.
	 * @return The new grid with the pawn of the player.
	 */
    TheGrid placePawns(TheGrid theGrid){

		if(this.color == Color.BLACK){

			theGrid.getSquare(0,0).setFree(false);
			this.pawnsPlayer[0] = new Pawn(theGrid.getSquare(0,0),this.color);
			theGrid.getSquare(4,1).setFree(false);
			this.pawnsPlayer[1] = new Pawn(theGrid.getSquare(4,1),this.color);
			theGrid.getSquare(6,1).setFree(false);
			this.pawnsPlayer[2] = new Pawn(theGrid.getSquare(6,1),this.color);
			theGrid.getSquare(2,3).setFree(false);
			this.pawnsPlayer[3] = new Pawn(theGrid.getSquare(2,3),this.color);
			theGrid.getSquare(8,3).setFree(false);
			this.pawnsPlayer[4] = new Pawn(theGrid.getSquare(8,3),this.color);
			theGrid.getSquare(0,5).setFree(false);
			this.pawnsPlayer[5] = new Pawn(theGrid.getSquare(0,5),this.color);
			theGrid.getSquare(10,5).setFree(false);
			this.pawnsPlayer[6] = new Pawn(theGrid.getSquare(10,5),this.color);
			theGrid.getSquare(2,7).setFree(false);
			this.pawnsPlayer[7] = new Pawn(theGrid.getSquare(2,7),this.color);
			theGrid.getSquare(8,7).setFree(false);
			this.pawnsPlayer[8] = new Pawn(theGrid.getSquare(8,7),this.color);
			theGrid.getSquare(4,9).setFree(false);
			this.pawnsPlayer[9] = new Pawn(theGrid.getSquare(4,9),this.color);
			theGrid.getSquare(6,9).setFree(false);
			this.pawnsPlayer[10] = new Pawn(theGrid.getSquare(6,9),this.color);
			theGrid.getSquare(10,10).setFree(false);
			this.pawnsPlayer[11] = new Pawn(theGrid.getSquare(10,10),this.color);

		}else if(this.color == Color.WHITE){

			theGrid.getSquare(5,0).setFree(false);
			this.pawnsPlayer[0] = new Pawn(theGrid.getSquare(5,0),this.color);
			theGrid.getSquare(10,0).setFree(false);
			this.pawnsPlayer[1] = new Pawn(theGrid.getSquare(10,0),this.color);
			theGrid.getSquare(3,2).setFree(false);
			this.pawnsPlayer[2] = new Pawn(theGrid.getSquare(3,2),this.color);
			theGrid.getSquare(7,2).setFree(false);
			this.pawnsPlayer[3] = new Pawn(theGrid.getSquare(7,2),this.color);
			theGrid.getSquare(1,4).setFree(false);
			this.pawnsPlayer[4] = new Pawn(theGrid.getSquare(1,4),this.color);
			theGrid.getSquare(9,4).setFree(false);
			this.pawnsPlayer[5] = new Pawn(theGrid.getSquare(9,4),this.color);
			theGrid.getSquare(1,6).setFree(false);
			this.pawnsPlayer[6] = new Pawn(theGrid.getSquare(1,6),this.color);
			theGrid.getSquare(9,6).setFree(false);
			this.pawnsPlayer[7] = new Pawn(theGrid.getSquare(9,6),this.color);
			theGrid.getSquare(3,8).setFree(false);
			this.pawnsPlayer[8] = new Pawn(theGrid.getSquare(3,8),this.color);
			theGrid.getSquare(7,8).setFree(false);
			this.pawnsPlayer[9] = new Pawn(theGrid.getSquare(7,8),this.color);
			theGrid.getSquare(0,10).setFree(false);
			this.pawnsPlayer[10] = new Pawn(theGrid.getSquare(0,10),this.color);
			theGrid.getSquare(5,10).setFree(false);
			this.pawnsPlayer[11] = new Pawn(theGrid.getSquare(5,10),this.color);

		}
		return theGrid;
	}

	/**
	 * This method returns the collection of Pawn.
	 * @return The pawns of the players.
	 */
	public Pawn[] getPawnsPlayer() {
		return pawnsPlayer;
	}

	/**
	 * This method is the getter of the attribute name.
	 * @return The name of the player.
	 */
	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "Player{" +
				"pawnsPlayer=" + Arrays.toString(pawnsPlayer) +
				", color=" + color +
				", name='" + name + '\'' +
				'}';
	}
}