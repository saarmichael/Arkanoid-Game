// 315240937
// Michael Saar
package arkanoidgame;

import arkanoidsprites.Block;
import arkanoidsprites.Level1Background;
import arkanoidsprites.Sprite;
import arkanoidsprites.Velocity;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this Level properties are.
 * number of Balls: 1
 * number of Blocks: 1
 * paddle size: normal
 * paddle speed: normal
 * background: target image
 * number of Blocks to hit in order to win level: all
 *
 * @author Michael Saar
 * @since 10-06-2021
 */
public class Level1 implements LevelInformation {

    // private int numberOfBalls;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numOfBallsToRemove;


    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVs = new ArrayList<>();
        ballsVs.add(Velocity.fromAngleAndSpeed(0, 3));
        return ballsVs;
    }

    @Override
    public List<Color> initialBallColor() {
        List<Color> ballsCs = new ArrayList<>();
        ballsCs.add(new Color(101, 197, 224));
        return ballsCs;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public Color paddleColor() {
        return new Color(7, 59, 8);
    }

    @Override
    public String levelName() {
        return "tutorial";
    }

    @Override
    public Sprite getBackground() {
        return new Level1Background(800, 600);
    }

    @Override
    public List<Block> blocks() {
        Block b1 = new Block(
                new Rectangle(new Point(380, 180), 40, 40), new Color(93, 250, 123));
        List<Block> blocksList = new ArrayList<>();
        blocksList.add(b1);
        return blocksList;
    }

    @Override
    public List<Block> unRemovableBlocks() {
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
