package main.java.com.example.Arkanoid.Data;

import java.io.Serializable;

public class HighScoreEntry implements Serializable, Comparable<HighScoreEntry> {
    private static final long serialVersionUID = 1L;

    private String playerName;
    private int score;

    public HighScoreEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(HighScoreEntry other) {
        // Sort descending (highest score first)
        return Integer.compare(other.score, this.score);
    }

    @Override
    public String toString() {
        return playerName + ": " + score;
    }
}