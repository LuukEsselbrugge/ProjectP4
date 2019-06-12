package screenapp;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        StageBuilder.setInitialStage(primaryStage);

        testServer();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void testServer(){
        SharedInstance.getInstance().server = new TCPServer(1337);
        new Thread(SharedInstance.getInstance().server).start();
    }
}