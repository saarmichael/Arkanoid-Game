// 315240937
// Michael Saar

import arkanoidgame.Game;


/**
 * this class runs an Arkanoid game.
 *
 * @author Michael Saar
 * @since 03-06-2021
 */
public class Ass5Game {

    /**
     * this main method runs the Arkanoid game.
     *
     * @param args empty.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }

}
