// 315240937
// Michael Saar
package arkanoidgame;

import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

public class GameFlow {

    private KeyboardSensor sensor;
    private AnimationRunner animationRunner;
    private Counter score;

    /**
     * constructor.
     *
     * @param ks KeyboardSensor
     * @param ar Animation Runner
     */
    public GameFlow(KeyboardSensor ks, AnimationRunner ar) {
        this.animationRunner = ar;
        this.score = new Counter();
        this.sensor = ks;
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(this.sensor, this.animationRunner, levelInfo, this.score);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            // if level stopped due to a loss (no more balls) - break the for loop and end the game.
            if (level.getBallsCounter().getValue() <= 0) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        AnimationRunner ar = new AnimationRunner();
        KeyboardSensor ks = ar.getGui().getKeyboardSensor();
        LevelInformation l1 = new Level1();
        LevelInformation l2 = new Level2();
        LevelInformation l3 = new Level3();
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(l1);
        levels.add(l2);
        levels.add(l3);
        GameFlow gameFlow = new GameFlow(ks, ar);
        gameFlow.runLevels(levels);
    }

}


