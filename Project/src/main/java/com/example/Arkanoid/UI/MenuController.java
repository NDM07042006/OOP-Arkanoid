package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.java.com.example.Arkanoid.Utlis.SoundManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private VBox volumeBox;

    @FXML
    private Slider volumeSlider;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // THÊM: Initialize để chạy nhạc menu khi vào màn hình
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Chạy nhạc menu khi vào màn hình chính
        SoundManager.getInstance().playMenuMusic();
    }

    @FXML
    private void startGame() {

        System.out.println("Start Game clicked");
        SoundManager.getInstance().playButtonClick();
        SoundManager.getInstance().playGameMusic();
    }

    @FXML
    private void showHighScores() {

        System.out.println("High Scores clicked");
        SoundManager.getInstance().playButtonClick();
    }

    @FXML
    private void showLevels() {
        System.out.println("Levels clicked");
        SoundManager.getInstance().playButtonClick();;
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
        SoundManager.getInstance().playButtonClick();

        // THÊM: Dọn dẹp resources trước khi thoát
        SoundManager.getInstance().dispose();
        if (stage != null) stage.close();
    }
}
