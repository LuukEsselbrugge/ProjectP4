package screenapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageBuilder {
    private static Stage currentStage;
    private static Scene currentScene;

    static void setInitialStage(Stage stage) {
        currentStage = stage;

        try {
            if(currentScene == null) {
                currentScene = new Scene(FXMLLoader.load(StageBuilder.class.getResource("searchscreen.fxml")));
            }

            currentScene.getStylesheets().add("StyleSheet.css");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //currentStage.initStyle(StageStyle.UNDECORATED);
        //currentStage.setMaximized(true);
        currentStage.setWidth(1366);
        currentStage.setHeight(768);
        currentStage.setScene(currentScene);
        currentStage.show();
        currentStage.requestFocus();
    }

    static void newScene(String fxml) {
        FXMLLoader loader = new FXMLLoader(StageBuilder.class.getResource(fxml));

        try {
            currentScene.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentStage.setScene(currentScene);
    }

    static void newScene(String fxml, String stylesheet) {
        FXMLLoader loader = new FXMLLoader(StageBuilder.class.getResource(fxml));

        try {
            currentScene.setRoot(loader.load());
            currentScene.getStylesheets().removeAll();
            currentScene.getStylesheets().add(stylesheet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentStage.setScene(currentScene);
    }

    static Stage getCurrentStage() {
        return currentStage;
    }
}
