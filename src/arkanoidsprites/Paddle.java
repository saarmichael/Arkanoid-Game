package arkanoidsprites;

// 315240937
// Michael Saar


import arkanoidgame.Game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.Color;

/**
 * this class represents a game paddle that can move left and right, has the properties of Collidable and Sprite.
 *
 * @author Michael Saar
 * @since 24-04-2021
 */
public class Paddle implements Sprite, Collidable {

    private static final double EPSILON = Math.pow(10, -10);

    private Rectangle rect;
    private Color color;
    private KeyboardSensor keyboard;
    private int speedFactor;
    private int sensitivityFactor;

    /**
     * constructor.
     *
     * @param rect              Rectangle size of Paddle
     * @param sensor            KeyboardSensor to activate Paddle
     * @param color             Paddle's Color
     * @param speedFactor       integer determines Paddle's speed
     * @param sensitivityFactor integer determine Paddle's sensitivity in terms of angle hits
     */
    public Paddle(Rectangle rect, KeyboardSensor sensor, Color color, int speedFactor, int sensitivityFactor) {
        this.rect = rect;
        this.keyboard = sensor;
        this.color = color;
        this.sensitivityFactor = sensitivityFactor;
        this.speedFactor = speedFactor;
    }

    /**
     * constructor.
     *
     * @param rect              Rectangle size of Paddle
     * @param color             Paddle's Color
     * @param speedFactor       integer determines Paddle's speed
     * @param sensitivityFactor integer determine Paddle's sensitivity in terms of angle hits
     */
    public Paddle(Rectangle rect, Color color, int speedFactor, int sensitivityFactor) {
        this.rect = rect;
        this.color = color;
        this.sensitivityFactor = sensitivityFactor;
        this.speedFactor = speedFactor;
    }

    /**
     * @return Paddle's Color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return Paddle's speed.
     */
    public int getSpeedFactor() {
        return speedFactor;
    }

    /**
     * @param sensor KeyboardSensor to set
     */
    public void setKeyboard(KeyboardSensor sensor) {
        this.keyboard = sensor;
    }

    /**
     * @param newColor Color to set
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * @param newRect Rectangle shape of the Paddle to set
     */
    public void setRect(Rectangle newRect) {
        this.rect = newRect;
    }

    /**
     * @param newSensitivityFactor sensitivityFactor to set
     */
    public void setSensitivityFactor(int newSensitivityFactor) {
        this.sensitivityFactor = newSensitivityFactor;
    }

    /**
     * @param newSpeedFactor speed to set to set
     */
    public void setSpeedFactor(int newSpeedFactor) {
        this.speedFactor = newSpeedFactor;
    }

    /**
     * move the Paddle to the left.
     */
    public void moveLeft() {
        Point newPosition = new Point(
                this.rect.getUpperLeft().getX() - this.speedFactor, this.rect.getUpperLeft().getY());
        this.rect = new Rectangle(newPosition, this.rect.getWidth(), this.rect.getHeight());
    }

    /**
     * move the Paddle to the right.
     */
    public void moveRight() {
        Point newPosition = new Point(
                this.rect.getUpperLeft().getX() + this.speedFactor, this.rect.getUpperLeft().getY());
        this.rect = new Rectangle(newPosition, this.rect.getWidth(), this.rect.getHeight());
    }


