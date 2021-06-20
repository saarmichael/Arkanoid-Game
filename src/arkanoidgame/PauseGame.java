// 315240937
// Michael Saar

package arkanoidgame;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class responsible for the behaviour of a paused screen.
 *
 * @author Michael Saar
 * @since 20-06-2021
 */
public class PauseGame implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * change the inner state when the game has been paused.
     */
    public PauseGame() {
        this.stop = false;
    }

    /**
     * constructor. Assuming the Animation shouldn't stop as it is initialized.
     *
     * @param k a KeyboardSensor.
     */
    public PauseGame(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // draw stop message on the GUI
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        // update the Animation to inform who-ever runs it, it should stop.
        /*
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }

         */
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
