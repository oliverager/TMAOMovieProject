package MovieProject.GUI.Controller;

import MovieProject.BE.Categories;
import javafx.collections.ObservableList;
//import MovieProject.GUI.Model;
import MovieProject.GUI.Model.CategoriesModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import MovieProject.GUI.Model.MovieModel;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class NewMovieController extends BaseController implements Initializable {

    @FXML
    private Button addMovie;

    @FXML
    private Button cancelMovie;

    @FXML
    private ListView<?> lstCategory;

    private ObservableList<Categories> getCategoriesToBeViewed;

    @FXML
    private TextField txtFilePath;

    @FXML
    private TextField txtMovieTitle;

    @FXML
    private TextField txtURating;


    private MovieModel movieModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //lstCategory.setItems;
    }


    public void setup() {
        MovieModel movieModel = getModel();
    }


    public void handleAddMovie(ActionEvent actionEvent) {
    }

    public void handleCancelMovie(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelMovie.getScene().getWindow();
        stage.close();
    }

    public void handleChooseFIle(ActionEvent actionEvent) { //Starter et søgevindue til filer. Den kan du bruge.
        String fileName;
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(stage);
        if (file != null) {

            fileName = file.toURI().toString();
            fileName = fileName.substring(6);
            String newFile = fileName;
            fileName = newFile.replace("%20", " ");
            txtFilePath.setText(fileName);
        }
    }
}