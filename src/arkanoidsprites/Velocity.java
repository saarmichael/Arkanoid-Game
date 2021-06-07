// 315240937
// Michael Saar


package arkanoidsprites;

import geometryprimatives.Point;

/**
 * @author Michael Saar
 * @since 12-04-2021
 * <p>
 * this class use, is to create Velocity objects that represent movement. The movement representation is achieved by
 * defying a two-dimensional vector (dx,dy) and applying its value to a given Point
 * </p>
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * @param dx x-value of Velocity vector
     * @param dy y-value of Velocity vector
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @param angle of Object's movement
     * @param speed of Object's movement
     * @return new Velocity represented by (x,y) vector
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        /* when speed represents the hypotenuse, and angle represents the angle between the vertical GUI side
         angle 0 direction is up.
         given angle and hypotenuse, vector (dx,dy) is received by applying simple trigonometry as seen below
         */
        double dx = speed * (Math.sin(Math.toRadians(angle)));
        double dy = -speed * (Math.cos(Math.toRadians(angle)));

        //double dx = speed * (Math.sin(Math.toDegrees(angle)));
        //double dy = speed * (Math.cos(Math.toDegrees(angle)));
        return new Velocity(dx, dy);
    }

    @Override
    public String toString() {
        return "Velocity{" + "dx=" + dx + ", dy=" + dy + '}';
    }

    /**
     * @return dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * @return the Velocity vector size (given by Pythagorean equation).
     */
    public int getSpeed() {
        return (int) Math.round(Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2)));
    }

    /**
     * @param newDx new dx value
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * @param newDy new dy value
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * @param p current Point with position (x,y)
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
