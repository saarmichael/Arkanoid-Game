// 315240937
// Michael Saar


package arkanoidsprites;

import biuoop.DrawSurface;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

/**
 * @author Michael Saar
 * @since 17-04-2021
 * <p>
 * this Interface object that can be collided with. A Collidable Object has location and size, and the action that
 * happens when a collision occurs.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint  the Point where a Collision occurred
     * @param currentVelocity the Velocity of the object
     * @param hitter          the Ball which hits the Collidable
     * @return new Velocity according to object's Velocity and collision Point.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * @param surface the DrawSurface to draw Collidable on
     */
    void drawOn(DrawSurface surface);
}
