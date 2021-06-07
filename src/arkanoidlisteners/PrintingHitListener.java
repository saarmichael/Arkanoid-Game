package arkanoidlisteners;


import arkanoidsprites.Ball;
import arkanoidsprites.Block;

/**
 * this Listener Class responsible to print message when a Block is hit.
 *
 * @author Michael Saar
 * @since 03-06-2021
 */
public class PrintingHitListener implements HitListener {

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }

}
