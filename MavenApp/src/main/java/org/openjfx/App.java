package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        StageBuilder.setInitialStage(stage);

        testServer();
    }

    public static void main(String[] args) {
        launch();
    }

    public void testServer(){
        SharedInstance.getInstance().server = new TCPServer(1337);
        new Thread(SharedInstance.getInstance().server).start();
    }
}