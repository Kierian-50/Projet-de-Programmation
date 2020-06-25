package gameElement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the game's class.
 * @author Kierian Tirlemont
 */
class GameTest {

    /**
     * This method tests the first constructor in a normal case.
     */
    @Test
    void testConstructorNormal(){
        try{

            Game game = new Game(null,null,Mode.HH,true);

        }catch (IllegalArgumentException e){
            fail("testConstructorNormal : This is a normal case : The constructor needs to work normally !");
        }
    }

    /**
     * This method tests the first constructor in an error case.
     * One of the player is null.
     */
    @Test
    void testConstructorErrorParamIsNull(){
        try{

            Game game = new Game(null,null,null,true);

            fail("testConstructorNormal : This is an error case : The constructor needs to stop " +
                    "and return an exception!");

        }catch (IllegalArgumentException e){
            assertEquals("Game : One of the parameter/Player is null !", e.getMessage());
        }
    }

    /**
     * This method tests the first constructor in a normal case.
     */
    @Test
    void testConstructorSecondNormal(){
        try{

            Game game = new Game(null,null,Mode.HH,true,new int[2][12],new int[2][12],new int[2],
                    "null.txt");

        }catch (IllegalArgumentException e){
            fail("testConstructorNormal : This is a normal case : The constructor needs to work normally !");
        }
    }

    /**
     * This method tests the first constructor in an error case.
     * One of the player is null.
     */
    @Test
    void testConstructorSecondErrorParamIsNull(){
        try{

            Game game = new Game(null,null,Mode.HH,true,new int[2][12],new int[2][12],new int[2],
                    null);

        }catch (IllegalArgumentException e){
            assertEquals("Game : One of the parameter/Player is null !", e.getMessage());
        }
    }

    /**
     * This method tests the changeCurrent's method in a normal case.
     */
    @Test
    void testChangeCurrentNormal(){
        try{


            Game game = new Game("Player1","Player2",Mode.HH,true);

            assertEquals("Player1",game.getCurrentPlayer().getName());

            game.changeCurrent();

            assertEquals("Player2",game.getCurrentPlayer().getName());

        }catch (IllegalArgumentException e){
            fail("testChangeCurrentNormal : This is a normal case : The changeCurrent's method needs to work normally !");
        }
    }

    /**
     * Test authorizeSquare in a normal case
     */
    @org.junit.jupiter.api.Test
    void authorizeSquare(){
        Game game = new Game(null,null,Mode.HH,true);
        Square[] possibleSquare = game.authorizeSquare(0,0);
        for(Square s : possibleSquare){
            if(s.getX() != 3 && s.getX() != 0 && s.getY() != 0 && s.getY() != 3){
                fail();
            }
        }
    }

    /**
     * Test pawnBelongTo in different cases.
     */
    @org.junit.jupiter.api.Test
    void pawnBelongTo(){
        Game game = new Game("Player1","Player2",Mode.HH,true);
        assertEquals(game.pawnBelongTo(0,0).getName(),"Player1");
        assertEquals(game.pawnBelongTo(0,10).getName(),"Player2");
        assertNull(game.pawnBelongTo(5, 5));
    }

    /**
     * Try to move the zen with the method move.
     */
    @org.junit.jupiter.api.Test
    void moveZen() {
        Game game = new Game(null,null,Mode.HH,true);
        game.move(0,1,5,5);
        assertFalse(game.getTheGrid().getSquare(0,1).isFree());
        assertTrue(game.getTheGrid().getSquare(5,5).isFree());
    }

    /**
     * Try to move a pawn with the method move.
     */
    @org.junit.jupiter.api.Test
    void moveAPawn1(){
        Game game = new Game(null,null,Mode.HH,true);
        game.move(1,1,0,0);
        assertFalse(game.getTheGrid().getSquare(1,1).isFree());
        assertTrue(game.getTheGrid().getSquare(0,0).isFree());
    }

    /**
     * Try to move a pawn with the method move.
     */
    @org.junit.jupiter.api.Test
    void moveAPawn2(){
        Game game = new Game(null,null,Mode.HH,true);
        game.move(10,7,10,10);
        assertFalse(game.getTheGrid().getSquare(10,7).isFree());
        assertTrue(game.getTheGrid().getSquare(10,10).isFree());
    }

    /**
     * Try to move a pawn with the method move.
     */
    @org.junit.jupiter.api.Test
    void moveAPawn3(){
        Game game = new Game(null,null,Mode.HH,true);
        game.move(2,9,4,9);
        assertFalse(game.getTheGrid().getSquare(2,9).isFree());
        assertTrue(game.getTheGrid().getSquare(4,9).isFree());
    }

    /**
     * Try to move a pawn with the method move.
     */
    @org.junit.jupiter.api.Test
    void moveAPawn4(){
        Game game = new Game(null,null,Mode.HH,true);
        game.move(0,2,0,5);
        assertFalse(game.getTheGrid().getSquare(0,2).isFree());
        assertTrue(game.getTheGrid().getSquare(0,5).isFree());
    }

    /**
     * Try to move a pawn with the method move.
     */
    @org.junit.jupiter.api.Test
    void moveAPawn5(){
        Game game = new Game(null,null,Mode.HH,true);
        game.move(8,8,8,7);
        assertFalse(game.getTheGrid().getSquare(8,8).isFree());
        assertTrue(game.getTheGrid().getSquare(8,7).isFree());
    }

    /**
     * Error check if the pawn exists.
     */
    @org.junit.jupiter.api.Test
    void moveAPawn6(){
        try {
            Game game = new Game(null,null,Mode.HH,true);
            game.move(2,2,1,1);
            fail();
        } catch (Exception e) {
            assertEquals("ERROR : move : the square is empty",e.getMessage());
        }
    }

    /**
     * Checks move the zen.
     */
    @org.junit.jupiter.api.Test
    void moveAPawn7(){
        try {
            Game game = new Game(null,null,Mode.HH,true);
            game.move(5,4,5,5);
            assertEquals(5,game.getZenPawn().getSquare().getX());
            assertEquals(4,game.getZenPawn().getSquare().getY());
            assertFalse(game.getTheGrid().getSquare(5,4).isFree());
            assertTrue(game.getTheGrid().getSquare(5,5).isFree());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Checks move the zen.
     */
    @org.junit.jupiter.api.Test
    void moveAPawn8(){
        try {
            Game game = new Game(null,null,Mode.HH,true);
            game.move(6,5,5,5);
            assertEquals(6,game.getZenPawn().getSquare().getX());
            assertEquals(5,game.getZenPawn().getSquare().getY());
            assertFalse(game.getTheGrid().getSquare(6,5).isFree());
            assertTrue(game.getTheGrid().getSquare(5,5).isFree());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Checks move a pawn.
     */
    @org.junit.jupiter.api.Test
    void moveAPawn9(){
        try {
            Game game = new Game(null,null,Mode.HH,true);
            game.move(0,1,0,0);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Checks move a pawn.
     */
    @org.junit.jupiter.api.Test
    void moveAPawn10(){
        try {
            Game game = new Game(null,null,Mode.HH,true);
            game.move(1,1,0,0);
            assertFalse(game.getTheGrid().getSquare(1,1).isFree());
            assertTrue(game.getTheGrid().getSquare(0,0).isFree());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Test the displayBoard without problem.
     */
    @Test
    void displayBoard(){
        try {
            Game game = new Game(null,null,Mode.HH,true);
            game.displayBoard();
        }catch (Exception e){
            fail();
        }
    }
}