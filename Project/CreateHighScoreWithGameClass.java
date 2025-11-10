import java.io.*;
import java.util.*;

public class CreateHighScoreWithGameClass {
    public static void main(String[] args) throws Exception {
        // Compile HighScoreEntry.java từ source code của game
        System.out.println("Compiling HighScoreEntry from game source...");
        ProcessBuilder pb = new ProcessBuilder(
            "javac", 
            "-cp", "src/main/java",
            "src/main/java/com/example/Arkanoid/Data/HighScoreEntry.java"
        );
        pb.inheritIO();
        Process p = pb.start();
        p.waitFor();
        
        System.out.println("Creating high scores using compiled class...");
        
        // Chạy code để tạo dữ liệu
        ProcessBuilder pb2 = new ProcessBuilder(
            "java",
            "-cp", "src/main/java",
            "CreateHighScoreData"
        );
        pb2.inheritIO();
        Process p2 = pb2.start();
        p2.waitFor();
    }
}
