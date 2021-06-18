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
    private boolean hasWon = true;

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

    private void endGame() {
        Animation end = new EndGameScreen(this.animationRunner.getGui().getKeyboardSensor(), this.score, this.hasWon);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.sensor, this.sensor.SPACE_KEY, end));
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
                this.hasWon = false;
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
        gameFlow.endGame();
        gameFlow.animationRunner.getGui().close();
    }

}


