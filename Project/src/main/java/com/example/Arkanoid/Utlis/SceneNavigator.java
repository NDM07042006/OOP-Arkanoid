package main.java.com.example.Arkanoid.Utlis;

import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.com.example.Arkanoid.UI.*;

/**
 * SceneNavigator - Utility class để chuyển scene nhanh chóng với cache
 */
public class SceneNavigator {

    /**
     * Chuyển đến MenuScene (sử dụng cache)
     */
    public static void goToMenu(Stage stage) {
        long startTime = System.nanoTime();

        Scene cachedScene = SceneCache.getInstance().getCachedScene("menu");

        if (cachedScene != null) {
            System.out.println("⚡ Fast navigation to MenuScene (cached)");

            // Lấy controller và update stage
            MenuController controller = (MenuController) SceneCache.getInstance().getCachedController("menu");
            if (controller != null) {
                controller.setStage(stage);
            }

            stage.setScene(cachedScene);
            stage.setTitle("Arkanoid Menu");

            // KHÔNG play music ở đây - để tránh blocking
            // SoundManager.getInstance().playMenuMusic();

            long endTime = System.nanoTime();
            double timeTaken = (endTime - startTime) / 1_000_000.0; // Convert to ms
            System.out.println("⏱️ Navigation took: " + String.format("%.2f", timeTaken) + "ms");
        } else {
            // Fallback: load bình thường nếu chưa cache
            System.out.println("🔄 Loading MenuScene (not cached)");
            MenuScene menuScene = new MenuScene(stage);
            menuScene.show();
        }
    }

    /**
     * Chuyển đến GameScene với level cụ thể (sử dụng cache)
     */
    public static void goToGame(Stage stage, int level) {
        long startTime = System.nanoTime();

        Scene cachedScene = SceneCache.getInstance().getCachedScene("game");

        if (cachedScene != null) {
            System.out.println("⚡ Fast navigation to GameScene Level " + level + " (cached)");

            // Lấy controller và update
            GameController controller = (GameController) SceneCache.getInstance().getCachedController("game");
            if (controller != null) {
                controller.setStage(stage);
                controller.setLevel(level);
            }
            PauseController.setGameController(controller);
            EndController.setGameController(controller);

            stage.setScene(cachedScene);
            stage.setTitle("Game - Level " + level);

            // KHÔNG play music ở đây - để tránh blocking
            // SoundManager.getInstance().playGameMusic();

            long endTime = System.nanoTime();
            double timeTaken = (endTime - startTime) / 1_000_000.0; // Convert to ms
            System.out.println("⏱️ Navigation took: " + String.format("%.2f", timeTaken) + "ms");




        } else {
            // Fallback: load bình thường nếu chưa cache
            System.out.println("🔄 Loading GameScene Level " + level + " (not cached)");
            GameScene2 gameScene = new GameScene2(stage, level);
            gameScene.show();
        }
    }

    /**
     * Chuyển đến LevelScene (sử dụng cache)
     */
    public static void goToLevels(Stage stage) {
        long startTime = System.nanoTime();

        Scene cachedScene = SceneCache.getInstance().getCachedScene("levels");

        if (cachedScene != null) {
            System.out.println("⚡ Fast navigation to LevelScene (cached)");

            // Lấy controller và update stage
            LevelController controller = (LevelController) SceneCache.getInstance().getCachedController("levels");
            if (controller != null) {
                controller.setStage(stage);
            }

            stage.setScene(cachedScene);
            stage.setTitle("Select Level");

            long endTime = System.nanoTime();
            double timeTaken = (endTime - startTime) / 1_000_000.0; // Convert to ms
            System.out.println("⏱️ Navigation took: " + String.format("%.2f", timeTaken) + "ms");
        } else {
            // Fallback: load bình thường nếu chưa cache
            System.out.println("🔄 Loading LevelScene (not cached)");
            LevelScene levelScene = new LevelScene(stage);
            levelScene.show();
        }
    }

    /**
     * Chuyển đến HighScoresScene (sử dụng cache)
     */
    public static void goToHighScores(Stage stage) {
        Scene cachedScene = SceneCache.getInstance().getCachedScene("highscores");

        if (cachedScene != null) {
            System.out.println("⚡ Fast navigation to HighScoresScene (cached)");

            // Lấy controller và update
            HighScoresController controller = (HighScoresController) SceneCache.getInstance().getCachedController("highscores");
            if (controller != null) {
                controller.setStage(stage);
                // High scores sẽ tự reload khi hiển thị
            }

            stage.setScene(cachedScene);
            stage.setTitle("High Scores");
        } else {
            // Fallback: load bình thường nếu chưa cache
            System.out.println("🔄 Loading HighScoresScene (not cached)");
            HighScoresScene highScoresScene = new HighScoresScene(stage);
            highScoresScene.show();
        }
    }
}