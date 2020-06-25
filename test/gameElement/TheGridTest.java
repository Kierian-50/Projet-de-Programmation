package gameElement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the TheGrid's method.
 * @author Kierian Tirlemont
 */
class TheGridTest {

    /**
     * This method tests the constructor of the class.
     */
    @Test
    void testConstructorNormal(){
        TheGrid theGrid = new TheGrid();

        int i = 0;

        for(Square[] squares : theGrid.getMyGrid()){
            for(Square s : squares){
                i++;
                if(s.getX()>=11 || s.getX()<0 || s.getY()>=11 || s.getY()<0){
                    fail("The position of this square "+s.toString()+" isn't correct !");
                }
            }
        }
        assertEquals(121,i);
        assertEquals(11,theGrid.getMyGrid().length);
    }

    /**
     * This method tests the getSquare's method of the class.
     */
    @Test
    void getSquare() {
        TheGrid theGrid = new TheGrid();
        assertTrue(theGrid.getSquare(0, 0).isFree());
        assertTrue(theGrid.getSquare(10, 10).isFree());
    }
}