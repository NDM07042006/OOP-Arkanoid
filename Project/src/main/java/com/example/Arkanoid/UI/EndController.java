package main.java.com.example.Arkanoid.UI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.arkanoid.engine.Define;
import main.java.com.example.Arkanoid.Utlis.SoundManager;

public class EndController {
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private ImageView backgroundImage;

    @FXML
    private Stage stage;
    
    @FXML
    private Button restartButton;
    
    @FXML
    private Button menuButton;
    
    @FXML
    private Button nextLevelButton;
    
    private boolean isWin = false; // Trạng thái thắng/thua

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /**
     * Set trạng thái thắng/thua và load background tương ứng
     * @param win true = thắng (you_win), false = thua (game_over)
     */
    public void setWinStatus(boolean win) {
        this.isWin = win;
        loadBackground();
    }
    
    private void loadBackground() {
        try {
            String imagePath = isWin ? Define.YOU_WIN_IMAGE_PATH : Define.GAME_OVER_IMAGE_PATH;
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            
            if (backgroundImage != null) {
                backgroundImage.setImage(image);
                backgroundImage.toBack(); // Đảm bảo background ở phía sau
                System.out.println("✅ EndController: Loaded " + (isWin ? "YOU_WIN" : "GAME_OVER") + " background");
            }
        } catch (Exception e) {
            System.err.println("❌ Error loading end game background: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    public void initialize() {
        // Setup hover effects cho tất cả các buttons
        setupHoverEffect(restartButton);
        setupHoverEffect(menuButton);
        setupHoverEffect(nextLevelButton);
        
        // Load background mặc định (game over) nếu chưa set
        if (backgroundImage != null && backgroundImage.getImage() == null) {
            loadBackground();
        }
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
