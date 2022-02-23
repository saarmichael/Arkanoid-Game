// 315240937
// Michael Saar

package arkanoidsprites;


import biuoop.DrawSurface;

import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.Color;


/**
 * this class handles the background graphics for Level3 class.
 *
 * @author Michael Saar
 * @since 20-06-2021
 */
public class Level3Background extends Block {

    /**
     * constructor.
     *
     * @param width  of the Block
     * @param height of the Block
     */
    public Level3Background(int width, int height) {
        super(new Rectangle(new Point(0, 0), width, height));
        super.setColor(new Color(139, 246, 216));
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);
        surface.setColor(new Color(48, 205, 138));
    }
}
