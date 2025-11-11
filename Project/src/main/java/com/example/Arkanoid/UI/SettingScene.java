package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SettingScene {
    private Stage stage;

    public SettingScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Arkanoid/Setting.fxml"));
            Parent root = loader.load();

            SettingController settingController = loader.getController();
            settingController.setStage(stage);

            Scene scene = new Scene(root);
            stage.setTitle("Setting");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("khong the tai file Setting.fxml");
        }
    }
}