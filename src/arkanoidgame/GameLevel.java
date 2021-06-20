// 315240937
// Michael Saar

package arkanoidgame;


import arkanoidlisteners.BallRemover;
import arkanoidlisteners.BlockRemover;
import arkanoidlisteners.HitListener;
import arkanoidlisteners.ScoreTrackingListener;
import arkanoidsprites.Sprite;
import arkanoidsprites.Block;
import arkanoidsprites.SpriteCollection;
import arkanoidsprites.GameEnvironment;
import arkanoidsprites.Paddle;
import arkanoidsprites.Ball;
import arkanoidsprites.StatsDisplayingBlock;
import arkanoidsprites.Collidable;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is responsible to create a full Animation based on info given from a GameLevel class.
 *
 * @author Michael Saar
 * @since 20-06-2021
 */
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
        this.blocksCounter = new Counter(0);
        this.ballsCounter = new Counter(le.numberOfBalls());
        this.score = new Counter();
        this.running = true;
    }

    /**
     * constructor.
     *
     * @param ks    a KeyboardSensor.
     * @param ar    an AnimationRunner.
     * @param le    a LevelInformation to initialize GameLevel according to.
     * @param score the current game score.
     */
    public GameLevel(KeyboardSensor ks, AnimationRunner ar, LevelInformation le, Counter score) {
        this(ks, ar, le);
        this.score = score;
    }

    /**
     * creates a new Paddle based on given info.
     */
    private void paddleCreator() {
        Rectangle paddleRect = new Rectangle(
                new Point(400 - (this.info.paddleWidth() / 2), 580), this.info.paddleWidth(), 10);
        Paddle gameLevelPaddle =
                new Paddle(paddleRect, this.info.paddleColor(), this.info.paddleSpeed(), 5);
        gameLevelPaddle.setKeyboard(this.sensor);
        gameLevelPaddle.addToGame(this);
        this.environment.setPaddle(gameLevelPaddle);
    }

    /**
     * creates a new List of Balls based on given info.
     */
    private void ballsCreator() {
        for (int i = 0; i < this.info.numberOfBalls(); i++) {
            Ball b = new Ball(new Point(400, 575), 5,
                    this.info.initialBallColor().get(i), this.info.initialBallVelocities().get(i));
            b.setGameEnvironment(this.environment);
            b.addToGame(this);
        }
    }

    /**
     * responsible to create all non-Collidable-Blocks, Background-paintings, frame Blocks etc.
     */
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
        HitListener statListener = new ScoreTrackingListener(this.score);
        List<HitListener> scoreListenersList = new ArrayList<>();
        scoreListenersList.add(statListener);
        Block statBar = new StatsDisplayingBlock((new Rectangle(new Point(0, 0), 800, 25)),
                Color.CYAN, scoreListenersList, this.score,
                this.ballsCounter, this.info.levelName());
        statBar.addToGame(this);
    }

    /**
     * gathers together all initializing logic.
     */
    public void initialize() {
        this.sprites.getSprites().add(this.info.getBackground());
        HitListener blockRemover = new BlockRemover(this, this.blocksCounter);
        HitListener scoreTracker = new ScoreTrackingListener(this.score);
        List<HitListener> gameLevelHitListeners = new ArrayList<>();
        gameLevelHitListeners.add(blockRemover);
        gameLevelHitListeners.add(scoreTracker);
        for (int i = 0; i < this.blocks.size(); i++) {
            this.blocks.get(i).addToGame(this);
            this.blocks.get(i).addHitListener(blockRemover);
            this.blocks.get(i).addHitListener(scoreTracker);
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

    /**
     * getter fot Balls counter.
     *
     * @return Ball's counter.
     */
    public Counter getBallsCounter() {
        return ballsCounter;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        if (this.sensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.sensor, this.sensor.SPACE_KEY, new PauseGame()));
        }
        this.sprites.notifyAllTimePassed();
        if (this.blocksCounter.getValue() >= this.info.numberOfBlocksToRemove()) {
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