package main.java.com.example.Arkanoid.UI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.com.example.Arkanoid.Utlis.SoundManager;

public class PauseController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private AnchorPane anchorPane;

    public void onPauseMenuOpened() {
        SoundManager.getInstance().pauseBackgroundMusic();
    }

    @FXML
    public void resume() {
        SoundManager.getInstance().playButtonClick();
    }

    @FXML
    public void restartLevel() {
        SoundManager.getInstance().playButtonClick();

        // THÊM: Chạy lại nhạc game từ đầu
        SoundManager.getInstance().playGameMusic();
    }

    @FXML
    public void menu() {
        SoundManager.getInstance().playButtonClick();
        SoundManager.getInstance().playMenuMusic();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Arkanoid/MenuGame.fxml"));
            Parent root = loader.load();

            MenuController controller = loader.getController();
            controller.setStage(stage);

            Scene scene = new Scene(root);
            stage.setTitle("Arkanoid Menu");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("khong the tai MenuGame.fxml");
        }
    }   


}
