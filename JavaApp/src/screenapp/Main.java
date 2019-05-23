package screenapp;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        StageBuilder.setInitialStage(primaryStage);

        //testServer();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void testServer(){
        TCPServer t = new TCPServer(1337);
        new Thread(t).start();

        while(true) {
            System.out.println("Connected clients:" + t.getClients().size());
            for (Client c : t.getClients()) {
                c.addResult(1, 1, 255, 255, 255);
               // c.addResult(2, 2, 0, 0, 255);
            }
            try {
                Thread.sleep(2000);
            } catch (Exception e){

            }
        }
    }
}