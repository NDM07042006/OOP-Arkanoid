package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.java.arkanoid.engine.Define;;
public class PauseScene {
    private Stage stage;

    public PauseScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Define.PAUSE_MENU_PATH));
            Parent root = loader.load();

            PauseController pauseController = loader.getController();
            pauseController.setStage(stage);

            Scene scene = new Scene(root);

            stage.setTitle("Pause");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Khong tim thay file PauseMenu.fxml");
            System.out.println(getClass().getResource(Define.PAUSE_MENU_PATH));

        }
    }
}