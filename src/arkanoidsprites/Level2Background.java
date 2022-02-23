// 315240937
// Michael Saar

package arkanoidsprites;

import biuoop.DrawSurface;
import geometryprimatives.Line;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.Color;

/**
 * this class handles the background graphics for Level2 class.
 *
 * @author Michael Saar
 * @since 20-06-2021
 */
public class Level2Background extends Block {

    /**
     * constructor.
     *
     * @param width  of the Block
     * @param height of the Block
     */
    public Level2Background(int width, int height) {
        super(new Rectangle(new Point(0, 0), width, height));
        super.setColor(Color.WHITE);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);
        surface.setColor(new Color(9, 198, 36));
        for (int i = 0; i < 730; i += 9) {
            Line l = new Line(120, 100, i, 250);
            l.drawOn(surface);
        }
        surface.setColor(new Color(128, 182, 147));
        surface.fillCircle(120, 100, 50);
        surface.setColor(new Color(71, 141, 86));
        surface.fillCircle(120, 100, 40);
        surface.setColor(new Color(86, 255, 132));
        surface.fillCircle(120, 100, 35);
    }
}
