package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import main.java.arkanoid.engine.Define;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
public class PauseScene {
    private Stage stage;

    public PauseScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        try {
            // Stage style đã được set ở GameController (TRANSPARENT)
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Define.PAUSE_MENU_PATH));
            Parent root = loader.load();

            PauseController pauseController = loader.getController();
            pauseController.setStage(stage);
            
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT); // Scene trong suốt
            
            stage.setTitle("Pause");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Khong tim thay file PauseMenu.fxml");
            System.out.println(getClass().getResource("/com/Arkanoid/PauseMenu.fxml"));

        }
    }
}
