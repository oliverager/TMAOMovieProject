package MovieProject.GUI.Controller;

import MovieProject.BE.Categories;


import MovieProject.BE.Movie;
import MovieProject.GUI.Model.CatMovieModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
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

    public TextArea txtDescription;
    //Importing and instantiating different models and FXML elements into the NewMovieController constructor.
    private MovieModel movieModel;
    private MainViewController mainController;
    private CategoriesModel categoriesModel;
    private CatMovieModel catMovieModel;
    private ObservableList<Categories> getCategoriesToBeViewed;
    @FXML
    private Button cancelMovie,btnSelectCategory,btnImageChooseFile,btnMovieChooseFile, addMovie,btnDeselectCategory;
    @FXML
    private TableView<Categories> lstCategories,lstSelectedCategory;
    @FXML
    private TableColumn<Categories, String> tableCategory,tableSelectedCategory;
    @FXML
    private TextField txtMovieFilePath, txtImageFilePath, txtImdbRating,txtMovieTitle,txtUserRating;






    //Initialize method that opens up the window and feeds data to the table-vies.
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //instantiating the models we need to use.
        try {
            categoriesModel = new CategoriesModel();
            movieModel = new MovieModel();
            catMovieModel = new CatMovieModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //lstCategories is a table view but is called lst due to updates. These lines feed the data to the table-view.
        tableCategory.setCellValueFactory(new PropertyValueFactory<>("Categories"));
        lstCategories.setItems(categoriesModel.getCategoriesToBeViewed());

        tableSelectedCategory.setCellValueFactory(new PropertyValueFactory<>("Categories"));
    }

    //gets our model
    public void setup() {
        MovieModel movieModel = getModel();
    }
    //the method that adds and binds the movie with the categories you selected.
    public void handleAddMovie(ActionEvent actionEvent) throws Exception {

        //getting info from our txt-lines and table-views.
        String title = txtMovieTitle.getText();
        String description = txtDescription.getText();
        Double userRating = Double.parseDouble(txtUserRating.getText());
        Double imdbRating = Double.parseDouble(txtImdbRating.getText());
        String mfPath = txtMovieFilePath.getText();
        String ifPath = txtImageFilePath.getText();

        //used in our for loop to generate category and movie matches.
        int sizeOfList = lstSelectedCategory.getItems().size();


        Stage stage = (Stage) addMovie.getScene().getWindow();
        stage.close();


        if (userRating.doubleValue() > 0 && userRating.doubleValue() <= 10 && imdbRating.doubleValue() > 0 && imdbRating.doubleValue() <= 10) {
            try {

                //adding the movie
                movieModel.addMovie(title, description, userRating, imdbRating, mfPath, ifPath);


                /* iterable for loop where I have to be smaller than the size of the list of selected categories.
                  I increase each time it goes through the loop, each pass through the loop connects a category with the movie
                */
                for (int i = 0; i < sizeOfList; i++) {

                    //
                    int sizeofMovie = movieModel.showList().size();
                    Movie movie = movieModel.showList().get(sizeofMovie - 1);
                    Categories categories = lstSelectedCategory.getItems().get(i);
                    catMovieModel.addMovieToCategory(movie ,categories);
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
    public void handleImageChooseFile(ActionEvent actionEvent) {
        try {
            mainController = new MainViewController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        FileChooser fc = new FileChooser();
        Stage fileStage = (Stage) cancelMovie.getScene().getWindow();
        File f = fc.showOpenDialog(fileStage);
        if (f.getPath().endsWith(".jpg") || f.getPath().endsWith(".png")) {
            txtImageFilePath.setText(f.getPath());
        }
        else{
            mainController.informationUser("Your file needs to be in jpg or png format");
        }
    }

    public void handleMovieChooseFile(ActionEvent actionEvent) {
        try {
            mainController = new MainViewController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        FileChooser fc = new FileChooser();
        Stage fileStage = (Stage) cancelMovie.getScene().getWindow();
        File f = fc.showOpenDialog(fileStage);
        if (f.getPath().endsWith(".mp4") || f.getPath().endsWith("mpeg4")) {
            txtMovieFilePath.setText(f.getPath());
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
        Categories deselectedCategory = lstSelectedCategory.getSelectionModel().getSelectedItem();
        lstSelectedCategory.getItems().remove(deselectedCategory);
        lstCategories.getItems().add(deselectedCategory);

    }
}