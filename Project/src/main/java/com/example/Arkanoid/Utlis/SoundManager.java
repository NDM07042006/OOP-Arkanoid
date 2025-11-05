package main.java.com.example.Arkanoid.Utlis;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Quản lý âm thanh trong game Arkanoid
 * Singleton pattern - chỉ có 1 instance duy nhất
 */
public class SoundManager {
    private static SoundManager instance;

    // Map lưu sound effects (âm thanh ngắn)
    private Map<String, AudioClip> soundEffects;

    // Map lưu background music (nhạc nền)
    private Map<String, MediaPlayer> backgroundMusics;

    // MediaPlayer hiện tại đang phát
    private MediaPlayer currentMusic;
    private String currentMusicName;

    // Volume settings
    private double sfxVolume = 0.7;
    private double musicVolume = 0.5;
    private boolean sfxEnabled = true;
    private boolean musicEnabled = true;

    // Fade transition duration
    private final Duration FADE_DURATION = Duration.millis(1000);

    private SoundManager() {
        soundEffects = new HashMap<>();
        backgroundMusics = new HashMap<>();
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * Load sound effect từ file
     */
    private void loadSoundEffect(String name, String filePath) {
        try {
            URL resource = getClass().getResource(filePath);
            if (resource != null) {
                AudioClip clip = new AudioClip(resource.toString());
                clip.setVolume(sfxVolume);
                soundEffects.put(name, clip);
                System.out.println("✓ Loaded sound: " + name);
            } else {
                System.err.println("✗ Sound file not found: " + filePath);
            }
        } catch (Exception e) {
            System.err.println("✗ Error loading " + name + ": " + e.getMessage());
        }
    }

    /**
     * Load background music từ file
     */
    private void loadBackgroundMusic(String name, String filePath) {
        try {
            URL resource = getClass().getResource(filePath);
            if (resource != null) {
                Media media = new Media(resource.toString());
                MediaPlayer player = new MediaPlayer(media);
                player.setVolume(musicVolume);
                player.setCycleCount(MediaPlayer.INDEFINITE); // Loop vô hạn
                backgroundMusics.put(name, player);
                System.out.println("✓ Loaded music: " + name);
            } else {
                System.err.println("✗ Music file not found: " + filePath);
            }
        } catch (Exception e) {
            System.err.println("✗ Error loading " + name + ": " + e.getMessage());
        }
    }

    /**
     * Load tất cả âm thanh - gọi 1 lần khi khởi động game
     */
    public void loadAllSounds() {
        System.out.println("\n========== LOADING SOUNDS ==========");

        // === MENU SOUNDS ===
        loadSoundEffect("button_hover", "/sounds/button_hover.mp3");
        loadSoundEffect("button_click", "/sounds/button_click.wav");

        // === GAME SOUNDS ===
        loadSoundEffect("paddle_hit", "/sounds/paddle_hit.wav");
        loadSoundEffect("wall_hit", "/sounds/wall_hit.wav");
        loadSoundEffect("brick_break", "/sounds/brick_break.wav");
        loadSoundEffect("life_lost", "/sounds/life_lost.wav");
        loadSoundEffect("level_complete", "/sounds/level_complete.wav");
        loadSoundEffect("game_over", "/sounds/game_over.wav");

        // === BACKGROUND MUSIC ===
        loadBackgroundMusic("menu", "/sounds/menu_theme.mp3");
        loadBackgroundMusic("game", "/sounds/game_theme.mp3");

        System.out.println("====================================\n");
    }

    // ==================== SOUND EFFECTS ====================

    /**
     * Phát sound effect
     */
    public void playSoundEffect(String name) {
        if (!sfxEnabled) return;

        AudioClip clip = soundEffects.get(name);
        if (clip != null) {
            clip.play();
        }
    }

    /**
     * Phát sound effect với volume tùy chỉnh
     */
    public void playSoundEffect(String name, double volumeMultiplier) {
        if (!sfxEnabled) return;

        AudioClip clip = soundEffects.get(name);
        if (clip != null) {
            clip.play(volumeMultiplier * sfxVolume);
        }
    }

    // === MENU SOUNDS ===

    public void playButtonHover() {
        playSoundEffect("button_hover", 0.5);
    }

    public void playButtonClick() {
        playSoundEffect("button_click");
    }

    // === GAME SOUNDS ===

    public void playPaddleHit() {
        playSoundEffect("paddle_hit");
    }

    public void playWallHit() {
        playSoundEffect("wall_hit");
    }

    public void playBrickBreak() {
        playSoundEffect("brick_break");
    }

    public void playLifeLost() {
        playSoundEffect("life_lost");
    }

    public void playLevelComplete() {
        playSoundEffect("level_complete");
    }

    public void playGameOver() {
        playSoundEffect("game_over");
    }

    // ==================== BACKGROUND MUSIC ====================

    /**
     * Phát background music với fade in
     */
    public void playBackgroundMusic(String name) {
        if (!musicEnabled) return;

        // Nếu đang phát nhạc này rồi thì không làm gì
        if (currentMusicName != null && currentMusicName.equals(name)) {
            return;
        }

        MediaPlayer newPlayer = backgroundMusics.get(name);
        if (newPlayer == null) {
            System.err.println("⚠ Music not found: " + name);
            return;
        }

        // Fade out nhạc cũ -> Fade in nhạc mới
        if (currentMusic != null) {
            fadeOutAndStop(currentMusic, () -> fadeInAndPlay(newPlayer));
        } else {
            fadeInAndPlay(newPlayer);
        }

        currentMusic = newPlayer;
        currentMusicName = name;
    }

    /**
     * Chuyển sang nhạc menu
     */
    public void playMenuMusic() {
        playBackgroundMusic("menu");
    }

    /**
     * Chuyển sang nhạc game
     */
    public void playGameMusic() {
        playBackgroundMusic("game");
    }

    /**
     * Fade in music
     */
    private void fadeInAndPlay(MediaPlayer player) {
        player.setVolume(0);
        player.play();

        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(player.volumeProperty(), 0)),
                new KeyFrame(FADE_DURATION, new KeyValue(player.volumeProperty(), musicVolume))
        );
        fadeIn.play();
    }

    /**
     * Fade out music và stop
     */
    private void fadeOutAndStop(MediaPlayer player, Runnable onFinished) {
        Timeline fadeOut = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(player.volumeProperty(), player.getVolume())),
                new KeyFrame(FADE_DURATION, new KeyValue(player.volumeProperty(), 0))
        );
        fadeOut.setOnFinished(e -> {
            player.stop();
            if (onFinished != null) {
                onFinished.run();
            }
        });
        fadeOut.play();
    }

    /**
     * Dừng nhạc nền với fade out
     */
    public void stopBackgroundMusic() {
        if (currentMusic != null) {
            fadeOutAndStop(currentMusic, null);
            currentMusicName = null;
        }
    }

    /**
     * Pause nhạc nền
     */
    public void pauseBackgroundMusic() {
        if (currentMusic != null) {
            currentMusic.pause();
        }
    }

    /**
     * Resume nhạc nền
     */
    public void resumeBackgroundMusic() {
        if (currentMusic != null && musicEnabled) {
            currentMusic.play();
        }
    }

    // ==================== VOLUME CONTROL ====================

    /**
     * Set volume cho sound effects (0.0 - 1.0)
     */
    public void setSfxVolume(double volume) {
        this.sfxVolume = Math.max(0.0, Math.min(1.0, volume));
        for (AudioClip clip : soundEffects.values()) {
            clip.setVolume(this.sfxVolume);
        }
    }

    /**
     * Set volume cho background music (0.0 - 1.0)
     */
    public void setMusicVolume(double volume) {
        this.musicVolume = Math.max(0.0, Math.min(1.0, volume));
        for (MediaPlayer player : backgroundMusics.values()) {
            player.setVolume(this.musicVolume);
        }
        // Update current playing music
        if (currentMusic != null) {
            currentMusic.setVolume(this.musicVolume);
        }
    }

    /**
     * Bật/tắt sound effects
     */
    public void setSfxEnabled(boolean enabled) {
        this.sfxEnabled = enabled;
    }

    /**
     * Bật/tắt background music
     */
    public void setMusicEnabled(boolean enabled) {
        this.musicEnabled = enabled;
        if (!enabled && currentMusic != null) {
            currentMusic.pause();
        } else if (enabled && currentMusic != null) {
            currentMusic.play();
        }
    }

    // ==================== GETTERS ====================

    public double getSfxVolume() {
        return sfxVolume;
    }

    public double getMusicVolume() {
        return musicVolume;
    }

    public boolean isSfxEnabled() {
        return sfxEnabled;
    }

    public boolean isMusicEnabled() {
        return musicEnabled;
    }

    /**
     * Giải phóng tất cả resources - gọi khi thoát game
     */
    public void dispose() {
        stopBackgroundMusic();
        soundEffects.clear();
        for (MediaPlayer player : backgroundMusics.values()) {
            player.dispose();
        }
        backgroundMusics.clear();
        currentMusic = null;
        currentMusicName = null;
    }
}