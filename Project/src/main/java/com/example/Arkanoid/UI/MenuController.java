package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MenuController {
    private Stage stage;

    @FXML
    private VBox volumeBox;

    @FXML
    private Slider volumeSlider;

    public void setStage(Stage stage) {
        System.out.println("MenuController setStage: " + stage);
        this.stage = stage;
    }

    @FXML
    private void startGame() {
        System.out.println("Starting game with stage: " + stage);
        GameScene gameScene = new GameScene(stage);
        gameScene.show(); 
    }

    @FXML
    private void showHighScores() {
        System.out.println("High Scores clicked");
    }

    @FXML
    private void showLevels() {
        System.out.println("Levels clicked");
    }

    private Stage newStage;

    @FXML
    private void toggleSetting() {
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

            Scene scene = new Scene(root, 400, 200);

            newStage.setTitle("Setting");
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
        System.out.println("Exit Game!");
        if (stage != null) stage.close();
    }
}
