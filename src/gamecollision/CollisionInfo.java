// 315240937
// Michael Saar


package gamecollision;


import arkanoidsprites.Collidable;
import geometryprimatives.Point;

/**
 * @author Michael Saar
 * @since 20-04-2021
 * <p>
 * this class holds information about a Block and its collision Point
 */
public class CollisionInfo {

    private Point collision;
    private Collidable block;

    /**
     * @param collision the Point where a collision occurred
     * @param block     the Collidable Block which the collision occurred with
     *                  <p>
     *                  this is a constructor method
     */
    public CollisionInfo(Point collision, Collidable block) {
        this.block = block;
        this.collision = collision;
    }

    /**
     * @return the collision Point.
     */
    public Point getCollision() {
        return collision;
    }

    /**
     * @return the collision Block.
     */
    public Collidable getBlock() {
        return block;
    }

    /**
     * @param newCollision Point to set.
     */
    public void setCollision(Point newCollision) {
        this.collision = newCollision;
    }

    /**
     * @param newBlock collision to set.
     */
    public void setBlock(Collidable newBlock) {
        this.block = newBlock;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * @return the Collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.block;
    }
}