package arkanoidsprites;

import arkanoidlisteners.HitListener;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.Color;
import java.util.List;

public class Level3Background extends Block {

    public Level3Background(int width, int height) {
        super(new Rectangle(new Point(0, 0), width, height));
        super.setColor(new Color(139, 246, 216));
    }

}
