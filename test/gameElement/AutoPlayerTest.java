package gameElement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the AutoPlayer's class.
 * @author Kierian Tirlemont
 */
class AutoPlayerTest {
    /**
     * This method tests the constructor of the AutoPlayer's class in a normal case.
     */
    @Test
    void testConstructorNormal() {
        try {
            AutoPlayer autoPlayer = new AutoPlayer(Color.BLACK,"Player1");
        } catch (IllegalArgumentException e) {
            fail("testConstructorNormal : This is a normal case, the constructor needs to work normally !");
        }
    }

    /**
     * This method tests the constructor of the AutoPlayer's class in a error case.
     * Error case : parameter is null.
     */
    @Test
    void testConstructorErrorParamIsNull() {

        try {

            AutoPlayer autoPlayer = new AutoPlayer(null, "Player1");

            fail("testConstructorErrorParamIsNull : This is an error case, the method needs to stop and return an exception !");

        } catch (IllegalArgumentException e) {
            assertEquals("Player : Error : The parameter can't be null !", e.getMessage());
        }
    }
}