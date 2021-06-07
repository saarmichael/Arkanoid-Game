// 315240937
// Michael Saar


package geometryprimatives;

/**
 * @author Michael Saar
 * @since 12-04-2021
 * <p>
 * this class use, is to create Linear Equation objects that represent linear lines with slope and y axis intercept
 * it also supports the representing of a linear line from the x = a form
 */
public class LinearEquation {

    private static final double INFINITY = 1.0 / 0.0;
    private static final double MINUS_INFINITY = -1.0 / 0.0;
    private static final double EPSILON = Math.pow(10, -10);


    private double slope;
    private double yIntercept;
    private double xValue;

    /**
     * for linear eq. y = ax + b:
     *
     * @param slope      is a
     * @param yIntercept is b
     *                   set the xValue field to be INFINITY
     */
    public LinearEquation(double slope, double yIntercept) {
        this.slope = slope;
        this.yIntercept = yIntercept;
        this.xValue = INFINITY;
    }

    /**
     * @param line the Line that 'sits' on the Linear EQ.
     *             this methods calculates the slope and y-intercept using start and end Points
     */
    public LinearEquation(Line line) {
        this.slope = findSlope(line.end(), line.start());
        this.yIntercept = findYIntercept(this.slope, line.end());
        // deal with case of lines of the form of x = const
        if (this.slope == INFINITY || this.slope == MINUS_INFINITY || line.start().equals(line.end())) {
            this.xValue = line.end().getX();
            this.slope = INFINITY;
            this.yIntercept = INFINITY;
        } else {
            this.xValue = INFINITY;
        }
    }

    /**
     * in order to create a Linear equation with the form of x = a.
     *
     * @param xValue is a (x = a)
     */
    public LinearEquation(double xValue) {
        this.slope = INFINITY;
        this.yIntercept = INFINITY;
        this.xValue = xValue;
    }

    @Override
    public String toString() {
        if (slope == INFINITY) {
            return "x = " + xValue;
        }
        return "LinearEquation{" + "slope=" + slope + ", yIntercept=" + yIntercept + '}';
    }


    /**
     * @param a a Point
     * @param b another Point
     * @return the slope stressed from a and b, according to the Mathematical formula slope = (y2- y1)/(x2-x1)
     */
    public double findSlope(Point a, Point b) {
        return ((a.getY() - b.getY()) / (a.getX() - b.getX()));
    }

    /**
     * @param lineSlope a given slope
     * @param p         a Point
     * @return the Y-intercept based on slope and a point (for the eq. y=ax + b, b = y-ax)
     */
    public double findYIntercept(double lineSlope, Point p) {
        if (lineSlope == INFINITY) {
            this.xValue = p.getX();
        }
        return (p.getY() - (lineSlope * p.getX()));
    }

    /**
     * @param equation the Linear equation to find an intersection Point with
     * @return the intersection Point between LinearEquation to equation
     */
    public Point findIntersect(LinearEquation equation) {
        // for y = ax + b and y = mx + n: x = (n - b)/(a - m)
        double deltaB = this.yIntercept - equation.getyIntercept();
        double deltaA = equation.getSlope() - this.slope;
        // if the equations are parallel or are the same equations
        if ((deltaA) == 0 || (equation.getSlope() == INFINITY && this.slope == INFINITY)) {
            return null;
        }
        // if one of the equations has slope of INFINITY, the intersection Point is slope*xValue + yIntercept
        if (this.slope == INFINITY) {
            return new Point(this.xValue, (equation.getSlope() * this.xValue) + equation.getyIntercept());
        }
        if (equation.getSlope() == INFINITY) {
            return new Point(equation.xValue, (this.slope * equation.getxValue()) + this.yIntercept);
        }
        // if the equations are not parallel and has a real-value slope
        double x = deltaB / deltaA;
        double y = (this.slope * x) + this.yIntercept;
        return new Point(x, y);
    }

    /**
     * @param equation LinearEquation to compare with
     * @return true if the equation are identical, false otherwise
     */
    public boolean isSameEquation(LinearEquation equation) {
        if (this.slope == INFINITY && equation.slope == INFINITY) {
            return (this.xValue == equation.xValue);
        }
        return (this.slope == equation.slope) && (this.yIntercept == equation.yIntercept);
    }

    /**
     * @param p Point to check
     * @return true if p is on the equation, false otherwise
     */
    public boolean isPointOnLinearEq(Point p) {
        if (this.slope == INFINITY) {
            if (p.getX() == this.xValue) {
                return true;
            }
        }
        // EPSILON helps to solve minor deviations due to Java real-numbers computing limitations
        return (Math.abs(p.getY() - ((this.slope * p.getX()) + this.yIntercept))) < EPSILON;
    }

    /**
     * @return equation's slope
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * @return equation's y-intercept
     */
    public double getyIntercept() {
        return this.yIntercept;
    }

    /**
     * @return equation's X-value
     */
    public double getxValue() {
        return this.xValue;
    }
}