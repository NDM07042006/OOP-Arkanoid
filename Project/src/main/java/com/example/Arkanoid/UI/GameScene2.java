package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameScene2 {
    private Stage stage;

    public GameScene2(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Arkanoid/GameScene.fxml"));
            Parent root = loader.load();

            GameController gameController = loader.getController();
            gameController.setStage(stage);

            Scene scene = new Scene(root);
            stage.setTitle("Game");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
