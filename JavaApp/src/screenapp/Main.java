package screenapp;

import javafx.application.Application;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Main extends Application {

    ListView<String> listView;

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
                c.addResult(0, 0, 255, 255, 255);
            }
            try {
                Thread.sleep(5000);
            } catch (Exception e){

            }
        }
    }
}