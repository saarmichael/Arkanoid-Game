// 315240937
// Michael Saar

package arkanoidsprites;

import biuoop.DrawSurface;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.Color;

/**
 * this class handles the background graphics for Level1 class.
 *
 * @author Michael Saar
 * @since 20-06-2021
 */
public class Level1Background extends Block {

    /**
     * constructor.
     *
     * @param width  of the Block
     * @param height of the Block
     */
    public Level1Background(int width, int height) {
        super(new Rectangle(new Point(0, 0), width, height));
        super.setColor(Color.BLACK);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);
        surface.setColor(Color.BLUE);
        surface.drawCircle(400, 200, 50);
        surface.drawCircle(400, 200, 75);
        surface.drawCircle(400, 200, 100);
        surface.drawLine(375, 200, 300, 200);
        surface.drawLine(425, 200, 500, 200);
        surface.drawLine(400, 175, 400, 100);
        surface.drawLine(400, 225, 400, 300);
    }
}
