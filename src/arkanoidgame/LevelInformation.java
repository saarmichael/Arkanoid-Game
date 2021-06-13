// 315240937
// Michael Saar

package arkanoidgame;

import arkanoidsprites.Block;
import arkanoidsprites.Sprite;
import arkanoidsprites.Velocity;


import java.awt.*;
import java.util.List;

public interface LevelInformation {

    int numberOfBalls();

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    List<Velocity> initialBallVelocities();

    List<Color> initialBallColor();

    int paddleSpeed();

    int paddleWidth();

    Color paddleColor();

    // the level name will be displayed at the top of the screen.
    String levelName();

    // Returns a sprite with the background of the level
    Sprite getBackground();

    // The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();

    List<Block> unRemovableBlocks();

    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}
