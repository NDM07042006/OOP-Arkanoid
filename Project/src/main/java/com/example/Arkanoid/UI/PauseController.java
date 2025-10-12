package main.java.com.example.Arkanoid.UI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class PauseController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void resume() {

    }

    @FXML
    public void restartLevel() {

    }

    @FXML
    public void menu() {
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
