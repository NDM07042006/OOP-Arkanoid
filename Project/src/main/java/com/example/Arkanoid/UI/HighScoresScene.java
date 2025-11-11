package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.arkanoid.engine.Define;
import main.java.com.example.Arkanoid.Utlis.SceneCache;

public class HighScoresScene {
    private Stage stage;

    public HighScoresScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Thử lấy từ cache trước
        Scene cachedScene = SceneCache.getInstance().getCachedScene("highscores");
        
        if (cachedScene != null) {
            // Sử dụng cached scene (instant!)
            System.out.println("⚡ Using cached HighScoresScene");
            HighScoresController controller = (HighScoresController) SceneCache.getInstance().getCachedController("highscores");
            if (controller != null) {
                controller.setStage(stage);
                // Reload data để có điểm mới nhất
                controller.initialize();
            }
            
            stage.setTitle("High Scores");
            stage.setScene(cachedScene);
            stage.setResizable(false);
            stage.show();
            return;
        }
        
        // Nếu chưa cache, load bình thường
        try {
            System.out.println("🔄 Loading HighScoresScene from FXML");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Define.HIGH_SCORES_PATH));
            Parent root = loader.load();

            HighScoresController controller = loader.getController();
            controller.setStage(stage);

            Scene scene = new Scene(root);
            stage.setTitle("High Scores");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not load HighScores.fxml");
        }
    }
}
