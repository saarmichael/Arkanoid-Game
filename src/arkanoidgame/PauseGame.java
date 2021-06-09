package arkanoidgame;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class PauseGame implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;

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
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
