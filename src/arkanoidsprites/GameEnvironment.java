// 315240937
// Michael Saar

package arkanoidsprites;

import biuoop.DrawSurface;
import gamecollision.CollisionInfo;
import geometryprimatives.Line;
import geometryprimatives.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Saar
 * @since 18-04-2021
 * <p>
 * this class responsible to hold information about all Objects and relevant actions to a game which composed of
 * Collidable Objects.
 */
public class GameEnvironment {

    private List<Collidable> collidables;
    private Paddle paddle;
    private int cornerFlag;

    /**
     * creates an ArrayList of Collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
        this.paddle = null;
        this.cornerFlag = 0;
    }

    /**
     * @param newCornerFlag 1 if a corner hit has happened, 0 after the hit occurs
     */
    public void setCornerFlag(int newCornerFlag) {
        this.cornerFlag = newCornerFlag;
    }

    /**
     * @return the cornerFlag flag field.
     */
    public int getCornerFlag() {
        return cornerFlag;
    }

    /**
     * @param collidablesList a collidables List to set.
     */
    public void setCollidables(List<Collidable> collidablesList) {
        this.collidables = collidablesList;
    }

    /**
     * @param newPaddle set a new Game Paddle.
     */
    public void setPaddle(Paddle newPaddle) {
        this.paddle = newPaddle;
    }

    /**
     * @return Paddle.
     */
    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * @param collidableList a given List of Colliadables
     *                       creates a new GameEnvironment with a given Collidables List.
     */
    public GameEnvironment(ArrayList<Collidable> collidableList) {
        this.collidables = collidableList;
    }

    /**
     * @param c a Collidable to add
     *          add the given collidable to the environment.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * remove Collidable c from the gameEnvironment.
     *
     * @param c the Collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * @return the Collidables List
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * @param trajectory a Line representing Object's trajectory from current Point to its next appearance.
     * @return a CollisionInfo List about all possible collisions of trajectory and the GameEnv Collidables.
     */
    public List<CollisionInfo> collisions(Line trajectory) {
        List<CollisionInfo> allCollisions = new ArrayList<>();
        //run through all collidables
        for (int i = 0; i < this.collidables.size(); i++) {
            List<Point> collisionPointsWithRect =
                    this.collidables.get(i).getCollisionRectangle().intersectionPoints(trajectory);
            // if collisions detected
            if (!collisionPointsWithRect.isEmpty()) {
                // add all collisions with Block i to the List
                for (int k = 0; k < collisionPointsWithRect.size(); k++) {
                    Point p = collisionPointsWithRect.get(k);
                    if (Math.abs(p.getX() - Math.round(p.getX())) < 0.2) {
                        p.setX(Math.round(p.getX()));
                    }
                    if (Math.abs(p.getY() - Math.round(p.getY())) < 0.2) {
                        p.setY(Math.round(p.getY()));
                    }
                    allCollisions.add(new CollisionInfo(p, this.collidables.get(i)));
                }
            }
        }
        return allCollisions;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory Line to find the closest collision to.
     * @return a CollisionInfo about the closest intersection Point with trajectory
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // get all actual collisions trajectory has with Collidables in GameEnv
        List<CollisionInfo> allCollisions = this.collisions(trajectory);
        // min initialized to have the longest possible distance between two Points on GUI, +1
        double min = 1001;
        // initialize a Point and a Block to be updated later on
        Point closestPoint = null;
        Collidable collisionBlock = null;
        // if no collisions detected
        if (allCollisions.isEmpty()) {
            return null;
        }
        // for each CollisionInfo check which is the closest to start of trajectory
        for (CollisionInfo collision : allCollisions) {
            // if found closer collision Point
            if (collision.collisionPoint().distance(trajectory.start()) < min) {
                closestPoint = collision.collisionPoint();
                collisionBlock = collision.collisionObject();
                min = collision.collisionPoint().distance(trajectory.start());
            } else if (collision.collisionPoint().distance(trajectory.start()) == min) {
                // if there are two equally distant Blocks
                setCornerFlag(1);
            }
        }
        return new CollisionInfo(closestPoint, collisionBlock);
    }

    /**
     * @param surface a DrawSurface to draw on
     *                <p>
     *                draw all collidables that belong to GameEnvironment
     */
    public void drawCollidables(DrawSurface surface) {
        for (Collidable collidable : this.collidables) {
            collidable.drawOn(surface);
        }
    }

}