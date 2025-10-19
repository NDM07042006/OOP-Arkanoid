package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

// import main.java.com.example.Arkanoid.UI.MenuScene;
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

    }

    @FXML
    public void menu() {
        stage.close();
        Stage mainStage = (Stage) stage.getOwner();
        MenuScene menuScene = new MenuScene(mainStage);
        menuScene.show();
    }   


}
