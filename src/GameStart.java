// 315240937
// Michael Saar

import arkanoidgame.Level1;
import arkanoidgame.Level2;
import arkanoidgame.Level3;
import arkanoidgame.Level4;
import arkanoidgame.LevelInformation;
import arkanoidgame.GameFlow;
import arkanoidgame.AnimationRunner;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * this class runs an Arkanoid game.
 *
 * @author Michael Saar
 * @since 20-06-2021
 */
public class GameStart {

    /**
     * main.
     *
     * @param args information for which levels to run and  in what order.
     */
    public static void main(String[] args) {
        AnimationRunner ar = new AnimationRunner();
        KeyboardSensor ks = ar.getGui().getKeyboardSensor();
        LevelInformation l1 = new Level1();
        LevelInformation l2 = new Level2();
        LevelInformation l3 = new Level3();
        LevelInformation l4 = new Level4();
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(l1);
        levels.add(l2);
        levels.add(l3);
        levels.add(l4);
        GameFlow gameFlow = new GameFlow(ks, ar);
        List<LevelInformation> levelsToRun = new ArrayList<>();
        if (args.length <= 0) {
            levelsToRun.addAll(levels);
        } else {
            for (String arg : args) {
                try {
                    int levelNum = Integer.parseInt(arg);
                    if (levelNum >= 1 && levelNum <= 4) {
                        levelsToRun.add(levels.get(levelNum - 1));
                    }
                } catch (NumberFormatException nfe) {
                    continue;
                }

            }
            if (levelsToRun.isEmpty()) {
                levelsToRun.addAll(levels);
            }
        }
        gameFlow.runLevels(levelsToRun);
        gameFlow.endGame();
        gameFlow.getAnimationRunner().getGui().close();
    }
}
