//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.java.com.example.Arkanoid;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.com.example.Arkanoid.UI.EndScene;

public class Main extends Application {
    public void start(Stage stage) {
        EndScene endScene = new EndScene(stage);
        endScene.show();
        System.out.println(stage.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
