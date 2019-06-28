package screenapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

import static java.awt.Color.WHITE;

public class RouteController implements Initializable {
    @FXML
    Label colorLbl;
    @FXML
    Label toFindLbl;
    @FXML
    Button cancelSearchBtn;

    /**
     * Initialize the view for the route.
     * @param url , URl url
     * @param resourceBundle , Resourcebundle for display
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Book book = SharedInstance.getInstance().books.get(SharedInstance.getInstance().search_q);

        if(SharedInstance.getInstance().counter < 2){
            SharedInstance.getInstance().counter++;
        }else{
            SharedInstance.getInstance().counter = 0;
        }

        colorLbl.setStyle("-fx-background-color: white");

        int[] RGB = {0, 0, 0};


        switch (SharedInstance.getInstance().counter){
            case 0:
                colorLbl.setText("RED");
                colorLbl.setTextFill(Paint.valueOf("red"));
                RGB[0] = 255;
                break;
            case 1:
                colorLbl.setText("GREEN");
                colorLbl.setTextFill(Paint.valueOf("green"));
                RGB[1] = 255;
                break;
            case 2:
                colorLbl.setText("BLUE");
                colorLbl.setTextFill(Paint.valueOf("blue"));
                RGB[2] = 255;
                break;
        }

        Shelf shelf = SharedInstance.getInstance().result;

        toFindLbl.setText("The location is closet " + shelf.getBookshelf() + " in column " + shelf.getCol() + " on row " + shelf.getRow());

        String kast = "Kast" + shelf.getBookshelf();

        for(Client client : SharedInstance.getInstance().server.getClients()){
            if(client.getID().equals(kast)){
                client.addResult(shelf.getCol(), shelf.getRow(), RGB[0], RGB[1], RGB[2]);
            }
        }
    }

    @FXML
    void cancelSearch() {
        SharedInstance.getInstance().books = null;

        StageBuilder.newScene("searchscreen.fxml");
    }
}
