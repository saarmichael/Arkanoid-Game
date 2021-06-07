// 315240937
// Michael Saar

package arkanoidlisteners;


import arkanoidsprites.Ball;
import arkanoidsprites.Block;

/**
 * All classes that implements this interface are doing an action when a hitEvent occurs between a Ball and a Block.
 *
 * @author Michael Saar
 * @since 03-06-2021
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the hit BLock.
     * @param hitter   the hitting Ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
