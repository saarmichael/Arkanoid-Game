// 315240937
// Michael Saar

package arkanoidgame;


import arkanoidlisteners.BallRemover;
import arkanoidlisteners.BlockRemover;
import arkanoidlisteners.HitListener;
import arkanoidsprites.Block;
import arkanoidsprites.Paddle;
import arkanoidsprites.GameEnvironment;
import arkanoidsprites.Ball;
import arkanoidsprites.SpriteCollection;
import arkanoidsprites.Collidable;
import arkanoidsprites.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// this should be like Game class!!
// should copy the class logic from there.
public class GameLevel implements Animation {

    private LevelInformation info;
    private List<Block> blocks;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor sensor;

    /**
     * constructor.
     *
     * @param ks a KeyboardSensor.
     * @param ar an AnimationRunner.
     * @param le a LevelInformation to initialize GameLevel according to.
     */
    public GameLevel(KeyboardSensor ks, AnimationRunner ar, LevelInformation le) {
        this.sensor = ks;
        this.runner = ar;
        this.info = le;
        this.blocks = new ArrayList<>();
        this.blocks.addAll(le.blocks());
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.blocksCounter = new Counter(le.blocks().size());
        this.ballsCounter = new Counter(le.numberOfBalls());
        this.score = new Counter();
        this.running = true;
    }

    private void paddleCreator() {
        Rectangle paddleRect = new Rectangle(
                new Point(400 - (this.info.paddleWidth() / 2), 580), this.info.paddleWidth(), 10);
        Paddle gameLevelPaddle =
                new Paddle(paddleRect, this.info.paddleColor(), this.info.paddleSpeed(), 5);
        gameLevelPaddle.setKeyboard(this.sensor);
        gameLevelPaddle.addToGame(this);
        this.environment.setPaddle(gameLevelPaddle);
    }

    private void ballsCreator() {
        for (int i = 0; i < this.info.numberOfBalls(); i++) {
            Ball b = new Ball(new Point(400, 575), 5,
                    this.info.initialBallColor().get(i), this.info.initialBallVelocities().get(i));
            b.setGameEnvironment(this.environment);
            b.addToGame(this);
        }
    }

    private void templateCreator() {
        if (this.info.unRemovableBlocks() != null) {
            for (int i = 0; i < this.info.unRemovableBlocks().size(); i++) {
                this.info.unRemovableBlocks().get(i).addToGame(this);
            }
        }
        Color framesColor = new Color(61, 69, 66);
        // the blocks alongside GUI frames
        Block bRight = new Block(new Rectangle(new Point(775, 0), 25, 600), framesColor);
        Block bLeft = new Block(new Rectangle(new Point(0, 0), 25, 600), framesColor);
        Block bTop = new Block(new Rectangle(new Point(0, 0), 800, 25), framesColor);
        Block bBot = new Block(new Rectangle(new Point(0, 590), 800, 10), framesColor);
        //Block bDeath = new Block(new Rectangle(new Point(0, 599), 800, 3));
        bRight.addToGame(this);
        bLeft.addToGame(this);
        bTop.addToGame(this);
        bBot.addToGame(this);
        HitListener ballRemover = new BallRemover(this, this.ballsCounter);
        bBot.addHitListener(ballRemover);
    }

    public void initialize() {
        this.sprites.getSprites().add(this.info.getBackground());
        HitListener blockRemover = new BlockRemover(this, this.blocksCounter);
        List<HitListener> gameLevelHitListeners = new ArrayList<>();
        gameLevelHitListeners.add(blockRemover);
        for (int i = 0; i < this.blocks.size(); i++) {
            this.blocks.get(i).addToGame(this);
            this.blocks.get(i).addHitListener(blockRemover);
        }
        this.paddleCreator();
        this.ballsCreator();
        this.templateCreator();
        // this.sprites.getSprites().addAll(this.blocks);
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // this.runner = new AnimationRunner();
        this.runner.run(this);
    }

