package screenapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

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

        if(SharedInstance.getInstance().counter < 3){
            SharedInstance.getInstance().counter++;
        }else{
            SharedInstance.getInstance().counter = 0;
        }

        int[] RGB = {0, 0, 0};
        switch (SharedInstance.getInstance().counter){
            case 0:
                //Todo Insert TCP server connection and color..
                colorLbl.setText("RED");
                colorLbl.setTextFill(Paint.valueOf("red"));
                RGB[0] = 255;
                break;
            case 1:
                //Todo Insert TCP server connection and color..
                colorLbl.setText("GREEN");
                colorLbl.setTextFill(Paint.valueOf("green"));
                RGB[1] = 255;
                break;
            case 2:
                //Todo insert TCP server connection and color.
                colorLbl.setText("BLUE");
                colorLbl.setTextFill(Paint.valueOf("blue"));
                RGB[2] = 255;
                break;
        }
        toFindLbl.setText(book.getTitle());

        Shelf shelf = SharedInstance.getInstance().result;

        String kast = "Kast " + shelf.getBookshelf();

        for(Client client : SharedInstance.getInstance().server.getClients()){
            if(client.getID().equals(kast)){
                client.addResult(shelf.getRow(), shelf.getCol(), RGB[0], RGB[1], RGB[2]);
            }
        }
    }

    @FXML
    void cancelSearch() {
        SharedInstance.getInstance().books = null;

        StageBuilder.newScene("searchscreen.fxml");
    }
}
