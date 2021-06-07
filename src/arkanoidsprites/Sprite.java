package arkanoidsprites;
// 315240937
// Michael Saar

import arkanoidgame.Game;
import biuoop.DrawSurface;

/**
 * @author Michael Saar
 * @since 20-04-2021
 * <p>
 * this interface is implemented by Classes that can behave as Sprites -- Objects that are all part of a same
 * GameEnvironment.
 */
public interface Sprite {
    // draw the sprite to the screen

    /**
     * @param d DrawSurface to draw Sprite on
     *          <p>
     *          draw a Sprite
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * @param game a game to add the Sprite to
     *             <p>
     *             adds a sprite to a Game
     */
    void addToGame(Game game);

    /**
     * @param game a game to remove Sprite from
     *             <p>
     *             adds a sprite to a Game
     */
    void removeFromGame(Game game);
}