package MovieProject.GUI.Controller;

import MovieProject.BE.Categories;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
//import MovieProject.GUI.Model;
import MovieProject.GUI.Model.CategoriesModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import MovieProject.GUI.Model.MovieModel;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class NewMovieController extends BaseController implements Initializable {


    private CategoriesModel categoriesModel;


    private ObservableList<Categories> getCategoriesToBeViewed;
    @FXML
    private Button addMovie;

    @FXML
    private Button btnChooseFile;

    @FXML
    private Button btnSelectCategory;

    @FXML
    private Button cancelMovie;

    @FXML
    private TableView<Categories> lstCategories;

    @FXML
    private TableView<Categories> lstSelectedCategory;

    @FXML
    private TableColumn<Categories, String> tableCategory;

    @FXML
    private TableColumn<Categories, String> tableSelectedCategory;

    @FXML
    private TextField txtFilePath;

    @FXML
    private TextField txtImdbRating;

    @FXML
    private TextField txtMovieTitle;

    @FXML
    private TextField txtUserRating;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            categoriesModel = new CategoriesModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        tableCategory.setCellValueFactory(new PropertyValueFactory<>("Categories"));
        lstCategories.setItems(categoriesModel.getCategoriesToBeViewed());





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

    public void handleSelectCategory(ActionEvent actionEvent) {
    }
}