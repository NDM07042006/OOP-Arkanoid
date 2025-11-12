package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import main.java.arkanoid.engine.Define;
import javafx.scene.Scene;

public class EndScene {
    private Stage stage;
    private boolean isWin;

    /**
     * Constructor cho EndScene
     * @param stage Stage hiện tại
     * @param win true = thắng (you_win), false = thua (game_over)
     */
    public EndScene(Stage stage, boolean win) {
        this.stage = stage;
        this.isWin = win;
    }
    
    // Constructor cũ để backward compatible
    public EndScene(Stage stage) {
        this(stage, false); // Mặc định là thua
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Define.END_SCREEN_PATH));
            Parent root = loader.load();

            EndController endController = loader.getController();
            endController.setStage(stage);
            endController.setWinStatus(isWin); // Set trạng thái thắng/thua

            Scene scene = new Scene(root);
            stage.setTitle(isWin ? "Victory!" : "Game Over!");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Khong the tai file EndScreen.fxml");
        }
    }
}
