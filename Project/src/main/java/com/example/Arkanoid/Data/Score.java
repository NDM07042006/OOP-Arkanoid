package main.java.com.example.Arkanoid.Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.java.arkanoid.engine.*;

public class Score {
    private int score;
    private int highScore;
    private static final String FILE_PATH = System.getProperty("user.dir") + "Data/highscore.dat";

    public Score() {
        score = 0;
        highScore = loadHighScore();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int x) {
        this.score = x;
    }

    

    public int getHighScore() {
        return highScore;
    }



    private Bricks bricks;

    public Bricks getBricks() {
        return bricks;
    }

    public void setBricks(Bricks bricks) {
        this.bricks = bricks;
    }

    public void updateScore() {
        if (bricks != null) {
            System.out.println("Updating score... current=" + score + " + " + bricks.getPoint_given());
            score += bricks.getPoint_given();
            if (score > highScore) {
                highScore = score;
                System.out.println("New high score! " + highScore);
                saveHighScore(highScore);
            }
        } else {
            System.out.println("brick is null!");
        }
    }

    public void addScore(int points) {
        score += points;
        if (score > highScore) {
            highScore = score;
            saveHighScore(highScore);
            System.out.println("New high score saved: " + highScore);
        }
    }


    private void saveHighScore(int highScore) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeInt(highScore);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    private int loadHighScore() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return ois.readInt();
        } catch (Exception e) {
            // TODO: handle exception
            return 0;
        }
    }
}