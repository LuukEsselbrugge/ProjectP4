package screenapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("homescreen.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("StyleSheet.css");

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        root.requestFocus();

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
            }catch (Exception e){

            }
        }

    }
}