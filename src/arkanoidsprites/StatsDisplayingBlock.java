package arkanoidsprites;

import arkanoidgame.Counter;
import arkanoidlisteners.HitListener;
import biuoop.DrawSurface;
import geometryprimatives.Rectangle;

import java.awt.Color;
import java.util.List;

/**
 * this class represents a type of Blocks that can display numerical stats, such as score or count.
 *
 * @author Michael Saar
 * @since 24-04-2021
 */
public class StatsDisplayingBlock extends Block {

    private Counter stat;
    private String text;

    /**
     * constructor method.
     *
     * @param rect         Rectangle that represents the Block
     * @param color        Block's Color
     * @param hitListeners list of HitListeners
     * @param text         the text to display
     * @param stat         the stat to display
     */
    public StatsDisplayingBlock(
            Rectangle rect, Color color, List<HitListener> hitListeners, Counter stat, String text) {
        super(rect, color, hitListeners);
        this.stat = stat;
        this.text = text;
    }

    /**
     * getter for stat.
     *
     * @return the stat.
     */
    public Counter getStat() {
        return stat;
    }

    /**
     * getter for text.
     *
     * @return the text.
     */
    public String getText() {
        return text;
    }

    /**
     * setter for stat.
     *
     * @param newStat to set.
     */
    public void setScore(Counter newStat) {
        this.stat = newStat;
    }

    /**
     * setter for text.
     *
     * @param newText to set.
     */
    public void setText(String newText) {
        this.text = newText;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);
        surface.setColor(Color.BLACK);
        surface.drawText((int) (super.getBlock().getUpperLeft().getX() + (super.getBlock().getWidth() / 2.5)),
                (int) (super.getBlock().getUpperLeft().getY() + (super.getBlock().getHeight())),
                this.text + this.stat.getValue(),
                (int) super.getBlock().getHeight());
    }
}
