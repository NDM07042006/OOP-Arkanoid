package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.com.example.Arkanoid.Utlis.SceneNavigator;

public class PauseController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void resume() {
        stage.close();
    }

    @FXML
    public void restartLevel() {
        // Đóng pause window
        stage.close();
        
        // Lấy main stage (game stage)
        Stage mainStage = (Stage) stage.getOwner();
        
        // Lấy level hiện tại từ cached GameController
        int currentLevel = 1; // Default
        GameController gameController = (GameController) main.java.com.example.Arkanoid.Utlis.SceneCache.getInstance().getCachedController("game");
        if (gameController != null) {
            currentLevel = gameController.getLevel();
        }
        
        System.out.println("🔄 Restarting Level " + currentLevel + "...");
        
        // Reload lại level hiện tại (KHÔNG clear cache, chỉ set lại level)
        SceneNavigator.goToGame(mainStage, currentLevel);
    }

    @FXML
    public void setting() {
        // Lấy main stage (owner của pause stage) TRƯỚC KHI đóng
        Stage mainStage = (Stage) stage.getOwner();
        
        // Đóng pause window hiện tại
        stage.close();
        
        // Tạo một stage mới cho Settings
        Stage settingStage = new Stage();
        settingStage.initOwner(mainStage);
        settingStage.initModality(javafx.stage.Modality.WINDOW_MODAL);
        
        // Hiển thị SettingScene
        SettingScene settingScene = new SettingScene(settingStage);
        settingScene.show();
        
        // Khi setting stage đóng, mở lại pause scene
        settingStage.setOnHidden(event -> {
            // Tạo pause stage mới với cùng mainStage (game scene)
            Stage newPauseStage = new Stage();
            newPauseStage.initStyle(javafx.stage.StageStyle.TRANSPARENT); // ✅ Thêm dòng này
            newPauseStage.initOwner(mainStage);
            newPauseStage.initModality(javafx.stage.Modality.WINDOW_MODAL);
            
            PauseScene pauseScene = new PauseScene(newPauseStage);
            pauseScene.show();
        });
    }

    @FXML
    public void menu() {
        // Đóng pause window
        stage.close();
        
        // Lấy main stage
        Stage mainStage = (Stage) stage.getOwner();
        
        // Sử dụng SceneNavigator để chuyển về menu nhanh
        SceneNavigator.goToMenu(mainStage);
    }

    @FXML
    public void exit() {
        // Đóng pause window
        stage.close();
        // Đóng game window (main stage)
        Stage mainStage = (Stage) stage.getOwner();
        if (mainStage != null) {
            mainStage.close();
        }
        // Thoát ứng dụng
        System.exit(0);
    }


}
