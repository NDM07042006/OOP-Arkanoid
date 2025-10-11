package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private VBox volumeBox;

    @FXML
    private Slider volumeSlider;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void startGame() {
        System.out.println("Start Game clicked");
    }

    @FXML
    private void showHighScores() {
        System.out.println("High Scores clicked");
    }

    @FXML
    private void showLevels() {
        System.out.println("Levels clicked");
    }

    @FXML
    private void toggleSetting() {
        volumeBox.setVisible(!volumeBox.isVisible());
    }

    @FXML
    private void exitGame() {
        if (stage != null) stage.close();
    }
}
