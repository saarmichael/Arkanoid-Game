// 315240937
// Michael Saar

package arkanoidgame;

import biuoop.KeyboardSensor;

import java.util.List;

/**
 * this class is responsible for running multiple levels all together.
 *
 * @author Michael Saar
 * @since 20-06-2021
 */
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

    /**
     * run an EndGame Animation.
     */
    public void endGame() {
        Animation end = new EndGameScreen(this.animationRunner.getGui().getKeyboardSensor(), this.score, this.hasWon);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.sensor, this.sensor.SPACE_KEY, end));
    }

    /**
     * this method is responsible for the logic of running multiple levels one after the other.
     *
     * @param levels List of levels to run.
     */
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

    /**
     * getter fot AnimationRunner.
     *
     * @return the AnimationRunner.
     */
    public AnimationRunner getAnimationRunner() {
        return animationRunner;
    }

}


