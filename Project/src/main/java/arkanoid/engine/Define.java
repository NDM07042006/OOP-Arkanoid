package main.java.arkanoid.engine;
//lưu các địa chỉ file hoặc các hằng số ở đây để tiện sử dụng
public class Define {
    // ==================== IMAGE PATHS ====================
    // Power-ups
    public static final String MULTI_BALL_IMAGE_PATH        = "/com/Arkanoid/img/MultiBall.png";
    public static final String FAST_BALL_IMAGE_PATH         = "/com/Arkanoid/img/FastBall.png";
    public static final String SLOW_BALL_IMAGE_PATH         = "/com/Arkanoid/img/SlowBall.png";
    public static final String FAST_PADDLE_IMAGE_PATH       = "/com/Arkanoid/img/FastPaddle.png";
    public static final String SLOW_PADDLE_IMAGE_PATH       = "/com/Arkanoid/img/SlowPaddle.png";
    public static final String RANDOM_POWER_UP_IMAGE_PATH   = "/com/Arkanoid/img/RandomPowerUp.png";

    // Game assets
    public static final String PADDLES_AND_BALLS_IMAGE_PATH = "/com/Arkanoid/img/paddles_and_balls.png";
    public static final String BRICKS_IMAGE_PATH            = "/com/Arkanoid/img/bricks.png";
    public static final String PAUSE_BUTTON_IMAGE_PATH      = "/com/Arkanoid/images/pause.png";
    
    // Backgrounds (base path, append level number + extension)
    public static final String BACKGROUND_BASE_PATH         = "/com/Arkanoid/images/background_";
    
    // End game backgrounds
    public static final String GAME_OVER_IMAGE_PATH         = "/com/Arkanoid/images/game_over.gif";
    public static final String YOU_WIN_IMAGE_PATH           = "/com/Arkanoid/images/you_win.gif";
    
    // UI Images
    public static final String UI_09_IMAGE_PATH             = "/com/Arkanoid/images/Ui_09.png";
    
    // ==================== FXML PATHS ====================
    public static final String END_SCREEN_PATH              = "/com/Arkanoid/EndScreen.fxml";
    public static final String MENU_GAME_PATH               = "/com/Arkanoid/MenuGame.fxml";
    public static final String PAUSE_MENU_PATH              = "/com/Arkanoid/PauseMenu.fxml";
    public static final String SETTING_PATH                 = "/com/Arkanoid/Setting.fxml";
    public static final String GAME_SCENE_PATH              = "/com/Arkanoid/GameScene.fxml";
    public static final String LEVELS_PATH                  = "/com/Arkanoid/Levels.fxml";
    public static final String HIGH_SCORES_PATH             = "/com/Arkanoid/HighScores.fxml";
    
    // ==================== SOUND PATHS ====================
    // Sound Effects
    public static final String BUTTON_HOVER_SOUND           = "/com/Arkanoid/sounds/button_hover.wav";
    public static final String BUTTON_CLICK_SOUND           = "/com/Arkanoid/sounds/button_click.wav";
    public static final String PADDLE_HIT_SOUND             = "/com/Arkanoid/sounds/paddle_hit.wav";
    public static final String WALL_HIT_SOUND               = "/com/Arkanoid/sounds/wall_hit.wav";
    public static final String BRICK_BREAK_SOUND            = "/com/Arkanoid/sounds/brick_break.wav";
    public static final String LIFE_LOST_SOUND              = "/com/Arkanoid/sounds/lost_life.wav";
    public static final String LEVEL_COMPLETE_SOUND         = "/com/Arkanoid/sounds/level_complete.wav";
    public static final String GAME_OVER_SOUND              = "/com/Arkanoid/sounds/game_over.wav";
    
    // Background Music
    public static final String MENU_THEME_MUSIC             = "/com/Arkanoid/sounds/menu_theme.wav";
    public static final String GAME_THEME_MUSIC             = "/com/Arkanoid/sounds/game_theme.wav";

    // ==================== GAME CONSTANTS ====================
    // Ball Speed
    public static final int MAX_BALL_SPEED                  = 10;
    public static final int DEFAULF_BALL_SPEED              = 5;
    public static final int MIN_BALL_SPEED                  = 1;
    public static final double DEFAULF_BALL_VECTOR_SPEED    = 2.0;

    // Paddle Speed
    public static final int MAX_PADDLE_SPEED                = 10;
    public static final int DEFAULF_PADDLE_SPEED            = 5;
    public static final int MIN_PADDLE_SPEED                = 1;

    // Screen Size
    public static final int    SCREEN_WIDTH                 = 800;
    public static final int    SCREEN_HEIGHT                = 600;
}