package gameElement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class test the Match's class.
 * @author Kierian Tirlemont
 */
class MatchTest {

    /**
     * This method tests the first constructor of the class in a normal case.
     */
    @Test
    void testConstructorFirstNormal(){
        try{
            Match match = new Match("Player1","Player2",Mode.HH,true);
        } catch (IllegalArgumentException e) {
            fail("testConstructorNormal : This is a normal case : the constructor needs to work !");
        }
    }

    /**
     * This method tests the first constructor of the class in an error case.
     * The error come from a null parameter.
     */
    @Test
    void testConstructorFirstErrorParamIsNull(){
        try{
            Match match = new Match("Player1","Player2",null,true);

            fail("testConstructorErrorParamIsNull : This is an error case : the program needs to stop !");
        } catch (IllegalArgumentException e) {
            assertEquals("Match : One of the argument is null !",e.getMessage());
        }
    }

    /**
     * This method tests the second constructor of the class in a normal case.
     */
    @Test
    void testConstructorSecondNormal(){
        try{
            Match match = new Match("Player1","Player2",Mode.HH,true, 0, 0,
            "filename.txt","fileCurrentGame.txt");
        } catch (IllegalArgumentException e) {
            fail("testConstructorNormal : This is a normal case : the constructor needs to work !");
        }
    }

    /**
     * This method tests the second constructor of the class in an error case.
     * The error come from a null parameter.
     */
    @Test
    void testConstructorSecondErrorParamIsNull(){
        try{
            Match match = new Match("Player1","Player2",null,true, 0, 0,
                    "filename.txt","fileCurrentGame.txt");

            fail("testConstructorErrorParamIsNull : This is an error case : the program needs to stop !");
        } catch (IllegalArgumentException e) {
            assertEquals("Match : One of the argument is null !",e.getMessage());
        }
    }


}