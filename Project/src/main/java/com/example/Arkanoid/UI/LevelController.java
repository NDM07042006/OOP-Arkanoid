package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.java.com.example.Arkanoid.Utlis.SoundManager;
import main.java.com.example.Arkanoid.Utlis.SceneNavigator;

public class LevelController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Rectangle level1Bg;
    
    @FXML
    private Rectangle level2Bg;
    
    @FXML
    private Rectangle level3Bg;
    
    @FXML
    private Rectangle level4Bg;
    
    @FXML
    private Rectangle level5Bg;
    
    @FXML
    private Rectangle level6Bg;
    
    @FXML
    private Rectangle level7Bg;
    
    @FXML
    private Rectangle level8Bg;
    
    @FXML
    private Rectangle level9Bg;
    
    @FXML
    private Rectangle level10Bg;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        // Thêm hover effect cho các level rectangles
        setupHoverEffect(level1Bg);
        setupHoverEffect(level2Bg);
        setupHoverEffect(level3Bg);
        setupHoverEffect(level4Bg);
        setupHoverEffect(level5Bg);
        setupHoverEffect(level6Bg);
        setupHoverEffect(level7Bg);
        setupHoverEffect(level8Bg);
        setupHoverEffect(level9Bg);
        setupHoverEffect(level10Bg);
    }

    private void setupHoverEffect(Rectangle rectangle) {
        if (rectangle != null) {
            rectangle.setOnMouseEntered(e -> {
                rectangle.setOpacity(0.8);
                rectangle.setScaleX(1.05);
                rectangle.setScaleY(1.05);
                SoundManager.getInstance().playButtonHover();
            });
            rectangle.setOnMouseExited(e -> {
                rectangle.setOpacity(1.0);
                rectangle.setScaleX(1.0);
                rectangle.setScaleY(1.0);
            });
        }
    }

    @FXML
    public void selectLevel1(MouseEvent event) {
        SoundManager.getInstance().playButtonClick();
        System.out.println("Level 1 selected");
        startGameAsync(1);
    }

    @FXML
    public void selectLevel2(MouseEvent event) {
        SoundManager.getInstance().playButtonClick();
        System.out.println("Level 2 selected");
        startGameAsync(2);
    }

    @FXML
    public void selectLevel3(MouseEvent event) {
        SoundManager.getInstance().playButtonClick();
        System.out.println("Level 3 selected");
        startGameAsync(3);
    }

    @FXML
    public void selectLevel4(MouseEvent event) {
        SoundManager.getInstance().playButtonClick();
        System.out.println("Level 4 selected");
        startGameAsync(4);
    }

    @FXML
    public void selectLevel5(MouseEvent event) {
        SoundManager.getInstance().playButtonClick();
        System.out.println("Level 5 selected");
        startGameAsync(5);
    }

    @FXML
    public void selectLevel6(MouseEvent event) {
        SoundManager.getInstance().playButtonClick();
        System.out.println("Level 6 selected");
        startGameAsync(6);
    }

    @FXML
    public void selectLevel7(MouseEvent event) {
        SoundManager.getInstance().playButtonClick();
        System.out.println("Level 7 selected");
        startGameAsync(7);
    }

    @FXML
    public void selectLevel8(MouseEvent event) {
        SoundManager.getInstance().playButtonClick();
        System.out.println("Level 8 selected");
        startGameAsync(8);
    }

    @FXML
    public void selectLevel9(MouseEvent event) {
        SoundManager.getInstance().playButtonClick();
        System.out.println("Level 9 selected");
        startGameAsync(9);
    }

    @FXML
    public void selectLevel10(MouseEvent event) {
        SoundManager.getInstance().playButtonClick();
        System.out.println("Level 10 selected");
        startGameAsync(10);
    }

    private void startGameAsync(int levelNumber) {
        System.out.println("⚡ Starting Level " + levelNumber + "...");
        
        // Play sound KHÔNG chờ
        SoundManager.getInstance().playButtonClick();
        
        // Chuyển scene NGAY LẬP TỨC (không đợi sound)
        javafx.application.Platform.runLater(() -> {
            SceneNavigator.goToGame(stage, levelNumber);
        });
    }

    @FXML
    public void backToMenu() {
        System.out.println("⚡ Back to menu...");
        
        // Play sound KHÔNG chờ  
        SoundManager.getInstance().playButtonClick();
        
        // Chuyển scene NGAY LẬP TỨC
        javafx.application.Platform.runLater(() -> {
            SceneNavigator.goToMenu(stage);
        });
    }
}
