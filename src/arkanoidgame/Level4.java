// 315240937
// Michael Saar

package arkanoidgame;

import arkanoidsprites.Level2Background;
import arkanoidsprites.Velocity;
import arkanoidsprites.Block;
import arkanoidsprites.Sprite;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this Level properties are.
 * number of Balls: 3
 * number of Blocks: 115
 * paddle size: normal
 * paddle speed: fast
 * background: none
 * number of Blocks to hit in order to win level: 20
 *
 * @author Michael Saar
 * @since 10-06-2021
 */
public class Level4 implements LevelInformation {

    private static final Color[] BLOCKS_COLORS = {new Color(144, 238, 144), new Color(103, 216, 154),
            new Color(52, 190, 165), new Color(30, 169, 172), new Color(29, 128, 175),
            new Color(28, 97, 177), new Color(52, 190, 165), new Color(33, 42, 165),
            new Color(103, 216, 154), new Color(144, 238, 144)};

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v1 = Velocity.fromAngleAndSpeed(0, 5);
        Velocity v2 = Velocity.fromAngleAndSpeed(10, 5);
        Velocity v3 = Velocity.fromAngleAndSpeed(-10, 5);
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(v1);
        velocities.add(v2);
        velocities.add(v3);
        return velocities;
    }

    @Override
    public List<Color> initialBallColor() {
        Color c1 = new Color(5, 62, 9);
        Color c3 = new Color(5, 62, 9);
        Color c2 = new Color(5, 62, 9);
        List<Color> colors = new ArrayList<>();
        colors.add(c1);
        colors.add(c2);
        colors.add(c3);
        return colors;
    }

    @Override
    public int paddleSpeed() {
        return 11;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public Color paddleColor() {
        return new Color(6, 102, 90);
    }

    @Override
    public String levelName() {
        return "BOSS";
    }

    @Override
    public Sprite getBackground() {
        return new Level2Background(800, 600);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int k = 25;
        int q = 0;
        // add multiple Blocks arranged in a half-pyramid structure
        for (int i = 100; i < 275; i += 25) {
            Color blockColor = BLOCKS_COLORS[q];
            for (int j = k; j < 775; j += 50) {
                Block block = new Block(new Rectangle(new Point(j, i), 50, 25), blockColor);
                blocks.add(block);
            }
            // k += 50;
            q++;
        }
        return blocks;
    }


    @Override
    public List<Block> unRemovableBlocks() {
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 20;
    }
}
