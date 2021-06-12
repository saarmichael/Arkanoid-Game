// 315240937
// Michael Saar

package arkanoidgame;


import arkanoidsprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.*;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
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

    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(new Color(14, 248, 35));
        d.drawText(341, (d.getHeight() / 2) + 2, String.valueOf(this.counter.getValue()), 42);
        d.setColor(Color.BLACK);
        d.drawText(344, d.getHeight() / 2, String.valueOf(this.counter.getValue()), 32);
        if (System.currentTimeMillis() > this.endTime) {
            this.stop = true;
            return;
        }
        if (this.timePassed >= (500 * this.countFrom / this.numOfSeconds)) {
            this.counter.decrease();
            this.timePassed = 0;
            this.iterateTime = System.currentTimeMillis();
        }
        this.timePassed = System.currentTimeMillis() - this.iterateTime;




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

    public boolean shouldStop() {
        return this.stop;
    }
}