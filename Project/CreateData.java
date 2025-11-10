import java.io.*;
import java.util.*;

// Copy class HighScoreEntry 
class HighScoreEntry implements Serializable, Comparable<HighScoreEntry> {
    private static final long serialVersionUID = 1L;
    private String playerName;
    private int score;
    
    public HighScoreEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }
    
    public String getPlayerName() { return playerName; }
    public int getScore() { return score; }
    
    @Override
    public int compareTo(HighScoreEntry other) {
        return Integer.compare(other.score, this.score);
    }
}

public class CreateData {
    public static void main(String[] args) {
        String jarPath = "target/classes";
        String filePath = "./Data/highScore.dat";
        
        try {
            // Load HighScoreEntry class từ compiled classes
            File classFile = new File("target/classes/main/java/com/example/Arkanoid/Data/HighScoreEntry.class");
            
            if (classFile.exists()) {
                System.out.println("✓ Found compiled HighScoreEntry class");
                
                // Tạo data bằng reflection
                Class<?> entryClass = Class.forName("main.java.com.example.Arkanoid.Data.HighScoreEntry", 
                    true, 
                    new java.net.URLClassLoader(new java.net.URL[]{new File("target/classes").toURI().toURL()}));
                
                List<Object> entries = new ArrayList<>();
                java.lang.reflect.Constructor<?> constructor = entryClass.getConstructor(String.class, int.class);
                
                entries.add(constructor.newInstance("MRNAM", 9999));
                entries.add(constructor.newInstance("DRAGON", 8500));
                entries.add(constructor.newInstance("PHOENIX", 7200));
                entries.add(constructor.newInstance("TIGER", 6100));
                entries.add(constructor.newInstance("EAGLE", 5000));
                entries.add(constructor.newInstance("SHARK", 4200));
                entries.add(constructor.newInstance("WOLF", 3500));
                entries.add(constructor.newInstance("BEAR", 2800));
                entries.add(constructor.newInstance("LION", 1900));
                entries.add(constructor.newInstance("HAWK", 1000));
                
                new File(filePath).getParentFile().mkdirs();
                
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                    oos.writeObject(entries);
                    System.out.println("\n✅ HIGH SCORES CREATED!");
                    System.out.println("📁 " + new File(filePath).getAbsolutePath());
                    System.out.println("\n🏆 Data:");
                    for (int i = 0; i < entries.size(); i++) {
                        Object e = entries.get(i);
                        String name = (String) entryClass.getMethod("getPlayerName").invoke(e);
                        int score = (int) entryClass.getMethod("getScore").invoke(e);
                        System.out.printf("#%-2d %-15s %,10d\n", i+1, name, score);
                    }
                }
            } else {
                System.out.println("Class file not found. Please run 'mvn compile' first.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
