// 315240937
// Michael Saar

package arkanoidlisteners;


import arkanoidgame.Counter;
import arkanoidgame.Game;
import arkanoidsprites.Ball;
import arkanoidsprites.Block;

/**
 * this class removes the Ball when it hits a certain Block.
 *
 * @author Michael Saar
 * @since 03-06-2021
 */
public class BallRemover implements HitListener {

    private final Game game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game          game.
     * @param removedBlocks number of remain Blocks in Game.
     */
    public BallRemover(Game game, Counter removedBlocks) {
        this.game = game;
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
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease();
    }
}
