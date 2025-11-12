package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.arkanoid.engine.Define;
import main.java.com.example.Arkanoid.Utlis.SceneCache;

public class LevelScene {
    private Stage stage;

    public LevelScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Thử lấy từ cache trước
        Scene cachedScene = SceneCache.getInstance().getCachedScene("levels");

        if (cachedScene != null) {
            // Sử dụng cached scene (instant!)
            System.out.println("⚡ Using cached LevelScene");
            LevelController controller = (LevelController) SceneCache.getInstance().getCachedController("levels");
            if (controller != null) {
                controller.setStage(stage);
            }

            stage.setTitle("Select Level");
            stage.setScene(cachedScene);
            stage.setResizable(false);
            stage.show();
            return;
        }

        // Nếu chưa cache, load bình thường
        try {
            System.out.println("🔄 Loading LevelScene from FXML");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Define.LEVELS_PATH));
            Parent root = loader.load();

            LevelController controller = loader.getController();
            controller.setStage(stage);

            Scene scene = new Scene(root);

            // Cache lại cho lần sau
            SceneCache.getInstance().getCachedScene("levels"); // Will cache it

            stage.setTitle("Select Level");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not load Levels.fxml");
        }
    }
}