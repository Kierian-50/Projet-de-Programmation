package gameElement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the Player's class.
 * @author Kierian Tirlemont
 */
class PlayerTest {

    /**
     * The method test in a normal case the constructor.
     */
    @Test
    void testConstructorNormal() {
        try {

            Player player = new AutoPlayer(Color.BLACK, "Player");

        } catch (IllegalArgumentException e) {
            fail("testConstructorNormal : This is a normal case, the constructor needs to work normally !");
        }
    }

    /**
     * The method test in an error case the constructor:
     * The error come from the parameter which is null.
     */
    @Test
    void testConstructorErrorParamNull() {
        try {

            Player player = new AutoPlayer(null, "Player");
            fail("testConstructorNormal : This is a normal case, the constructor needs to work normally !");

        } catch (IllegalArgumentException e) {
            assertEquals("Player : Error : The parameter can't be null !", e.getMessage());
        }
    }

    /**
     * Test the method whichPawns in a case of
     * the pawn exists.
     */
    @Test
    void whichPawnsExist(){
        try {

            Player player = new HumanPlayer(Color.BLACK, "Player");
            player.placePawns(new TheGrid());
            assertNotNull(player.whichPawn(0,0));

        } catch (IllegalArgumentException e) {
            fail("whichPawnsExist : This is a normal case, the constructor needs to work normally !");
        }
    }

    /**
     * Test the method whichPawns in a case of
     * the pawn does not exist.
     */
    @Test
    void whichPawnsDoesntExist(){
        try {

            Player player = new AutoPlayer(Color.BLACK, "Player");
            player.placePawns(new TheGrid());
            assertNull(player.whichPawn(1,1));

        } catch (IllegalArgumentException e) {
            fail("whichPawnsDoesntExist : This is a normal case, the constructor needs to work normally !");
        }
    }

}