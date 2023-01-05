package MovieProject.GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import MovieProject.GUI.Model.MovieModel;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewMovieController extends BaseController implements Initializable {

    private MovieModel movieModel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setup() {
        MovieModel movieModel = getModel();
    }


}
