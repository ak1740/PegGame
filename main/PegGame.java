/* Peg Game Interface
 * Declares all the required abstract methods
 * Aliya, Leen, Mariam, Zoya
 */
package PegGame.main;
import java.util.Collection;

public interface PegGame {
    Collection<Move> getPossibleMoves();
    GameState getGameState();
    void makeMove(Move move) throws PegGameException;

}
