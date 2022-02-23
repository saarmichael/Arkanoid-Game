// 315240937
// Michael Saar

package arkanoidsprites;


import arkanoidgame.GameLevel;
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
     * @param gameLevel a game to add the Sprite to
     *                  <p>
     *                  adds a sprite to a Game
     */
    void addToGame(GameLevel gameLevel);

    /**
     * @param gameLevel a game to remove Sprite from
     *                  <p>
     *                  adds a sprite to a Game
     */
    void removeFromGame(GameLevel gameLevel);
}