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

import java.util.ArrayList;
import java.util.List;

// this should be like Game class!!
// should copy the class logic from there.
public class Game12345 extends GameLevel {

    private LevelInformation info;
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
    public Game12345(KeyboardSensor ks, AnimationRunner ar, LevelInformation le) {
        super(ks, ar, le);
        this.sensor = ks;
        this.runner = ar;
        this.info = le;
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.blocksCounter = new Counter(le.blocks().size());
        this.ballsCounter = new Counter(le.numberOfBalls());
        this.running = true;
    }

    private Paddle paddleCreator() {
        Rectangle paddleRect = new Rectangle(
                new Point(400 - (this.info.paddleWidth() / 2), 580), this.info.paddleWidth(), 10);
        Paddle gameLevelPaddle =
                new Paddle(paddleRect, this.info.paddleColor(), this.info.paddleSpeed(), 5);
        gameLevelPaddle.setKeyboard(this.sensor);
        gameLevelPaddle.addToGame(this);
        this.environment.setPaddle(gameLevelPaddle);
        return gameLevelPaddle;
    }

    private void ballsCreator() {
        for (int i = 0; i < this.info.numberOfBalls(); i++) {
            Ball b = new Ball(new Point(400, 585), 5,
                    this.info.initialBallColor().get(i), this.info.initialBallVelocities().get(i));
            b.setGameEnvironment(this.environment);
            b.addToGame(this);
        }
    }

    private void frameCreator() {
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
        HitListener ballRemover = new BallRemover(this, this.ballsCounter);
        bBot.addHitListener(ballRemover);
    }

    public void initialize() {
        this.sprites.getSprites().add(this.info.getBackground());
        HitListener blockRemover = new BlockRemover(this, this.blocksCounter);
        List<HitListener> gameLevelHitListeners = new ArrayList<>();
        gameLevelHitListeners.add(blockRemover);
        Paddle gameLevelPaddle = paddleCreator();
        this.ballsCreator();
        this.frameCreator();
        this.sprites.getSprites().addAll(this.info.blocks());
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
}
