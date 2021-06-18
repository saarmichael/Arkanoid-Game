package arkanoidgame;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation{

    private KeyboardSensor keyboard;
    private String stopKey;
    private Animation animation;
    private boolean stop;
    private boolean isKeyPressed;

    public KeyPressStoppableAnimation (KeyboardSensor ks, String key, Animation animation) {
        this.keyboard = ks;
        this.stopKey = key;
        this.animation = animation;
        if (ks.isPressed(key)) {
            this.isKeyPressed = true;
        }
        else {
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

    @Override
    public void setStop(boolean newCondition) {
        this.stop = newCondition;
    }
}
