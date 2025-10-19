package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
import javafx.stage.Stage;
public class SettingController {
    @FXML
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void exitSetting() {
        if (stage != null) stage.close();
    }
}
