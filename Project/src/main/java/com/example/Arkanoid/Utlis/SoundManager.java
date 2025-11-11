package main.java.com.example.Arkanoid.Utlis;

import javafx.scene.media.AudioClip;

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

    // Map lưu background music (nhạc nền) - dùng AudioClip thay vì MediaPlayer
    private Map<String, AudioClip> backgroundMusics;

    // AudioClip hiện tại đang phát
    private AudioClip currentMusic;
    private String currentMusicName;

    // Volume settings
    private double sfxVolume = 0.7;
    private double musicVolume = 0.5;
    private boolean sfxEnabled = true;
    private boolean musicEnabled = true;

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
                clip.setVolume(sfxVolume); // Dùng sfxVolume thay vì hardcode
                soundEffects.put(name, clip);
                System.out.println("✓ Loaded sound: " + name + " (volume: " + clip.getVolume() + ")");
            } else {
                System.err.println("✗ Sound file not found: " + filePath);
            }
        } catch (Exception e) {
            System.err.println("✗ Error loading " + name + ": " + e.getMessage());
        }
    }

    /**
     * Load background music từ file - dùng AudioClip
     */
    private void loadBackgroundMusic(String name, String filePath) {
        try {
            URL resource = getClass().getResource(filePath);
            if (resource != null) {
                System.out.println("Loading music from: " + resource.toString());
                AudioClip clip = new AudioClip(resource.toString());
                clip.setVolume(musicVolume); // Dùng musicVolume thay vì hardcode
                clip.setCycleCount(AudioClip.INDEFINITE); // Loop vô hạn
                backgroundMusics.put(name, clip);
                System.out.println("✓ Loaded music: " + name + " (volume: " + clip.getVolume() + ")");
            } else {
                System.err.println("✗ Music file not found: " + filePath);
            }
        } catch (Exception e) {
            System.err.println("✗ Error loading " + name + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Load tất cả âm thanh - gọi 1 lần khi khởi động game
     */
    public void loadAllSounds() {
        System.out.println("\n========== LOADING SOUNDS ==========");

        // === MENU SOUNDS ===
        loadSoundEffect("button_hover", "/com/Arkanoid/sounds/button_hover.mp3");
        loadSoundEffect("button_click", "/com/Arkanoid/sounds/button_click.wav");

        // === GAME SOUNDS ===
        loadSoundEffect("paddle_hit", "/com/Arkanoid/sounds/paddle_hit.wav");
        loadSoundEffect("wall_hit", "/com/Arkanoid/sounds/wall_hit.wav");
        loadSoundEffect("brick_break", "/com/Arkanoid/sounds/brick_break.wav");
        loadSoundEffect("life_lost", "/com/Arkanoid/sounds/lost_life.wav");
        loadSoundEffect("level_complete", "/com/Arkanoid/sounds/level_complete.wav");
        loadSoundEffect("game_over", "/com/Arkanoid/sounds/game_over.wav");

        // === BACKGROUND MUSIC ===
        loadBackgroundMusic("menu", "/com/Arkanoid/sounds/menu_theme.wav");
        loadBackgroundMusic("game", "/com/Arkanoid/sounds/game_theme.wav");

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
        if (!musicEnabled) {
            System.out.println("Music disabled, not playing: " + name);
            return;
        }

        // Nếu đang phát nhạc này rồi thì không làm gì
        if (currentMusicName != null && currentMusicName.equals(name)) {
            System.out.println("Already playing: " + name);
            return;
        }

        AudioClip newMusic = backgroundMusics.get(name);
        if (newMusic == null) {
            System.err.println("⚠ Music not found: " + name);
            System.err.println("Available music: " + backgroundMusics.keySet());
            return;
        }

        try {
            // Dừng nhạc cũ
            if (currentMusic != null) {
                System.out.println("Stopping current music: " + currentMusicName);
                currentMusic.stop();
            }

            // Phát nhạc mới trong background thread để không block UI
            final AudioClip musicToPlay = newMusic;
            final String musicName = name;

            new Thread(() -> {
                try {
                    System.out.println("Playing music: " + musicName + " at volume: " + musicToPlay.getVolume());
                    musicToPlay.play();
                    System.out.println("✓ Started playing: " + musicName);

                    // Kiểm tra xem có đang phát không
                    if (musicToPlay.isPlaying()) {
                        System.out.println("✓ Music is currently playing!");
                    } else {
                        System.err.println("⚠ Music loaded but NOT playing!");
                    }
                } catch (Exception e) {
                    System.err.println("✗ Error playing music " + musicName + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }).start();

            currentMusic = newMusic;
            currentMusicName = name;

        } catch (Exception e) {
            System.err.println("✗ Error playing music " + name + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Chuyển sang nhạc menu
     */
    public void playMenuMusic() {
        try {
            playBackgroundMusic("menu");
        } catch (Exception e) {
            System.err.println("⚠ Cannot play menu music (MP3 codec may not be available): " + e.getMessage());
        }
    }

    /**
     * Chuyển sang nhạc game
     */
    public void playGameMusic() {
        try {
            playBackgroundMusic("game");
        } catch (Exception e) {
            System.err.println("⚠ Cannot play game music (MP3 codec may not be available): " + e.getMessage());
        }
    }

    /**
     * Dừng nhạc nền
     */
    public void stopBackgroundMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusicName = null;
        }
    }

    /**
     * Pause nhạc nền - AudioClip không support pause, chỉ có thể stop
     */
    public void pauseBackgroundMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
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

        // Update tất cả music clips trong map
        for (AudioClip clip : backgroundMusics.values()) {
            clip.setVolume(this.musicVolume);
        }

        // QUAN TRỌNG: Update currentMusic đang phát (nếu có)
        if (currentMusic != null) {
            currentMusic.setVolume(this.musicVolume);
        }
    }

    /**
     * Bật/tắt sound effects
     */
    public void setSfxEnabled(boolean enabled) {
        this.sfxEnabled = enabled;
        System.out.println("SFX " + (enabled ? "enabled" : "disabled"));
    }

    /**
     * Bật/tắt background music
     */
    public void setMusicEnabled(boolean enabled) {
        this.musicEnabled = enabled;
        System.out.println("Music " + (enabled ? "enabled" : "disabled"));

        if (!enabled) {
            // Tắt nhạc - dừng nhạc hiện tại
            if (currentMusic != null && currentMusic.isPlaying()) {
                currentMusic.stop();
                System.out.println("✓ Stopped music: " + currentMusicName);
            }
        } else {
            // Bật nhạc - chỉ phát nếu volume > 0
            if (currentMusicName != null && musicVolume > 0.0) {
                if (currentMusic != null && !currentMusic.isPlaying()) {
                    currentMusic.play();
                    System.out.println("✓ Resumed music: " + currentMusicName + " (volume: " + musicVolume + ")");
                } else if (currentMusic == null) {
                    playBackgroundMusic(currentMusicName);
                }
            } else if (musicVolume == 0.0) {
                System.out.println("⚠ Music enabled but volume is 0, not playing");
            }
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
        for (AudioClip clip : backgroundMusics.values()) {
            clip.stop();
        }
        backgroundMusics.clear();
        currentMusic = null;
        currentMusicName = null;
    }
}
