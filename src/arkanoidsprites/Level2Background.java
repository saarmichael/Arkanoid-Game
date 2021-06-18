package arkanoidsprites;

import biuoop.DrawSurface;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.Color;

public class Level2Background extends Block {

    public Level2Background(int width, int height) {
        super(new Rectangle(new Point(0, 0), width, height));
        super.setColor(Color.WHITE);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);
        surface.setColor(Color.BLACK);

    }
}
