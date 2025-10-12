package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;;
public class PauseScene {
    private Stage stage;

    public PauseScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Arkanoid/PauseMenu.fxml"));
            Parent root = loader.load();

            PauseController pauseController = loader.getController();
            pauseController.setStage(stage);
            
            Scene scene = new Scene(root);
            
            stage.setTitle("Pause");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Khong tim thay file PauseMenu.fxml");
            System.out.println(getClass().getResource("/com/Arkanoid/PauseMenu.fxml"));

        }
    }
}
