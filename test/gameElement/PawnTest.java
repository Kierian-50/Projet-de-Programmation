package gameElement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the Pawn's class.
 * @author Kierian Tirlemont
 */
class PawnTest {

    /**
     * This method tests the constructor in a normal case.
     */
    @Test
    void testConstructorNormal(){
        try {
            new Pawn(new Square(0,0),Color.BLACK);
        } catch (IllegalArgumentException e) {
            fail("testConstructorNormal : This is a normal case, the constructor needs to work !");
        }
    }

    /**
     * This method tests the constructor in an error case:
     * One of the parameter is null
     */
    @Test
    void testConstructorErrorParamIsNull(){
        try {

            new Pawn(null,Color.BLACK);

            fail("testConstructorNormal : This is an error case, the constructor needs to stop !");

        } catch (IllegalArgumentException e) {
            assertEquals("Pawn : One of the argument is null",e.getMessage());
        }
    }

    /**
     * This method tests the setSquare's method in an error case :
     * The error come from one of the parameter which is null.
     */
    @Test
    void testSetSquareErrorParamIsNull(){
        try {

            Pawn pawn = new Pawn(new Square(0,0),Color.BLACK);

            pawn.setSquare(null);

            fail("testSetSquareErrorParamIsNull : This is an error case, the method needs to stop !");

        } catch (IllegalArgumentException e) {
            assertEquals("setSquare : One of the parameter is null !",e.getMessage());
        }
    }

    /**
     * This method tests the setSquare's method in a normal case.
     */
    @Test
    void testSetSquareNormal(){
        try {

            Pawn pawn = new Pawn(new Square(0,0),Color.BLACK);

            pawn.setSquare(new Square(1,1));

            assertEquals(1,pawn.getSquare().getX());
            assertEquals(1,pawn.getSquare().getY());

        } catch (IllegalArgumentException e) {
            fail("testSetSquareNormal : This is a normal case, the method needs to work !");
        }
    }
}