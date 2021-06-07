package arkanoidgame;// 315240937
// Michael Saar


import biuoop.DrawSurface;

public interface Animation {
    void doOneFrame(DrawSurface d);
    boolean shouldStop();
}