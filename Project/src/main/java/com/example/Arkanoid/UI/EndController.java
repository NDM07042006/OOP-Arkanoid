package main.java.com.example.Arkanoid.UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.com.example.Arkanoid.Utlis.SoundManager;  // ← THÊM

public class EndController implements Initializable {  // ← THÊM implements

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SoundManager.getInstance().playMenuMusic();
    }

    @FXML
    public void Restart() {
        SoundManager.getInstance().playButtonClick();
        SoundManager.getInstance().playGameMusic();

    }

    @FXML
    public void Menu() {
        SoundManager.getInstance().playButtonClick();

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

    @FXML
    public void NextLevel() {
        SoundManager.getInstance().playButtonClick();
        SoundManager.getInstance().playGameMusic();


    }
}