package gameElement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the Square's class.
 * The constructor has no error case because the a
 * square could be in -1 (that the case in my code)
 * or in 1000.
 * @author Kierian Tirlemont
 */
class SquareTest {

    /**
     * This method tests the constructor in a normal case.
     */
    @Test
    void testConstructorNormal(){
        try{
            new Square(0,0);
        }catch (IllegalArgumentException e){
            fail("testConstructorNormal : This is a normal case");
        }
    }

    /**
     * This method tests the setIsFree's method in a normal case.
     */
    @Test
    void testSetIsFreeNormal(){
        try{
            Square s1 = new Square(0,0);
            s1.setIsFree(false);
            assertFalse(s1.isFree());
        }catch (IllegalArgumentException e){
            fail("testSetIsFreeNormal : This is a normal case");
        }
    }
}