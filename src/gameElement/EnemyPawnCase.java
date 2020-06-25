package gameElement;

/**
 * This enumeration corresponds to every cases for the movement of a pawn.
 */
public enum EnemyPawnCase {
    ALLOW,     //Movement which is allow because np with the enemy pawn
    FORBIDDEN, //Movement forbidden because there is a enemy pawn
    CAPTURE;   //you can capture the enemy pawn
}