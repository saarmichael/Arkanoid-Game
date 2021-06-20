// 315240937
// Michael Saar

package arkanoidlisteners;

import arkanoidgame.Counter;
import arkanoidsprites.Ball;
import arkanoidsprites.Block;

/**
 * this Listener Class is responsible to keep track on game's score.
 *
 * @author Michael Saar
 * @since 03-06-2021
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter Counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * setter for currentScore.
     *
     * @param newCurrentScore new current score.
     */
    public void setCurrentScore(Counter newCurrentScore) {
        this.currentScore = newCurrentScore;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //a hit worth 5 points.
        this.currentScore.increase(5);
        // System.out.println(this.currentScore);
    }
}