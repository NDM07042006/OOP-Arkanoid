package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class EndScene {
    private Stage stage;

    public EndScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Arkanoid/EndScreen.fxml"));
            Parent root = loader.load();

            EndController endController = loader.getController();
            endController.setStage(stage);

            Scene scene = new Scene(root);
            stage.setTitle("End Game!");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Khong the tai file EndScreen.fxml");
        }
    }
}
