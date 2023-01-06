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

    private MovieModel movieModel;
    private MainViewController mainController;
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
            movieModel = new MovieModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        tableCategory.setCellValueFactory(new PropertyValueFactory<>("Categories"));
        lstCategories.setItems(categoriesModel.getCategoriesToBeViewed());

        tableSelectedCategory.setCellValueFactory(new PropertyValueFactory<>("Categories"));

    }


    public void setup() {
        MovieModel movieModel = getModel();
    }


    public void handleAddMovie(ActionEvent actionEvent) {
        String title = txtMovieTitle.getText();
        Double userRating = Double.parseDouble(txtUserRating.getText());
        Double imdbRating = Double.parseDouble(txtImdbRating.getText());
        String fPath = txtFilePath.getText();

        Stage stage = (Stage) addMovie.getScene().getWindow();
        stage.close();

        try { movieModel.addMovie(title, userRating, imdbRating, fPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleCancelMovie(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelMovie.getScene().getWindow();
        stage.close();
    }

    public void handleChooseFile(ActionEvent actionEvent) {
        try {
            mainController = new MainViewController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        FileChooser fc = new FileChooser();
        Stage fileStage = (Stage) cancelMovie.getScene().getWindow();
        File f = fc.showOpenDialog(fileStage);
        if (f.getPath().endsWith(".mp4") || f.getPath().endsWith("mpeg4")) {
            txtFilePath.setText(f.getPath());
        }
        else{
            mainController.informationUser("Your file needs to be in mp4 or mpeg4 format");
        }







    }

    public void handleSelectCategory(ActionEvent actionEvent) {
        Categories selectedCategory = lstCategories.getSelectionModel().getSelectedItem();
        lstSelectedCategory.getItems().add(selectedCategory);

    }
}