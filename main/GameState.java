/* Enum Class for the game status
 Aliya, Leen, Mariam, Zoya
 */
package PegGame.main;

public enum GameState {
    NOT_STARTED("Game has not started"),
    IN_PROGRESS("Game is in progress"),
    STALEMATE("Game ended in a stalemate"),
    WON("Congratulation! You won the game");

    private final String message;
    GameState(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
