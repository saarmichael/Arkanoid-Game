// 315240937
// Michael Saar

package geometryprimatives;


import biuoop.DrawSurface;

import java.util.List;

/**
 * @author Michael Saar
 * @since 12-04-2021
 * <p>
 * this class is used to create Line objects that represent line with starting and ending Points
 */
public class Line {

    private static final double EPSILON = Math.pow(10, -10);

    private Point start;
    private Point end;

    /**
     * @param start the starting Point of the Line
     * @param end   the ending Point of the Line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @return the start Point of the Line
     */
    public Point getStart() {
        return start;
    }

    /**
     * @return the end Point of the Line
     */
    public Point getEnd() {
        return end;
    }

    /**
     * @param newStart Point to set as new start of Line
     */
    public void setStart(Point newStart) {
        this.start = newStart;
    }

    /**
     * @param newEnd Point to set as new start of Line
     */
    public void setEnd(Point newEnd) {
        this.end = newEnd;
    }

    /**
     * @param x1 x-value of starting Point
     * @param y1 y-value of starting Point
     * @param x2 x-value of ending Point
     * @param y2 y-value of ending Point
     *           creates new Line by creating new starting and ending Points
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    @Override
    public String toString() {
        return "Line{" + "start=" + this.start + ", end=" + this.end + '}';
    }

    /**
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return the middle Point of the line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * @return the starting Point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the ending Point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * @param rect the rectangle to find the closest intersection Point with
     * @return the closest intersection Point with rect
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        // if there are no intersection Points
        if (intersections.isEmpty()) {
            return null;
        }
        Point closest = intersections.get(0);
        // use the classic algorithm to find the minimal distance of all intersection Points
        double min = Math.abs(this.start.distance(closest));
        for (int i = 1; i < intersections.size(); i++) {
            double distance = Math.abs(this.start.distance(intersections.get(i)));
            if (distance < min) {
                closest = intersections.get(i);
                min = distance;
            }
        }
        return closest;
    }

    /**
     * @param other Line to compare
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        LinearEquation le = new LinearEquation(this);
        LinearEquation leOther = new LinearEquation(other);
        // if the lines are on the same linearEquation, they intersect
        if (le.isSameEquation(leOther)) {
            return true;
        }
        // find the intersection between the two Linear-Equations
        Point p = le.findIntersect(leOther);
        // if the lines are parallel or the same line
        if (p == null) {
            // if the lines are same, intersection exist iff the lines are touching exactly at one point
            return this.start.equals(other.end) || this.end.equals(other.start);
        }
        if ((p.getX() * Math.pow(10, 12)) % Math.pow(10, 12) != 0) {
            p.setX((p.getX() * Math.pow(10, 12)) / Math.pow(10, 12));
        }
        if ((p.getY() * Math.pow(10, 12)) % Math.pow(10, 12) != 0) {
            p.setY((p.getY() * Math.pow(10, 12)) / Math.pow(10, 12));
        }
        // if the linear lines intersect, check if intersection point is on both Lines
        return this.isPointOnLine(p) && other.isPointOnLine(p);
    }

    /**
     * @param other Line to compare with
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start)) && (this.end.equals(other.end)))
                || ((this.start.equals(other.end)) && (this.end.equals(other.start)));
    }

    /**
     * @param other Line to find intersection with
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // if the lines intersect, find the intersection point and return it. Else return null
        if (this.isIntersecting(other)) {
            if (this.start.equals(other.end)) {
                // if one of the lines is on the other line, but starts at its edge
                if (this.isPointOnLine(other.start) || other.isPointOnLine(this.end)) {
                    return null;
                }
                return this.start;
            }
            if (this.end.equals(other.start)) {
                // of ine of the lines is on the other line, but starts at its edge
                if (this.isPointOnLine(other.end) || other.isPointOnLine(this.start)) {
                    return null;
                }
                return this.end;
            }
            // find the intersection using LinearEquation
            LinearEquation le = new LinearEquation(this);
            LinearEquation leOther = new LinearEquation(other);
            return le.findIntersect(leOther);
        }
        return null;
    }

    /**
     * @param p Point to check if on Line
     * @return true of p is on Line, false otherwise
     */
    public boolean isPointOnLine(Point p) {
        if (p == null) {
            return false;
        }
        LinearEquation equation = new LinearEquation(this);
        // if p is on LinearEquation that the Line is on, check if p is between start and end Points
        if (equation.isPointOnLinearEq(p)) {


            if (Math.abs(p.getX() - Math.round(p.getX())) < EPSILON) {
                p.setX(Math.round(p.getX()));
            }
            if (Math.abs(p.getY() - Math.round(p.getY())) < EPSILON) {
                p.setY(Math.round(p.getY()));
            }


            return ((p.getX() >= this.end.getX() && p.getX() <= this.start.getX())
                    || (p.getX() >= this.start.getX() && p.getX() <= this.end.getX()))
                    && (((p.getY() >= this.end.getY() && p.getY() <= this.start.getY())
                    || (p.getY() >= this.start.getY() && p.getY() <= this.end.getY())));
        }
        return false;
    }

    /**
     * drawing the line.
     *
     * @param d to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
    }

}