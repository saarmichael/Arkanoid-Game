package arkanoidsprites;

// 315240937
// Michael Saar

import arkanoidgame.Game;
import arkanoidlisteners.HitListener;
import arkanoidlisteners.HitNotifier;
import biuoop.DrawSurface;
import geometryprimatives.Point;
import geometryprimatives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class responsible to represent Block.
 * Blocks are Rectangle that has color and implements Interfaces Collidable and Sprite.
 * objects can collide with Blocks and change their Velocity.
 *
 * @author Michael Saar
 * @since 24-04-2021
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private static final Color DEFAULT_COLOR = Color.BLACK;

    private Rectangle block;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * constructor method.
     *
     * @param rect         Rectangle that represents the Block
     * @param color        Block's Color
     * @param hitListeners list of HitListeners
     */
    public Block(Rectangle rect, Color color, List<HitListener> hitListeners) {
        this.block = rect;
        this.color = color;
        this.hitListeners = hitListeners;
    }

    /**
     * constructor method.
     *
     * @param rect         Rectangle that represents the Block
     * @param hitListeners list of HitListeners
     */
    public Block(Rectangle rect, List<HitListener> hitListeners) {
        this.block = rect;
        this.color = DEFAULT_COLOR;
        this.hitListeners = hitListeners;
    }

    /**
     * constructor method.
     *
     * @param rect  Rectangle that represents the Block
     * @param color Block's Color
     */
    public Block(Rectangle rect, Color color) {
        this.block = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * constructor method with default Color value.
     *
     * @param rect Rectangle that represents the Block
     */
    public Block(Rectangle rect) {
        this.block = rect;
        this.color = DEFAULT_COLOR;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return the Rectangle shape of the Block.
     */
    public Rectangle getBlock() {
        return block;
    }

    /**
     * @param newBlock to set.
     */
    public void setBlock(Rectangle newBlock) {
        this.block = newBlock;
    }

    /**
     * @param newColor to set.
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * @return the Rectangle Block which an Object has been collided to
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * setter for hitListeners List.
     *
     * @param newHitListeners the new List to set.
     */
    public void setHitListeners(List<HitListener> newHitListeners) {
        this.hitListeners = newHitListeners;
    }

    @Override
    public void addHitListener(HitListener hl) {
        // preventing from adding hl to all other Colliidable's hitListeners List
        this.hitListeners = new ArrayList<>(this.hitListeners);
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * @return Block's Color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * this method notify all this Block Listeners that a hit had occurred.
     *
     * @param hitter the hitting Ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).hitEvent(this, hitter);
        }
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        // check if the hit occurred
        if (Math.abs(collisionPoint.getX() - this.block.getUpperLeft().getX()) < Math.pow(10, -15)
                || Math.abs(collisionPoint.getX() - this.block.getTop().end().getX()) < Math.pow(10, -15)) {
            newVelocity.setDx(-1 * currentVelocity.getDx());
        }
        if (Math.abs(collisionPoint.getY() - this.block.getUpperLeft().getY()) < Math.pow(10, -15)
                || Math.abs(collisionPoint.getY() - this.block.getBottom().end().getY()) < Math.pow(10, -15)) {
            newVelocity.setDy(-1 * currentVelocity.getDy());
        }
        // notify the hitter ball that a hit occurred
        // if no collision Point has been found, return the same currentVelocity
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * draws the block on the DrawSurface.
     *
     * @param surface the DrawSurface to draw Collidable on
     */
    public void drawOn(DrawSurface surface) {

        surface.setColor(this.color);
        surface.fillRectangle((int) this.block.getTop().start().getX(), (int) this.block.getTop().start().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.block.getTop().start().getX(), (int) this.block.getTop().start().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }

    /**
     * this method relates to Sprite Interface and when a Block is treated as a Sprite, it notifies the Block time has
     * passed, which means the Block should move or do something.
     */
    @Override
    public void timePassed() {
        // nothing yet.
    }

    /**
     * @param game a Game the Block should be added to both as a Collidable and as a Sprite
     */
    @Override
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    @Override
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

}
