package screenapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

        currentStage.initStyle(StageStyle.UNDECORATED);
        currentStage.setMaximized(true);
        currentStage.setScene(currentScene);
        currentStage.show();
        currentStage.requestFocus();
    }

    static void newScene(String fxml) {
        try {
            currentScene.setRoot(FXMLLoader.load(StageBuilder.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentStage.setScene(currentScene);
    }

    static void newScene(String fxml, String stylesheet){
        try {
            currentScene.setRoot(FXMLLoader.load(StageBuilder.class.getResource(fxml)));
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

    static Scene getCurrentScene() {
        return currentScene;
    }
}
