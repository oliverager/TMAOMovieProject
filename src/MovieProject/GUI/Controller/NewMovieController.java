package MovieProject.GUI.Controller;

import MovieProject.BE.Categories;


import MovieProject.BE.Movie;
import MovieProject.GUI.Model.CatMovieModel;
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

    //Importing and instanciating different models and FXML elements into the NewMovieController constructor.
    private MovieModel movieModel;
    private MainViewController mainController;
    private CategoriesModel categoriesModel;

    private CatMovieModel catMovieModel;


    private ObservableList<Categories> getCategoriesToBeViewed;

    @FXML
    private Button btnDeselectCategory;
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
            catMovieModel = new CatMovieModel();
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


    public void handleAddMovie(ActionEvent actionEvent) throws Exception {
        String title = txtMovieTitle.getText();
        Double userRating = Double.parseDouble(txtUserRating.getText());
        Double imdbRating = Double.parseDouble(txtImdbRating.getText());
        String fPath = txtFilePath.getText();
        int sizeOfList = lstSelectedCategory.getItems().size();


        Stage stage = (Stage) addMovie.getScene().getWindow();
        stage.close();


        if (userRating.doubleValue() > 0 && userRating.doubleValue() < 11 && imdbRating.doubleValue() > 0 && imdbRating.doubleValue() < 11) {
            try {
                movieModel.addMovie(title, userRating, imdbRating, fPath);
                int movieNumber= movieModel.getMovieNumber(title);



                for (int i = 0; i < sizeOfList; i++) {
                    Categories categories = lstSelectedCategory.getItems().get(i);
                    Movie movie = movieModel.getObservableMovies().get(movieNumber);
                    catMovieModel.addMovieToCategory(movie,categories);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mainController.informationUser("Your rating value needs to be between 1-10");
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
        lstCategories.getItems().remove(selectedCategory);
        lstSelectedCategory.getItems().add(selectedCategory);

    }

    public void handleDeselectCategory (ActionEvent actionEvent) {
        Categories deslectedCategory = lstSelectedCategory.getSelectionModel().getSelectedItem();
        lstSelectedCategory.getItems().remove(deslectedCategory);
        lstCategories.getItems().add(deslectedCategory);

    }
}