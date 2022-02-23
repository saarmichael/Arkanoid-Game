// 315240937
// Michael Saar


package arkanoidsprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Saar
 * <p>
 * this method is responsible to deal with a collection of Sprites, which means to apply same methods to all Sprites in
 * the collection.
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Constructor with parameter.
     *
     * @param sprites list of sprites.
     */
    public SpriteCollection(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * @param s a Sprite that should be added to sprites List
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * remove Sprite s from the collection.
     *
     * @param s the Sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * @param d a DrawSurface to draw all Sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * @return the sprites collection
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * @param newSprites new Collection of Sprites
     */
    public void setSprites(List<Sprite> newSprites) {
        this.sprites = newSprites;
    }
}