    /**
     * @param c a Colliadable to add to Game's Collidabales List in GameEnvironment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * remove Collidable c from the game.
     *
     * @param c the Collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * @param s a Sprite to add to Game's Sprites List.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove Sprite s from the game Sprite's collection.
     *
     * @param s the Sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        if (this.sensor.isPressed("p")) {
            this.runner.run(new PauseGame(this.sensor));
        }
        this.sprites.notifyAllTimePassed();
        if (this.blocksCounter.getValue() <= 0) {
            this.sprites.notifyAllTimePassed();
            this.score.increase(100);
            this.sprites.drawAllOn(d);
            this.running = false;
        } else if (this.ballsCounter.getValue() <= 0) {
            this.sprites.notifyAllTimePassed();
            d.drawText(170, 170, "YOU DIE :(", 80);
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }


    public static void main(String[] args) {
        AnimationRunner ar = new AnimationRunner();
        GameLevel g1 = new GameLevel(ar.getGui().getKeyboardSensor(), ar, new Level1());
        g1.initialize();
        g1.run();

        GameLevel g2 = new GameLevel(ar.getGui().getKeyboardSensor(), ar, new Level2());
        g2.initialize();
        g2.run();
    }
}




/*
import arkanoidlisteners.BallRemover;
import arkanoidlisteners.BlockRemover;
import arkanoidlisteners.HitListener;
import arkanoidlisteners.ScoreTrackingListener;
import arkanoidsprites.SpriteCollection;
import arkanoidsprites.GameEnvironment;
import arkanoidsprites.Sprite;
import arkanoidsprites.Ball;
import arkanoidsprites.Block;
import arkanoidsprites.Collidable;
import arkanoidsprites.Paddle;
import arkanoidsprites.StatsDisplayingBlock;
import arkanoidsprites.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

 */


/**
 * @author Michael Saar
 * @param c a Colliadable to add to Game's Collidabales List in GameEnvironment
 * <p>
 * remove Collidable c from the game.
 * @param c the Collidable to remove
 * @param s a Sprite to add to Game's Sprites List.
 * <p>
 * remove Sprite s from the game Sprite's collection.
 * @param s the Sprite to remove
 * <p>
 * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
 * <p>
 * // Run the game - start the animation loop.
 *
 * <p>
 * this main method runs the Game.
 * @param c a Colliadable to add to Game's Collidabales List in GameEnvironment
 * <p>
 * remove Collidable c from the game.
 * @param c the Collidable to remove
 * @param s a Sprite to add to Game's Sprites List.
 * <p>
 * remove Sprite s from the game Sprite's collection.
 * @param s the Sprite to remove
 * <p>
 * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
 * <p>
 * // Run the game - start the animation loop.
 *
 * <p>
 * this main method runs the Game.
 * @param c a Colliadable to add to Game's Collidabales List in GameEnvironment
 * <p>
 * remove Collidable c from the game.
 * @param c the Collidable to remove
 * @param s a Sprite to add to Game's Sprites List.
 * <p>
 * remove Sprite s from the game Sprite's collection.
 * @param s the Sprite to remove
 * <p>
 * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
 * <p>
 * // Run the game - start the animation loop.
 *
 * <p>
 * this main method runs the Game.
 * @param c a Colliadable to add to Game's Collidabales List in GameEnvironment
 * <p>
 * remove Collidable c from the game.
 * @param c the Collidable to remove
 * @param s a Sprite to add to Game's Sprites List.
 * <p>
 * remove Sprite s from the game Sprite's collection.
 * @param s the Sprite to remove
 * <p>
 * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
 * <p>
 * // Run the game - start the animation loop.
 *
 * <p>
 * this main method runs the Game.
 * @since 20-04-2020
 * <p>
 * this class is responsible to initialize and run an Arkanoid game, relating to all Sprites from GameEnvironment.
 */
/*
public class Game implements Animation {

    private static final int BALL_X = 395;
    private static final int BALL_Y = 300;
    private static final int BALL_RADIUS = 5;
    private static final int BALL_ANGLE = 180;
    private static final int BALL_SPEED = 6;
    private static final Color BALL_COLOR = new Color(0, 110, 80);
    private static final Color PADDLE_COLOR = new Color(33, 80, 217);
    private static final Color BACKGROUND_COLOR = new Color(189, 235, 240);
    private static final int PADDLE_HEIGHT = 10;
    private static final int PADDLE_WIDTH = 110;
    private static final GUI ARKANOID_GUI = new GUI("Arkanoid Game", 800, 600);
    private static final Color[] BLOCKS_COLORS = {new Color(144, 238, 144), new Color(103, 216, 154),
            new Color(52, 190, 165), new Color(30, 169, 172), new Color(29, 128, 175),
            new Color(28, 97, 177), new Color(33, 42, 165), new Color(52, 190, 165),
            new Color(103, 216, 154), new Color(144, 238, 144)};
    private static final Point FRAME_START = new Point(5, 5);
    private static final Point FRAME_END = new Point(795, 795);

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private AnimationRunner runner = new AnimationRunner();
    private boolean running;
    private KeyboardSensor sensor;

 */


/**
 * @param c a Colliadable to add to Game's Collidabales List in GameEnvironment
 */
    /*
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

     */

/**
 * remove Collidable c from the game.
 *
 * @param c the Collidable to remove
 */
    /*
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

     */

/**
 * @param s a Sprite to add to Game's Sprites List.
 */
    /*
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

     */

/**
 * remove Sprite s from the game Sprite's collection.
 *
 * @param s the Sprite to remove
 */
    /*
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

     */


/**
 * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
 */
    /*
    public void initialize() {
        this.blocksCounter = new Counter();
        this.score = new Counter();
        HitListener blockRemover = new BlockRemover(this, this.blocksCounter);
        HitListener scoreTracker = new ScoreTrackingListener(this.score);
        List<HitListener> gameHitListeners = new ArrayList<>();
        gameHitListeners.add(blockRemover);
        gameHitListeners.add(scoreTracker);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.sensor = this.runner.getGui().getKeyboardSensor();
        Rectangle paddleRect = new Rectangle(new Point(350, 580), PADDLE_WIDTH, PADDLE_HEIGHT);
        Paddle gamePaddle = new Paddle(paddleRect, PADDLE_COLOR, 11, 5);
        gamePaddle.setKeyboard(this.sensor);
        gamePaddle.addToGame(this);
        environment.setPaddle(gamePaddle);
        // the Ball game
        Ball gameBall1 = new Ball(
                new Point(BALL_X, BALL_Y), BALL_RADIUS, BALL_COLOR,
                Velocity.fromAngleAndSpeed(BALL_ANGLE, BALL_SPEED), FRAME_START, FRAME_END);
        gameBall1.setGameEnvironment(this.environment);
        gameBall1.addToGame(this);
        Ball gameBall2 = new Ball(
                new Point(BALL_X + 15, BALL_Y), BALL_RADIUS, BALL_COLOR,
                Velocity.fromAngleAndSpeed(BALL_ANGLE, BALL_SPEED), FRAME_START, FRAME_END);
        gameBall2.setGameEnvironment(this.environment);
        gameBall2.addToGame(this);
        Ball gameBall3 = new Ball(
                new Point(BALL_X - 15, BALL_Y), BALL_RADIUS, BALL_COLOR,
                Velocity.fromAngleAndSpeed(BALL_ANGLE, BALL_SPEED), FRAME_START, FRAME_END);
        gameBall3.setGameEnvironment(this.environment);
        gameBall3.addToGame(this);
        this.ballsCounter = new Counter(3);
        // the blocks alongside GUI frames
        Block bRight = new Block(new Rectangle(new Point(785, 0), 15, 600));
        Block bLeft = new Block(new Rectangle(new Point(0, 0), 15, 600));
        Block bTop = new Block(new Rectangle(new Point(0, 0), 800, 25));
        Block bBot = new Block(new Rectangle(new Point(0, 590), 800, 3));
        //Block bDeath = new Block(new Rectangle(new Point(0, 599), 800, 3));
        bRight.addToGame(this);
        bLeft.addToGame(this);
        bTop.addToGame(this);
        bBot.addToGame(this);
        // bDeath.addToGame(this);
        Block scoreDisplayer = new StatsDisplayingBlock(new Rectangle(new Point(0, 2), 800, 15),
                Color.GRAY.brighter(), null, this.score, "Score: ");
        scoreDisplayer.addToGame(this);
        HitListener ballRemover = new BallRemover(this, this.ballsCounter);
        bBot.addHitListener(ballRemover);
        // k is a parameter to set the x Value start of the Blocks
        int k = 305;
        int q = 0;
        // add multiple Blocks arranged in a half-pyramid structure

        /*
        DEAR ASSIGNMENT CHECKER!!!!!
        if you want the game to be shorter do as follows:
        in the first loop, change i <250 to 2 < 150
        and in the second loop change j < 785 to j < 665
        ENJOY!
         */
    /*
        for (int i = 125; i < 250; i += 25) {
            Color blockColor = BLOCKS_COLORS[q];
            for (int j = k; j < 785; j += 60) {
                Block block = new Block(
                        new Rectangle(new Point(j, i), 60, 25), blockColor, gameHitListeners);
                block.addToGame(this);
                this.blocksCounter.increase();
            }
            k += 60;
            q++;
        }
    }

     */

