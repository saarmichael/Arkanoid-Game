// 315240937
// Michael Saar

package arkanoidgame;

import arkanoidsprites.Sprite;
import arkanoidsprites.Block;
import arkanoidsprites.Velocity;
import arkanoidsprites.Level2Background;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this Level properties are.
 * number of Balls: 10
 * number of Blocks: 15
 * paddle size: huge
 * paddle speed: slow
 * background: Green Sun
 * number of Blocks to hit in order to win level: all
 *
 * @author Michael Saar
 * @since 10-06-2021
 */
public class Level2 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVs = new ArrayList<>();
        // for each ball there is a corresponding ball in exactly the negate angle.
        for (int i = 1; i < this.numberOfBalls() + 1; i = i + 2) {
            ballsVs.add(Velocity.fromAngleAndSpeed(5 * i, 5));
            ballsVs.add(Velocity.fromAngleAndSpeed(-5 * i, 5));
        }
        return ballsVs;
    }

    @Override
    public List<Color> initialBallColor() {
        List<Color> ballsCs = new ArrayList<>();
        for (int i = 1; i < this.numberOfBalls() + 1; i++) {
            ballsCs.add(new Color(i * 5, i * 5, i * 5));
        }
        return ballsCs;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public Color paddleColor() {
        return new Color(6, 102, 90);
    }

    @Override
    public String levelName() {
        return "Green Peace";
    }

    @Override
    public Sprite getBackground() {
        return new Level2Background(800, 600);
    }

    @Override
    public List<Block> blocks() {
        // Block b1 = new Block(new Rectangle(new Point(380, 180), 40, 40), Color.RED);
        List<Block> blocksList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Block b = new Block(new Rectangle(new Point(25 + (i * 50), 250), 50, 25),
                    new Color(i, 10 * i, 7 * i));
            blocksList.add(b);
        }
        return blocksList;
    }

    @Override
    public List<Block> unRemovableBlocks() {
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
