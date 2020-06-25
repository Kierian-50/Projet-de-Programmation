package gameElement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the HumanPlayer's class.
 * @author Kierian Tirlemont
 */
class HumanPlayerTest {
    /**
     * This method tests the constructor of the HumanPlayer's class in a normal case.
     */
    @Test
    void testConstructorNormal() {
        try {
            HumanPlayer humanPlayer = new HumanPlayer(Color.BLACK,"Player1");
        } catch (IllegalArgumentException e) {
            fail("testConstructorNormal : This is a normal case, the constructor needs to work normally !");
        }
    }

    /**
     * This method tests the constructor of the HumanPlayer's class in a error case.
     * Error case : parameter is null.
     */
    @Test
    void testConstructorErrorParamIsNull() {
        try {
            HumanPlayer humanPlayer = new HumanPlayer(null, "Player1");
            fail("testConstructorErrorParamIsNull : This is an error case, the method needs to stop and return an exception !");
        } catch (IllegalArgumentException e) {
            assertEquals("Player : Error : The parameter can't be null !", e.getMessage());
        }
    }
}