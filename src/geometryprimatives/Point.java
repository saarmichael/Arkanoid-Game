// 315240937
// Michael Saar

package geometryprimatives;

import java.util.Random;

/**
 * @author Michael Saar
 * @since 12-04-2021
 * <p>
 * this class use, is to create Point objects that represent points with x and y values
 */
public class Point {

    private double x;
    private double y;

    private static final Point ORIGIN = new Point(0, 0);

    /**
     * @param x the x-value of the new point
     * @param y the y-value of the new point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param xBound the x-value of side border
     * @param yBound the y-value of bottom border
     * @return new random Point
     * <p>
     * creates a random Point within the given borders
     * this method is static in order to prevent confusion and auto-casting issues for class users
     */
    public static Point randomPoint(int xBound, int yBound) {
        Random rand = new Random();
        int x = rand.nextInt(xBound);
        int y = rand.nextInt(yBound);
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }

    /**
     * @return the Point {0,0}
     */
    public static Point getOrigin() {
        return ORIGIN;
    }

    /**
     * @return x
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return y
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param newX x-value
     *             setting a new x- value for the Point object
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * @param newY y-value
     *             setting a new y- value for the Point object
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     * @param other Point to measure distance from
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((other.getY() - this.y), 2) + Math.pow((other.getX() - this.x), 2));
    }

    /**
     * @param other Point to check if equal to
     * @return true if Points are equal. false else
     */
    public boolean equals(Point other) {
        return this.y == other.y && this.x == other.x;
    }

}

