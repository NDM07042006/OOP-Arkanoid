package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void pause() {
        // Tạo một stage mới cho pause (giống như GameScene)
        Stage pauseStage = new Stage();
        pauseStage.initOwner(stage);
        pauseStage.initModality(Modality.WINDOW_MODAL);
        
        PauseScene pauseScene = new PauseScene(pauseStage);
        pauseScene.show();
    }
}
