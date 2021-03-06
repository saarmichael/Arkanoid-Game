// 315240937
// Michael Saar

package arkanoidlisteners;


import arkanoidgame.Counter;
import arkanoidgame.GameLevel;
import arkanoidsprites.Ball;
import arkanoidsprites.Block;

/**
 * this class removes the Block when it get hit by a certain Ball.
 *
 * @author Michael Saar
 * @since 03-06-2021
 */
public class BlockRemover implements HitListener {


    private final GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param gameLevel          game.
     * @param removedBlocks number of remain Blocks in Game.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * setter for remaining Blocks.
     *
     * @param remains ne Counter.
     */
    public void setRemainingBlocks(Counter remains) {
        this.remainingBlocks = remains;
    }

    /**
     * getter for remaining Blocks.
     *
     * @return remainingBlocks.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     *
     * @param beingHit the Block.
     * @param hitter   the hitting Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.increase();
        hitter.setColor(beingHit.getColor());
    }
}