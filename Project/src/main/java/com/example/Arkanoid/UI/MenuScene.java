package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.com.example.Arkanoid.Utlis.SoundManager;
import main.java.arkanoid.engine.Define;
import main.java.com.example.Arkanoid.Utlis.SceneCache;

import java.io.IOException;

public class MenuScene {

    private Stage stage;

    public MenuScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        System.out.println("MenuScene show() using stage: " + stage);
        
        // Thử lấy từ cache trước
        Scene cachedScene = SceneCache.getInstance().getCachedScene("menu");
        
        if (cachedScene != null) {
            // Sử dụng cached scene (instant!)
            System.out.println("⚡ Using cached MenuScene");
            MenuController controller = (MenuController) SceneCache.getInstance().getCachedController("menu");
            if (controller != null) {
                controller.setStage(stage);
            }
            
            stage.setTitle("Arkanoid Menu");
            stage.setScene(cachedScene);
            stage.setResizable(false);
            stage.show();
            
            // Phát nhạc nền menu
            SoundManager.getInstance().playMenuMusic();
            return;
        }
        
        // Nếu chưa cache, load bình thường
        try {
            System.out.println("🔄 Loading MenuScene from FXML");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Define.MENU_GAME_PATH));
            Parent root = loader.load();

            MenuController controller = loader.getController();
            controller.setStage(stage);

            Scene scene = new Scene(root);
            stage.setTitle("Arkanoid Menu");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            // Phát nhạc nền menu
            SoundManager.getInstance().playMenuMusic();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("khong the tai MenuGame.fxml");
        }
    }
}
