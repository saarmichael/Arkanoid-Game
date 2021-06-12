package arkanoidgame;

import arkanoidlisteners.BlockRemover;
import arkanoidlisteners.HitListener;
import arkanoidsprites.Collidable;
import arkanoidsprites.GameEnvironment;
import arkanoidsprites.Sprite;
import arkanoidsprites.SpriteCollection;
import biuoop.KeyboardSensor;

// this should be like Game class!!
// should copy the class logic from there.
public class GameLevel {

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
    public GameLevel(KeyboardSensor ks, AnimationRunner ar, LevelInformation le) {
        this.sensor = ks;
        this.runner = ar;
        this.info = le;
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.sprites.getSprites().addAll(le.blocks());
        this.blocksCounter = new Counter(le.blocks().size());
        this.ballsCounter = new Counter(le.numberOfBalls());
        this.running = true;
    }


    public void initialize() {
        HitListener blockRemover = new BlockRemover(this, this.blocksCounter);
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

}