    /*
    int k = 135;
        int q = 0;
        // add multiple Blocks arranged in a half-pyramid structure
        for (int i = 122; i < 262; i += 22) {
            Color blockColor = BLOCKS_COLORS[q];
            for (int j = k; j < 795; j += 60) {
                Block block = new Block(
                        new Rectangle(new Point(j, i), 60, 22), blockColor, gameHitListeners);
                block.addToGame(this);
                this.blocksCounter.increase();
            }
            k += 60;
            q++;
     */


/**
 * // Run the game - start the animation loop.
 */
    /*
        public void run() {
            this.runner.run(new CountdownAnimation(2, 3, this.sprites));
            this.running = true;
            // this.runner = new AnimationRunner();
            this.runner.run(this);
     */


// set the GUI, sleeper and animation speed
// Sleeper sleeper = new Sleeper();
// int framesPerSecond = 60;
// int millisecondsPerFrame = 1000 / framesPerSecond;
// run the animation
        /*
        while (this.blocksCounter.getValue() > 0 && this.ballsCounter.getValue() > 0) {
            // timing
            long startTime = System.currentTimeMillis();
            DrawSurface d = ARKANOID_GUI.getDrawSurface();
            d.setColor(BACKGROUND_COLOR);
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d);
            // d.drawText(20, 20, "blocks remain: " + this.blocksCounter.getValue(), 20);
            ARKANOID_GUI.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        sleeper.sleepFor(100);
        DrawSurface d = ARKANOID_GUI.getDrawSurface();
        d.setColor(BACKGROUND_COLOR);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        if (this.blocksCounter.getValue() == 0) {
            sleeper.sleepFor(100);
            this.score.increase(100);
            this.sprites.drawAllOn(d);
            //d.drawText(170, 170, "YOU WON !!!", 80);
        } else {
            d.drawText(170, 170, "YOU DIE :(", 80);
        }
        ARKANOID_GUI.show(d);
        sleeper.sleepFor(1000);
        ARKANOID_GUI.close();

         */



    /*
}

     */


/**
 * <p>
 * this main method runs the Game.
 */
    /*
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }

     */

    /*
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(BACKGROUND_COLOR);
        d.fillRectangle(0, 0, 800, 600);
        this.sprites.drawAllOn(d);
        if (this.sensor.isPressed("p")) {
            this.runner.run(new PauseGame(this.sensor));
        }
        // d.drawText(20, 20, "blocks remain: " + this.blocksCounter.getValue(), 20);
        this.sprites.notifyAllTimePassed();
        if (this.blocksCounter.getValue() <= 0) {
            this.sprites.notifyAllTimePassed();
            this.score.increase(100);
            this.sprites.drawAllOn(d);
            this.running = false;
        } else if (this.ballsCounter.getValue() <= 0) {
            this.sprites.notifyAllTimePassed();
            d.drawText(170, 170, "YOU DIE :(", 80);
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}

     */