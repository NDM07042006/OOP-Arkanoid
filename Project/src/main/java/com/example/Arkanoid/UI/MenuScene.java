package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuScene {

    private Stage stage;


    public MenuScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        System.out.println("MenuScene show() using stage: " + stage);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Arkanoid/MenuGame.fxml"));
            Parent root = loader.load();

            MenuController controller = loader.getController();
            controller.setStage(stage);

            Scene scene = new Scene(root);
            stage.setTitle("Arkanoid Menu");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("khong the tai MenuGame.fxml");
        }
    }
}
