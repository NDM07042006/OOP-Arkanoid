package main.java.com.example.Arkanoid.UI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.arkanoid.engine.Define;
import main.java.com.example.Arkanoid.Utlis.SoundManager;

public class EndController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Stage stage;
    
    @FXML
    private Button restartButton;
    
    @FXML
    private Button menuButton;
    
    @FXML
    private Button nextLevelButton;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    public void initialize() {
        // Setup hover effects cho tất cả các buttons
        setupHoverEffect(restartButton);
        setupHoverEffect(menuButton);
        setupHoverEffect(nextLevelButton);
    }
    
    private void setupHoverEffect(Button button) {
        if (button != null) {
            button.setOnMouseEntered(e -> {
                SoundManager.getInstance().playButtonHover();
            });
        }
    }

    @FXML
    public void Restart() {
        SoundManager.getInstance().playButtonClick();
    } 

    @FXML
    public void Menu() {
        SoundManager.getInstance().playButtonClick();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Define.MENU_GAME_PATH));
            Parent root = loader.load();

            MenuController controller = loader.getController();
            controller.setStage(stage);

            Scene scene = new Scene(root);
            stage.setTitle("Arkanoid Menu");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("khong the tai MenuGame.fxml");
        }
    }

    @FXML
    public void NextLevel() {
        SoundManager.getInstance().playButtonClick();
    }
}
