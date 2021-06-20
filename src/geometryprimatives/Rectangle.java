// 315240937
// Michael Saar

package geometryprimatives;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Saar
 * @since 16-04-2021
 * <p>
 * this class represents Rectangles that have four Lines -- top, bottom, right, left.
 */
public class Rectangle {

    private Line left;
    private Line right;
    private Line top;
    private Line bottom;

    /**
     * @param upperLeft top left Point
     * @param width     width of Rectangle
     * @param height    height of Rectangle
     *                  <p>
     *                  Create a new rectangle with location and width/height -- set the Lines according to parameters
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.top = new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));
        this.left = new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height));
        this.bottom = new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        this.right = new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }

    /**
     * @param right  right Line of Rectangle
     * @param left   left Line of Rectangle
     * @param top    top Line of Rectangle
     * @param bottom bottom Line of Rectangle
     */
    public Rectangle(Line right, Line left, Line top, Line bottom) {
        this.bottom = bottom;
        this.top = top;
        this.left = left;
        this.right = right;
    }


    /**
     * @param line Line to find intersection Points with
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        // check for every side of the Rectangle if line is intersecting with it
        if (line.isIntersecting(this.bottom)) {
            intersections.add(line.intersectionWith(this.bottom));
        }
        if (line.isIntersecting(this.top)) {
            intersections.add(line.intersectionWith(this.top));
        }
        if (line.isIntersecting(this.left)) {
            intersections.add(line.intersectionWith(this.left));
        }
        if (line.isIntersecting(this.right)) {
            intersections.add(line.intersectionWith(this.right));
        }
        // check if intersection were detected and returned null
        if (!intersections.isEmpty()) {
            boolean areAllNull = true;
            for (int i = 0; i < intersections.size(); i++) {
                if (intersections.get(i) != null) {
                    areAllNull = false;
                }
            }
            if (areAllNull) {
                intersections.clear();
            }
        }
        return intersections;
    }

    /**
     * @return the width and height of the rectangle
     */
    public double getWidth() {
        return this.top.end().getX() - this.top.start().getX();
    }

    /**
     * @return the height of the Rectangle
     */
    public double getHeight() {
        return this.right.end().getY() - this.right.start().getY();
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.top.start();
    }

    /**
     * @param p Point to check
     * @return if the Point is inside the Rectangle
     */
    public boolean isPointInRectangle(Point p) {
        return p.getX() < this.top.end().getX() && p.getX() > this.getUpperLeft().getX()
                && p.getY() > this.top.end().getY() && p.getY() < this.bottom.end().getY();
    }

    /**
     * @param p Point to check.
     * @return 1 if the point is in the right side of the Rectangle, esle returns -1
     */
    public int pointSideInBlock(Point p) {
        double middle = this.getUpperLeft().getX() + (this.getWidth() / 2);
        if (p.getX() >= middle) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * @return the bottom Line of the Rectangle
     */
    public Line getBottom() {
        return bottom;
    }

    /**
     * @return the left Line of the Rectangle
     */
    public Line getLeft() {
        return left;
    }

    /**
     * @return the right Line of the Rectangle
     */
    public Line getRight() {
        return right;
    }

    /**
     * @return the top Line of the Rectangle
     */
    public Line getTop() {
        return top;
    }

    /**
     * @param newBottom new bottom line
     */
    public void setBottom(Line newBottom) {
        this.bottom = newBottom;
    }

    /**
     * @param newLeft new left line
     */
    public void setLeft(Line newLeft) {
        this.left = newLeft;
    }

    /**
     * @param newRight new right line
     */
    public void setRight(Line newRight) {
        this.right = newRight;
    }

    /**
     * @param newTop new top line
     */
    public void setTop(Line newTop) {
        this.top = newTop;
    }


}
