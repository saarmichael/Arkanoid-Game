// 315240937
// Michael Saar

package arkanoidgame;


import biuoop.DrawSurface;

public interface Animation {
    void doOneFrame(DrawSurface d);

    boolean shouldStop();
}