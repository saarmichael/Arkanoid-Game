// 315240937
// Michael Saar

package arkanoidsprites;


import arkanoidgame.GameLevel;
import biuoop.DrawSurface;
import gamecollision.CollisionInfo;
import geometryprimatives.Line;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.Color;
import java.util.Random;

/**
 * @author Michael Saar
 * @since 24-04-2021
 * <p>
 * this class use, is to create Ball objects that represent circles with center Point, radius, color and Velocity.
 */

public class Ball implements Sprite {

    private static final int DEFAULT_END_FRAME_X = 775;
    private static final int DEFAULT_END_FRAME_Y = 590;
    private static final int DEFAULT_RADIUS = 1;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private static final Velocity DEFAULT_VELOCITY = Velocity.fromAngleAndSpeed(0, 0);

    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private Point frameStart;
    private Point frameEnd;
    private GameEnvironment gEnv;

    /**
     * @param center     Point stating the Ball's center
     * @param r          Ball's radius
     * @param color      Ball's color
     * @param velocity   Ball's Velocity
     * @param frameStart Point of top left frame-corner
     * @param frameEnd   Point of bottom right frame-corner
     */
    public Ball(Point center, int r, Color color, Velocity velocity, Point frameStart, Point frameEnd) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = velocity;
        this.frameStart = frameStart;
        this.frameEnd = frameEnd;
    }

    /**
     * @param center   Point stating the Ball's center
     * @param r        Ball's radius
     * @param color    Ball's color
     * @param velocity Ball's Velocity
     *                 this is another constructor to create Balls without stated frameStart and frameEnd
     */
    public Ball(Point center, int r, Color color, Velocity velocity) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = velocity;
        this.frameStart = Point.getOrigin();
        this.frameEnd = new Point(DEFAULT_END_FRAME_X, DEFAULT_END_FRAME_Y);
    }

    /**
     * @param center Point stating the Ball's center
     * @param r      Ball's radius
     * @param color  Ball's color
     *               <p>
     *               this is another constructor to create Balls without stated Velocity, frameStart and frameEnd
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = DEFAULT_VELOCITY;
        frameStart = Point.getOrigin();
        frameEnd = new Point(DEFAULT_END_FRAME_X, DEFAULT_END_FRAME_Y);
    }

    /**
     * @param xCenter x-Value of Ball's center Point
     * @param yCenter y-Value of Ball's center Point
     * @param r       Ball's size
     * @param color   Ball's color
     *                <p>
     *                this is another constructor to create Balls without stated Velocity,
     *                frameStart and frameEnd, and with given
     *                center-Point x and y values
     */
    public Ball(double xCenter, double yCenter, int r, Color color) {
        this.center = new Point(xCenter, yCenter);
        this.radius = r;
        this.color = color;
        this.velocity = DEFAULT_VELOCITY;
        this.frameStart = Point.getOrigin();
        this.frameEnd = new Point(DEFAULT_END_FRAME_X, DEFAULT_END_FRAME_Y);
    }

    /**
     * this constructor creates new 'empty Ball'.
     */
    public Ball() {
        this.center = Point.getOrigin();
        this.radius = DEFAULT_RADIUS;
        this.color = DEFAULT_COLOR;
        this.velocity = DEFAULT_VELOCITY;
        frameStart = Point.getOrigin();
        frameEnd = new Point(DEFAULT_END_FRAME_X, DEFAULT_END_FRAME_Y);
    }

    /**
     * @return center x-value
     */
    public double getX() {
        return this.center.getX();
    }

    /**
     * @return center y-value
     */
    public double getY() {
        return this.center.getY();
    }

    /**
     * @return Ball's radius (size)
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return Ball's color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return Ball's Velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * @return Point of frameStart
     */
    public Point getFrameStart() {
        return frameStart;
    }

    /**
     * @return Point of frameEnd
     */
    public Point getFrameEnd() {
        return frameEnd;
    }

    /**
     * @return the Ball's GameEnvironment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gEnv;
    }

    /**
     * @param newCenter new Point to set Ball's center to
     *                  <p>
     *                  this method sets a new Ball's center Point given a Point
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    /**
     * @param x new Point's x-value
     * @param y new Point's y-value
     *          <p>
     *          this method sets a new Ball's center Point given x and y values
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * @param r new Ball size
     *          <p>
     *          this method sets a new Ball's size
     */
    public void setRadius(int r) {
        this.radius = r;
    }

    /**
     * @param newColor new Ball Color
     *                 <p>
     *                 this method sets a new Ball's color
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * @param v new Velocity to be set
     *          <p>
     *          this method sets the Ball's Velocity using Velocity argument
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * @param dx dx value to create new Velocity
     * @param dy dy value to create new Velocity
     *           <p>
     *           set the Ball's Velocity using dx,dy arguments
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @param newFrameStart Point of the top left frame corner
     */
    public void setFrameStart(Point newFrameStart) {
        this.frameStart = newFrameStart;
    }

    /**
     * @param newFrameEnd Point of the bottom right frame corner
     */
    public void setFrameEnd(Point newFrameEnd) {
        this.frameEnd = newFrameEnd;
    }

    /**
     * @param topBound    bound to value of new center Point
     * @param bottomBound bound to value of new center Point
     *                    <p>
     *                    this method sets a new random center Point to the Ball within bounds
     */
    public void setRandomCenter(Point topBound, Point bottomBound) {
        Random rand = new Random();
        int width = (int) bottomBound.getX() - (int) topBound.getX();
        int height = (int) bottomBound.getY() - (int) topBound.getY();
        // achieve random Point within bounds by randomising the Point in range of bottom - top, and then adding top
        this.center = new Point(rand.nextInt(width) + topBound.getX(), rand.nextInt(height) + topBound.getY());
    }

    /**
     * @param bound bound to value of new Ball size (radius)
     *              <p>
     *              this method sets a new random radius of the Ball, not larger than bound
     */
    public void setRandomRadius(int bound) {
        Random rand = new Random();
        this.radius = rand.nextInt(bound);
    }

    /**
     * this method sets a new Color to the Ball.
     */
    public void setRandomColor() {
        Random rand = new Random();
        this.color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

    /**
     * @param colorToAvoid a Color that should not be generated
     *                     this method sets a new Color to the Ball, that is different than colorToAvoid
     */
    public void setRandomColor(Color colorToAvoid) {
        Random rand = new Random();
        this.color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        while (this.color.equals(colorToAvoid)) {
            this.color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        }
    }

    /**
     * @param bound bound to value of new Ball Velocity
     *              <p>
     *              this method sets a new random Velocity to the Ball, not larger than bound
     */
    public void setRandomVelocity(int bound) {
        Random rand = new Random();
        this.velocity = new Velocity(rand.nextInt(bound), rand.nextInt(bound));
    }

    /**
     * @param newGameEnv the GameEnvironment to add
     *                   <p>
     *                   set a new GameEnv to the Ball
     */
    public void setGameEnvironment(GameEnvironment newGameEnv) {
        this.gEnv = newGameEnv;
    }

    /**
     * @param surface a DrawSurface with bounds
     *                <p>
     *                this method fix the Ball's size and position according to frame size and position
     */
    public void fixBallPosition(DrawSurface surface) {
        Point bound = new Point(surface.getWidth(), surface.getHeight());
        fixBallPosition(Point.getOrigin(), bound);
    }

    @Override
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    /**
     * @param gameLevel a Game the Ball should be added to
     */
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * @param givenFrameStart the Point of frame's top left corner
     * @param givenFrameEnd   the Point of frame's bottom right corner
     *                        <p>
     *                        this method fix the Ball's size and position according to frame size and position
     */
    public void fixBallPosition(Point givenFrameStart, Point givenFrameEnd) {
        // make sure that the radius is positive
        this.radius = Math.abs(this.radius);
        int width = (int) givenFrameEnd.getX() - (int) givenFrameStart.getX();
        int height = (int) givenFrameEnd.getY() - (int) givenFrameStart.getY();
        // fix ball's radius in case of radius larger tha GUI height or width
        if ((this.radius * 2) > Math.min(height, width)) {
            this.radius = (Math.min(height, width)) / 4;
        }

        /* fix ball's center if it is straying out of GUI's borders. There are four cases:
        1. the ball is straying from the left border (width 0)
        2. the ball is straying from the top border (height 0)
        3. the ball is straying from the right border (max width)
        4. the ball is straying from the bottom border (max height)
         */
        if (this.center.getX() < givenFrameStart.getX()) {
            this.velocity.setDx(-this.velocity.getDx());
            this.center.setX(radius + givenFrameStart.getX());
        }
        if (this.center.getY() < givenFrameStart.getY()) {
            this.velocity.setDy(-this.velocity.getDy());
            this.center.setY(radius + givenFrameStart.getY());
        }
        if (this.center.getX() > givenFrameEnd.getX()) {
            this.velocity.setDx(-this.velocity.getDx());
            this.center.setX(givenFrameEnd.getX() - this.radius);
        }
        if (this.center.getY() > givenFrameEnd.getY()) {
            this.velocity.setDy(-this.velocity.getDy());
            this.center.setY(givenFrameEnd.getY() - this.radius);
        }
    }

    /**
     * this method fix the Ball's size and position according to its own frame size and position.
     */
    public void fixBallPosition() {
        fixBallPosition(this.frameStart, this.frameEnd);
    }

    /**
     * @param block the Block to check if the Ball is inside of
     * @return true if the Ball is inside the Block
     */
    public boolean isBallInBlock(Block block) {
        Rectangle rect = block.getCollisionRectangle();
        return rect.isPointInRectangle(this.center);
    }

    /**
     * this method changes Ball's position on screen according to its frame bounds and Velocity.
     */
    public void moveOneStep() {
        fixBallPosition();
        Paddle gamePaddle = this.gEnv.getPaddle();
        Rectangle paddleRect = gamePaddle.getCollisionRectangle();
        if (paddleRect.isPointInRectangle(this.center)) {
            if (paddleRect.pointSideInBlock(this.center) == 1) {
                this.setCenter(
                        paddleRect.getRight().getEnd().getX() + (5 * gamePaddle.getSpeedFactor()),
                        paddleRect.getUpperLeft().getY() + 1);
                // if the direction of the paddle and the ball are countered, change Velocity's Dx
                if (this.velocity.getDx() < 0) {
                    this.setVelocity(-1 * this.velocity.getDx(), this.velocity.getDy());
                }
            } else {
                this.setCenter(
                        paddleRect.getLeft().getEnd().getX() - (5 * gamePaddle.getSpeedFactor()),
                        paddleRect.getUpperLeft().getY() + 1);
                // if the direction of the paddle and the ball are countered, change Velocity's Dx
                if (this.velocity.getDx() > 0) {
                    this.setVelocity(-1 * this.velocity.getDx(), this.velocity.getDy());
                }
            }
            this.setVelocity(this.velocity.getDx(), this.velocity.getDy());
        }
        // set new Point representing next Ball's occurrence, and get the trajectory based on it
        Point nextOccurrence = new Point(this.velocity.getDx() + this.getX(), this.velocity.getDy() + this.getY());
        Line trajectory = new Line(this.center, nextOccurrence);
        CollisionInfo colInfo = this.gEnv.getClosestCollision(trajectory);
        if (colInfo != null) {

            /*
            if (this.gEnv.getCornerFlag() == 1) {
                this.setVelocity(-this.velocity.getDx(), -this.getVelocity().getDy());
                this.gEnv.setCornerFlag(0);
                return;
            }

            */

            // if the closest collision Point is on trajectory (to the next occurrence) a hit will occur
            this.setVelocity(colInfo.collisionObject().hit(this, colInfo.collisionPoint(), this.getVelocity()));
            Point nextNextOccurrence = new Point(
                    this.getX() + this.velocity.getDx(), this.velocity.getDy() + this.getY());
            Line nextTrajectory = new Line(this.center, nextNextOccurrence);
            CollisionInfo nextColInfo = this.gEnv.getClosestCollision(nextTrajectory);

            if (nextColInfo != null) {
                this.setVelocity(
                        nextColInfo.collisionObject().hit(this, nextColInfo.collisionPoint(), this.getVelocity()));
                this.setCenter(new Point(
                        colInfo.collisionPoint().getX() - (0.1 * this.velocity.getDx()),
                        colInfo.collisionPoint().getY() - (0.1 * this.velocity.getDy())));
            }
            return;
        }
        // set the new position of the Ball
        this.setCenter(this.getVelocity().applyToPoint(this.center));
    }

    /**
     * @param givenFrameStart the top left frame corner
     * @param givenFrameEnd   the bottom right frame corner
     *                        <p>
     *                        this method changes Ball's position on screen according to its frame bounds and Velocity.
     */
    public void moveOneStep(Point givenFrameStart, Point givenFrameEnd) {
        // if the ball is straying out of the vertical bounds, change its center Point to be as close as possible
        // to frame edge and change its Velocity to -dy
        if (!this.isBallOnVertical(givenFrameStart, givenFrameEnd)) {
            if (this.isBallStrayingToUp(givenFrameStart)) {
                this.setCenter(this.getX(), givenFrameStart.getX() + this.radius + this.velocity.getDy());
            } else if (this.isBallStrayingToDown(givenFrameEnd)) {
                this.setCenter(this.getX(), givenFrameEnd.getY() - this.radius + this.velocity.getDy());
            }
            this.velocity.setDy(-this.velocity.getDy());
        }
        // if the ball is straying out of the horizontal bounds, change its center Point to be as close as possible
        // to frame edge and change its Velocity to -dx
        if (!this.isBallOnHorizontal(givenFrameStart, givenFrameEnd)) {
            if (this.isBallStrayingToLeft(givenFrameStart)) {
                this.setCenter(givenFrameStart.getX() + this.radius + this.velocity.getDx(), this.getY());
            } else if (this.isBallStrayingToRight(givenFrameEnd)) {
                this.setCenter(givenFrameEnd.getX() - this.radius + this.velocity.getDx(), this.getY());
            }
            this.velocity.setDx(-this.velocity.getDx());
        }
        //  move the Ball's center according to its Velocity
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * @param surface given DrawSurface to move the Ball on
     */
    public void moveOneStep(DrawSurface surface) {
        moveOneStep(Point.getOrigin(), new Point(surface.getWidth(), surface.getHeight()));
    }

    /**
     * @param givenFrameEnd Point to compare to
     * @return true if the Ball is straying out from the right
     */
    public boolean isBallStrayingToRight(Point givenFrameEnd) {
        return this.center.getX() + this.radius + (this.velocity.getDx()) > givenFrameEnd.getX();
    }

    /**
     * @param givenFrameStart Point to compare to
     * @return true if the Ball is straying out from the left
     */
    public boolean isBallStrayingToLeft(Point givenFrameStart) {
        return this.center.getX() - this.radius + (this.velocity.getDx()) < givenFrameStart.getX();
    }

    /**
     * @param givenFrameStart Point to compare to
     * @return true if the Ball is straying out from the up
     */
    public boolean isBallStrayingToUp(Point givenFrameStart) {
        return this.center.getY() - this.radius + (this.velocity.getDy()) < givenFrameStart.getY();
    }

    /**
     * @param givenFrameEnd Point to compare to
     * @return true if the Ball is straying out from the up
     */
    public boolean isBallStrayingToDown(Point givenFrameEnd) {
        return this.center.getY() + this.radius + (this.velocity.getDy()) > givenFrameEnd.getY();
    }

    /**
     * @return false if the ball is strayed out of its own frame's horizontal bounds. Else, returns true.
     */
    public boolean isBallOnHorizontal() {
        return isBallOnHorizontal(this.frameStart, this.frameEnd);
    }

    /**
     * @return false if the ball is strayed out of its own frame's vertical bounds. Else, returns true.
     */
    public boolean isBallOnVertical() {
        return isBallOnVertical(this.frameStart, this.frameEnd);
    }

    /**
     * @param givenFrameStart top left corner of GUI
     * @param givenFrameEnd   bottom right corner of GUI
     * @return false if the ball is strayed out of the given frame's horizontal bounds. Else, returns true.
     */
    public boolean isBallOnHorizontal(Point givenFrameStart, Point givenFrameEnd) {
        if (isBallStrayingToRight(givenFrameEnd) || isBallStrayingToLeft(givenFrameStart)) {
            return false;
        }
        return true;
    }

    /**
     * @param surface a given DrawSurface with width and height
     * @return false if the ball is strayed out of the given DrawSurface horizontal bounds. Else, returns true
     */
    public boolean isBallOnHorizontal(DrawSurface surface) {
        if ((this.center.getX() - this.radius < 0) || this.center.getX() + this.radius > surface.getWidth()) {
            return false;
        }
        return true;
    }

    /**
     * @param givenFrameStart top left corner of GUI
     * @param givenFrameEnd   bottom right corner of GUI
     * @return false if the ball is strayed out of the given frame's vertical bounds. Else, returns true
     */
    public boolean isBallOnVertical(Point givenFrameStart, Point givenFrameEnd) {

        if (isBallStrayingToUp(givenFrameStart) || isBallStrayingToDown(givenFrameEnd)) {
            return false;
        }
        return true;
    }

    /**
     * @param surface DrawSurface to check
     * @return false if the ball is strayed out of the given DrawSurface vertical bounds. Else, returns true
     */
    public boolean isBallOnVertical(DrawSurface surface) {
        if ((this.center.getY() - this.radius < 0) || this.center.getY() + this.radius > surface.getHeight()) {
            return false;
        }
        return true;
    }

    /**
     * @param surface DrawSurface to check
     * @return false if the ball is strayed out of the surface's bounds. else, returns true
     */
    public boolean isBallInSurface(DrawSurface surface) {
        return this.isBallOnHorizontal(surface) && this.isBallOnVertical(surface);
    }

    /**
     * @param surface DrawSurface to draw the Ball on
     *                draw the ball on the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * this method relates to Sprite Interface and when a Ball is treated as a Sprite, it notifies the Ball time has
     * passed, which means the Ball should move. The movements is implemented by using the moveOneStep method.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }


}