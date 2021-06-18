package arkanoidgame;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


public class EndGameScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private boolean hasWon;

    public EndGameScreen(KeyboardSensor ks, Counter c, boolean hasWon) {
        this.keyboard = ks;
        this.stop = false;
        this.score = c;
        this.hasWon = hasWon;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // draw stop message on the GUI
        if (this.hasWon) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is  " + this.score.getValue(), 32);
        }
        else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
        }
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

    @Override
    public void setStop(boolean newCondition) {
        this.stop = newCondition;
    }
}
