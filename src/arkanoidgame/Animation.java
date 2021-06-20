// 315240937
// Michael Saar

package arkanoidgame;


import biuoop.DrawSurface;

/**
 * this interface represents all classes that has a behaviour of an Animation.
 * An Animation has a graphic appearance (frame), and information regarding stopping conditions.
 *
 * @author Michael Saar
 * @since 20-06-2021
 */
public interface Animation {

    /**
     * the visual graphic appearance of the Animation.
     *
     * @param d DrawSurface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true if Animation should stop. False otherwise.
     */
    boolean shouldStop();

}