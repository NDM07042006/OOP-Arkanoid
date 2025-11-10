package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.java.com.example.Arkanoid.Utlis.SoundManager;
import main.java.com.example.Arkanoid.Utlis.SceneNavigator;

public class MenuController {

    @FXML
    private VBox volumeBox;

    @FXML
    private Slider volumeSlider;

    private Stage stage;
    
    // Cooldown để tránh spam hover sound
    private long lastHoverTime = 0;
    private static final long HOVER_COOLDOWN = 200; // 200ms

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // @FXML
    // private void startGame() {
    //     System.out.println("Start Game clicked");
    //     // Phát âm thanh và chuyển scene đồng thời - không chờ
    //     new Thread(() -> SoundManager.getInstance().playButtonClick()).start();
    //     SoundManager.getInstance().playGameMusic(); // Chuyển sang nhạc game
    //     GameScene gameScene = new GameScene(stage);
    //     gameScene.show();
    // }

    @FXML
    private void startGame() {
        System.out.println("⚡ Starting Game from Menu...");
        
        // Play sound
        SoundManager.getInstance().playButtonClick();
        
        // Chuyển scene ngay lập tức
        javafx.application.Platform.runLater(() -> {
            SceneNavigator.goToGame(stage, 1);
        });
    }

    @FXML
    private void showHighScores() {
        System.out.println("⚡ Opening High Scores...");
        
        // Play sound
        SoundManager.getInstance().playButtonClick();
        
        // Chuyển scene ngay lập tức
        javafx.application.Platform.runLater(() -> {
            SceneNavigator.goToHighScores(stage);
        });
    }
    
    @FXML
    private void showLevels() {
        System.out.println("⚡ Opening Levels...");
        
        // Play sound
        SoundManager.getInstance().playButtonClick();
        
        // Chuyển scene ngay lập tức
        javafx.application.Platform.runLater(() -> {
            SceneNavigator.goToLevels(stage);
        });
    }

    @FXML
    private Stage newStage;

    @FXML
    private void toggleSetting() {
        SoundManager.getInstance().playButtonClick();
        try {
            if (newStage != null && newStage.isShowing()) {
                newStage.toFront();
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Arkanoid/Setting.fxml"));
            Parent root = loader.load();

            newStage = new Stage();
            SettingController settingController = loader.getController();
            settingController.setStage(newStage);

            Scene scene = new Scene(root);

            newStage.setTitle("Settings");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("khong the tai file Setting");
        }
    }

    @FXML
    private void exitGame() {
        SoundManager.getInstance().playButtonClick();
        System.out.println("Exit Game!");
        if (stage != null) {
            stage.close();
        }
    }
    
    // Method để add vào FXML cho hover effect
    @FXML
    private void onButtonHover() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastHoverTime > HOVER_COOLDOWN) {
            SoundManager.getInstance().playButtonHover();
            lastHoverTime = currentTime;
        }
    }
}
