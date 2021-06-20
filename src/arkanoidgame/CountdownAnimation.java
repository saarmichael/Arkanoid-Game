// 315240937
// Michael Saar

package arkanoidgame;


import arkanoidsprites.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will appear on the screen for
 * (numOfSeconds / countFrom) seconds, before it is replaced with the next one.
 *
 * @author Michael Saar
 * @since 20-06-2021
 */
public class CountdownAnimation implements Animation {

    private boolean stop;
    private double numOfSeconds;
    private int countFrom;
    private Counter counter;
    private SpriteCollection gameScreen;
    private long startTime;
    private long timePassed;
    private long iterateTime;
    private long endTime;
    private double size = 7;

    /**
     * constructor.
     *
     * @param numOfSeconds for the Count to last.
     * @param countFrom    the number to start countdown from.
     * @param gameScreen   the Sprites to display in the background.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.counter = new Counter(countFrom);
        this.gameScreen = gameScreen;
        this.stop = false;
        this.startTime = System.currentTimeMillis();
        this.endTime = (long) (System.currentTimeMillis() + (numOfSeconds * 1000));
        this.timePassed = 0;
        this.iterateTime = System.currentTimeMillis();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(new Color(27, 73, 33));
        // d.fillCircle(395, (d.getHeight() / 2) - 20, 50);
        d.setColor(Color.BLACK);
        d.drawText(345, (d.getHeight() / 2) + 2, String.valueOf(this.counter.getValue()), (int) (this.size * 42));
        d.setColor(new Color(72, 229, 211));
        d.drawText(348, d.getHeight() / 2, String.valueOf(this.counter.getValue()), (int) (this.size * 32));
        if (System.currentTimeMillis() > this.endTime) {
            this.stop = true;
            return;
        }
        if (this.timePassed >= (500 * this.countFrom / this.numOfSeconds)) {
            this.counter.decrease();
            this.timePassed = 0;
            this.iterateTime = System.currentTimeMillis();
            this.size = 7;
        }
        this.timePassed = System.currentTimeMillis() - this.iterateTime;
        this.size = this.size - 0.07;




        /*
        Sleeper sleeper = new Sleeper();
        long startTime = System.currentTimeMillis();
        long endTime = (long) (startTime + this.numOfSeconds);
        long currentTime = startTime;
        d.drawText(10, d.getHeight() / 2, String.valueOf(this.countFrom), 32);
        sleeper.sleepFor(100);

        while (currentTime < endTime) {
            this.gameScreen.drawAllOn(d);
            d.drawText(10, d.getHeight() / 2, String.valueOf(this.countFrom), 32);
            countFrom--;
            sleeper.sleepFor((long) (this.countFrom / this.numOfSeconds));
            currentTime = System.currentTimeMillis();
        }


        this.stop = true;

         */
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}