// 315240937
// Michael Saar

package arkanoidlisteners;


import arkanoidgame.Counter;
import arkanoidgame.GameLevel;
import arkanoidsprites.Ball;
import arkanoidsprites.Block;

/**
 * this class removes the Ball when it hits a certain Block.
 *
 * @author Michael Saar
 * @since 03-06-2021
 */
public class BallRemover implements HitListener {

    private final GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param gameLevel          game.
     * @param removedBlocks number of remain Blocks in Game.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBlocks;
    }

    /**
     * setter for counter.
     *
     * @param remainingBallsUpdated new Counter.
     */
    public void setRemainingBalls(Counter remainingBallsUpdated) {
        this.remainingBalls = remainingBallsUpdated;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease();
        System.out.println(this.remainingBalls);
    }
}
