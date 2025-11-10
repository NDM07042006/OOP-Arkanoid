package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import main.java.com.example.Arkanoid.Utlis.SoundManager;

public class SettingController {
    @FXML
    private Stage stage;
    
    @FXML
    private Slider musicVolumeSlider;
    
    @FXML
    private Slider sfxVolumeSlider;
    
    @FXML
    private Label musicVolumeLabel;
    
    @FXML
    private Label sfxVolumeLabel;
    
    @FXML
    private CheckBox musicEnabledCheckBox;
    
    @FXML
    private CheckBox sfxEnabledCheckBox;
    
    private SoundManager soundManager;

    @FXML
    public void initialize() {
        soundManager = SoundManager.getInstance();
        
        // Load giá trị hiện tại từ SoundManager (trước khi add listeners)
        musicVolumeSlider.setValue(soundManager.getMusicVolume());
        sfxVolumeSlider.setValue(soundManager.getSfxVolume());
        musicEnabledCheckBox.setSelected(soundManager.isMusicEnabled());
        sfxEnabledCheckBox.setSelected(soundManager.isSfxEnabled());
        
        // Set initial state của sliders
        musicVolumeSlider.setDisable(!musicEnabledCheckBox.isSelected());
        sfxVolumeSlider.setDisable(!sfxEnabledCheckBox.isSelected());
        
        // Cập nhật label hiển thị
        updateMusicVolumeLabel(soundManager.getMusicVolume());
        updateSfxVolumeLabel(soundManager.getSfxVolume());
        
        // Add listeners SAU KHI set giá trị ban đầu để tránh trigger không cần thiết
        
        // Listener cho Music Volume Slider - update ngay lập tức
        musicVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue();
            soundManager.setMusicVolume(volume);
            updateMusicVolumeLabel(volume);
        });
        
        // Listener cho SFX Volume Slider - update ngay lập tức
        sfxVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue();
            soundManager.setSfxVolume(volume);
            updateSfxVolumeLabel(volume);
        });
        
        // Listener cho Music Enable/Disable
        musicEnabledCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            soundManager.setMusicEnabled(newValue);
            musicVolumeSlider.setDisable(!newValue);
        });
        
        // Listener cho SFX Enable/Disable
        sfxEnabledCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            soundManager.setSfxEnabled(newValue);
            sfxVolumeSlider.setDisable(!newValue);
        });
    }
    
    private void updateMusicVolumeLabel(double volume) {
        musicVolumeLabel.setText(String.format("%d%%", (int)(volume * 100)));
    }
    
    private void updateSfxVolumeLabel(double volume) {
        sfxVolumeLabel.setText(String.format("%d%%", (int)(volume * 100)));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void testSound() {
        // Test âm thanh button click
        soundManager.playButtonClick();
    }

    @FXML
    public void exitSetting() {
        if (stage != null) stage.close();
    }
}
