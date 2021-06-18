package arkanoidgame;

import arkanoidsprites.Block;
import arkanoidsprites.Level3Background;
import arkanoidsprites.Sprite;
import arkanoidsprites.Velocity;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.*;
import java.awt.desktop.PreferencesEvent;
import java.util.ArrayList;
import java.util.List;

public class Level3 implements LevelInformation {

    private static final Color[] BLOCKS_COLORS = {new Color(144, 238, 144), new Color(103, 216, 154),
            new Color(52, 190, 165), new Color(30, 169, 172), new Color(29, 128, 175),
            new Color(28, 97, 177), new Color(33, 42, 165), new Color(52, 190, 165),
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
        return "Street-Ball";
    }

    @Override
    public Sprite getBackground() {
        return new Level3Background(800, 600);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int k = 275;
        int q = 0;
        // add multiple Blocks arranged in a half-pyramid structure
        for (int i = 200; i < 325; i += 25) {
            Color blockColor = BLOCKS_COLORS[q];
            for (int j = k; j < 775; j += 50) {
                Block block = new Block(new Rectangle(new Point(j, i), 50, 25), blockColor);
                blocks.add(block);
            }
            k += 50;
            q++;
        }
        return blocks;
    }


    @Override
    public List<Block> unRemovableBlocks() {
        Block b1 = new Block(
                new Rectangle(new Point(180, 350), 60, 250), new Color(40, 46, 50));
        //Block b2 = new Block(
          //      new Rectangle(new Point(200, 430), 20, 50), new Color(40, 46, 50));
        //Block b3 = new Block(
          //      new Rectangle(new Point(205, 200), 10, 230), new Color(56, 67, 71));
        List<Block> unBlocks = new ArrayList<>();
        unBlocks.add(b1);
        // unBlocks.add(b2);
        // unBlocks.add(b3);
        return unBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 10;
    }
}