    /**
     * notify the paddle time has passed since last appearance. Move the Paddle according to keyboard keys.
     */
    public void timePassed() {
        // if both keys are pressed
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            return;
        }
        // move left if pressed
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (this.rect.getUpperLeft().getX() < 15) {
                return;
            }
            moveLeft();
        }
        // move right if pressed
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (this.rect.getUpperLeft().getX() > 785 - this.rect.getWidth()) {
                return;
            }
            moveRight();
        }
    }

    /**
     * draw the Paddle.
     *
     * @param d the DrawSurface to draw Collidable on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getTop().start().getX(), (int) this.rect.getTop().start().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getTop().start().getX(), (int) this.rect.getTop().start().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }


    /**
     * @return the Rectangle shape of the Paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        /*
          notify an object who has hit the Paddle, the hit occurred by returning its new Velocity,
          according to the area on the paddle in which the hit has occurred.
         */
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        Point nextNextOccurrence = new Point(
                collisionPoint.getX() + currentVelocity.getDx(), collisionPoint.getY() + currentVelocity.getDy());
        // if the hit occurred on the right or left sides of the Paddle, change the DX value to the opposite direction
        if (Math.abs(collisionPoint.getX() - this.rect.getUpperLeft().getX()) < EPSILON
                || Math.abs(collisionPoint.getX() - this.rect.getTop().end().getX()) < EPSILON
                || Math.abs(nextNextOccurrence.getX() - this.rect.getUpperLeft().getX() + this.speedFactor) < EPSILON
                || Math.abs(nextNextOccurrence.getX() - this.rect.getTop().end().getX() - this.speedFactor) < EPSILON) {
            newVelocity.setDx(-1 * currentVelocity.getDx());
        }
        // if the hit occurred on the top of thePaddle, change the DY value according to the hit area on the paddle
        if (Math.abs(collisionPoint.getY() - this.rect.getUpperLeft().getY()) < EPSILON) {
            newVelocity = topHit(collisionPoint, currentVelocity);
        }
        // if hit the bottom of the paddle
        if (Math.abs(collisionPoint.getY() - this.rect.getBottom().start().getY()) < EPSILON) {
            newVelocity = topHit(collisionPoint, currentVelocity);
        }

        return newVelocity;
    }

    /**
     * deals with case of hitting the top of the Paddle.
     *
     * @param collisionPoint  Point of collision
     * @param currentVelocity the Velocity of the object before the hit
     * @return new Velocity according to the area of the hit on the Paddle.
     */
    private Velocity topHit(Point collisionPoint, Velocity currentVelocity) {
        int hitArea = hitArea(collisionPoint);
        if (hitArea == 3) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        if (hitArea == 5) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        if (hitArea == 4) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        if (hitArea == 2) {
            return Velocity.fromAngleAndSpeed(-30, currentVelocity.getSpeed());
        }
        return Velocity.fromAngleAndSpeed(-60, currentVelocity.getSpeed());
    }

    /**
     * @param collisionPoint Point of collision.
     * @return the number of the hit area
     */
    private int hitArea(Point collisionPoint) {
        double areaSize = this.rect.getWidth() / this.sensitivityFactor;
        return (int) (((collisionPoint.getX() - this.rect.getUpperLeft().getX()) / areaSize)) + 1;
    }

    /**
     * @param g a Game the Block should be added to both as a Collidable and as a Sprite
     */
    @Override
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }


    @Override
    public void removeFromGame(Game game) {
        //empty for now.
        return;
    }
}


/*

************************************************
implementation for later
************************************************


    private Velocity topHit(Point collisionPoint, Velocity currentVelocity) {
        int hitArea = hitArea(collisionPoint);
        // if hit occurred on the center of the Paddle
        if (hitArea == (int) (this.sensitivityFactor / 2) + 1) {
            return Velocity.fromAngleAndSpeed(0, currentVelocity.getSpeed());
        } else if (hitArea <= this.sensitivityFactor / 2) {
            // if the hit occurred on the left side of the paddle
            return Velocity.fromAngleAndSpeed(
                    -60 * hitArea / ((int) this.sensitivityFactor / 2), currentVelocity.getSpeed());
        } else {
            // if the hit occurred on the right side of the paddle
            return Velocity.fromAngleAndSpeed((60 * (hitArea - this.sensitivityFactor / 2))
                    / ((int) this.sensitivityFactor / 2), currentVelocity.getSpeed());
        }
    }
 */