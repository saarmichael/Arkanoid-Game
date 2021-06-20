package arkanoidgame;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this is a rapper-class for Animation classes.
 * this class is responsible for stopping behaviour that invoked from key-pressing.
 *
 * @author Michael Saar
 * @since 20-06-2021
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboard;
    private String stopKey;
    private Animation animation;
    private boolean stop;
    private boolean isKeyPressed;

    /**
     * constructor.
     *
     * @param ks        KeyboardSensor
     * @param key       stopping key.
     * @param animation Animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor ks, String key, Animation animation) {
        this.keyboard = ks;
        this.stopKey = key;
        this.animation = animation;
        if (ks.isPressed(key)) {
            this.isKeyPressed = true;
        } else {
            this.isKeyPressed = false;
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed(this.stopKey) && !this.isKeyPressed) {
            this.stop = true;
        }
        if (!this.keyboard.isPressed(this.stopKey)) {
            this.isKeyPressed = false;
        }
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
