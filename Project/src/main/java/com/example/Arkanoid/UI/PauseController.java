package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
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
        stage.close();
    }

    @FXML
    public void restartLevel() {
        stage.close();
        Stage newStage = (Stage) stage.getOwner();
        GameScene gameScene = new GameScene(newStage);
        gameScene.show();
    }

    @FXML
    public void menu() {
        stage.close();
        Stage mainStage = (Stage) stage.getOwner();
        MenuScene menuScene = new MenuScene(mainStage);
        menuScene.show();
    }   


}
