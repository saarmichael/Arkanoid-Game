// 315240937
// Michael Saar

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

    private Counter score;
    private Counter lives;
    private String levelName;

    /**
     * constructor method.
     *
     * @param rect         Rectangle that represents the Block
     * @param color        Block's Color
     * @param hitListeners list of HitListeners
     * @param text         the text to display
     * @param stat         the stat to display
     * @param lives        number of balls in the game.
     */
    public StatsDisplayingBlock(
            Rectangle rect, Color color, List<HitListener> hitListeners, Counter stat, Counter lives, String text) {
        super(rect, color, hitListeners);
        this.score = stat;
        this.levelName = text;
        this.lives = lives;
    }

    /**
     * getter for stat.
     *
     * @return the stat.
     */
    public Counter getStat() {
        return this.score;
    }

    /**
     * getter for text.
     *
     * @return the text.
     */
    public String getText() {
        return this.levelName;
    }

    /**
     * setter for stat.
     *
     * @param newStat to set.
     */
    public void setScore(Counter newStat) {
        this.score = newStat;
    }

    /**
     * setter for text.
     *
     * @param newText to set.
     */
    public void setText(String newText) {
        this.levelName = newText;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);
        surface.setColor(Color.BLACK);
        surface.drawText((int) (super.getBlock().getUpperLeft().getX() + 20),
                (int) (super.getBlock().getUpperLeft().getY() + (super.getBlock().getHeight())),
                "Lives: " + this.lives.getValue(),
                (int) super.getBlock().getHeight());
        surface.drawText((int) (super.getBlock().getUpperLeft().getX() + 220),
                (int) (super.getBlock().getUpperLeft().getY() + (super.getBlock().getHeight())),
                "score: " + this.score.getValue(),
                (int) super.getBlock().getHeight());
        surface.drawText((int) (super.getBlock().getUpperLeft().getX() + 420),
                (int) (super.getBlock().getUpperLeft().getY() + (super.getBlock().getHeight())),
                "Level Name: " + this.levelName,
                (int) super.getBlock().getHeight());
    }
}
