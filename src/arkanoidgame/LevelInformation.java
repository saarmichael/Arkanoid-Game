// 315240937
// Michael Saar

package arkanoidgame;

import arkanoidsprites.Block;
import arkanoidsprites.Sprite;
import arkanoidsprites.Velocity;


import java.awt.Color;
import java.util.List;

/**
 * this interface represents all classes that supply information for GameLevel creating.
 *
 * @author Michael Saar
 * @since 20-06-2021
 */
public interface LevelInformation {

    /**
     * @return the number of Balls to crseate in the level.
     */
    int numberOfBalls();

    /**
     * @return List of the initial Velocities of all Balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return List of the initial Colors of all Balls.
     */
    List<Color> initialBallColor();

    /**
     * @return the Paddle's speed.
     */
    int paddleSpeed();

    /**
     * @return the Paddle's width.
     */
    int paddleWidth();

    /**
     * @return the Paddle's Color.
     */
    Color paddleColor();

    /**
     * @return the level's name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * The Blocks that make up this level but are not removable, each block contains its size, color and location.
     */
    List<Block> unRemovableBlocks();

    /**
     * @return the Number of blocks that should be remove before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}
