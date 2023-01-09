package MovieProject.GUI.Controller;

import MovieProject.BE.Categories;
import MovieProject.BE.Movie;
import MovieProject.GUI.Model.CatMovieModel;
import MovieProject.GUI.Model.CategoriesModel;
import MovieProject.GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainViewController extends BaseController implements Initializable {

    public TextField searchField;

    public Button searchButton;

    public TableColumn IMDBTableColumn;
    public Button btnClearCategories;

    @FXML
    private TableColumn ToOldTableColumn,RatingTableColumn,NameTableColumn,categoriesTableColumn;
        @FXML
    private TableView<Categories> categoriesTableView;
    @FXML
    private TableView<Movie> MovieTableView;

    @FXML
    private TextArea movieTextArea;

    @FXML
    private Button movieAdd,movieDelete,categoriesAdd,categoriesDelete,updateRating;

    private String errorText;;
    MovieModel movieModel;
    CategoriesModel categoriesModel;

    CatMovieModel catMovieModel;

    Categories category;
    Movie movie;


    public MainViewController()  {

        try {
            movieModel = new MovieModel();
            categoriesModel=new CategoriesModel();
            catMovieModel = new CatMovieModel();
        } catch (Exception e) {
            displayError(e);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setColoumsForMovies();
        setColoumsForCategories();
        keyPressListenerMovie();
        keyPressListenerCategories();
        mouseListenerMovie();
        listenerMovieList();
        mouseListenerCategories();
        listenerCategoryList();
    }

        public void setColoumsForMovies()
    {
        NameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        RatingTableColumn.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        IMDBTableColumn.setCellValueFactory(new PropertyValueFactory<>("imdb"));
        ToOldTableColumn.setCellValueFactory(new PropertyValueFactory<>("ToOld"));

        MovieTableView.setItems(movieModel.getObservableMovies());
    }
    public void clearCategories(ActionEvent actionEvent) throws Exception {
        MovieTableView.setItems(movieModel.getObservableMovies());
    }

    public void setColoumsForCategories()
    {
        categoriesTableColumn.setCellValueFactory(new PropertyValueFactory<>("Categories"));
        categoriesTableView.setItems(categoriesModel.getCategoriesToBeViewed());
    }

    public void keyPressListenerMovie()
    {
        MovieTableView.setOnKeyPressed(event -> {
            if (event.getCode()== KeyCode.ENTER)
                System.out.println("hej");
        });
    }

    public void keyPressListenerCategories()
    {
        categoriesTableView.setOnKeyPressed(event -> {
            if (event.getCode()== KeyCode.ENTER)
                System.out.println("hej");
        });
    }

    public void mouseListenerMovie()
    {
        MovieTableView.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2) {
                {

                    movie=MovieTableView.getSelectionModel().getSelectedItem();
                    String moviePath=movie.getFileLink();

                    try {
                        playVideo(moviePath);
                    } catch (IOException e) {
                        displayError(e);
                    }
                }
            }
        });
    }

    public void mouseListenerCategories()
    {
        categoriesTableView.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2)
                selectMovieFromCategory();
                MovieTableView.setItems(catMovieModel.getObservableMovies());
        });
    }

    public void listenerMovieList() {
        MovieTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->

        {
            categoriesDelete.setDisable(true);
            movieDelete.setDisable(false);
        });
    }
    public void listenerCategoryList() {
        categoriesTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            movieDelete.setDisable(true);
            categoriesDelete.setDisable(false);
        });
    }


    public void selectMovieFromCategory()
    {
        category = categoriesTableView.getSelectionModel().getSelectedItem();
        int number = category.getId();
        try {
            catMovieModel.showList(number);
        } catch (Exception e) {
            displayError(e);
        }
    }

    public void handleNewMovie(ActionEvent actionEvent) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MovieProject/GUI/View/NewMovie.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();

        NewMovieController controller = loader.getController();
        controller.setModel(movieModel);
        controller.setup();

        stage.setScene(new Scene(root));
        stage.setTitle("New Window");
        stage.initModality(Modality.NONE);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.showAndWait();
        MovieTableView.setItems(movieModel.showList());

    }
    @Override
    public void setup() {

    }

    public void handleNewCategory(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MovieProject/GUI/View/NewCategory.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();

        NewCategoryController controller = loader.getController();
        controller.setCategoriesModel(categoriesModel);
        controller.setup();

        stage.setScene(new Scene(root));
        stage.setTitle("New Category");
        stage.initModality(Modality.NONE);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

public void playVideo(String moviePath) throws IOException {

    boolean filesExits= Files.exists(Path.of(moviePath)); //check om filen eksisterer

    if (filesExits)
    {
        File file = new File(moviePath);
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);

    }
    else
        informationUser("File do not exist!");      //Her kaldes en metode, der viser et vindue med besked om, at filen ikke findes.

    //text file, should be opening in default text editor

}

    public void informationUser(String information){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Regarding movie");
        info.setHeaderText(information + "");
        info.showAndWait();
    }

    private void displayError(Throwable t) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(errorText);
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }



    //Actionevent for when you press the delete button under category table.
    public void deleteCategoriesAction(ActionEvent actionEvent) throws Exception {
        //Tries to execute the method below, if that's not possible we get an exception thrown.
        try {
            confirmationAlertCategory();

        } catch (Exception exc) {
            exc.printStackTrace();
            throw new Exception("Could not delete Category", exc);
        }
    }

    public void deleteMoviesAction(ActionEvent actionEvent) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You are about to delete a Movie");
        alert.setContentText("Are you sure you want to delete?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Movie deletedMovie = MovieTableView.getSelectionModel().getSelectedItem();
            movieModel.deletedMovie(deletedMovie);
        } else {

        }
        MovieTableView.setItems(movieModel.showList());
    }

    public void updateRatingsAction(ActionEvent actionEvent) throws Exception {
        Movie movie = MovieTableView.getSelectionModel().getSelectedItem();
        double rating = Double.parseDouble(JOptionPane.showInputDialog(""));
        movieModel.updateMovieRating(movie, rating);
        MovieTableView.setItems(movieModel.showList());
        if (rating > 0 && rating < 10) {

        } else {
            informationUser("Your rating value needs to be between 1-10");
        }
    }

    public void handleSearch(ActionEvent actionEvent) {
        if (searchButton.getText().equals("Search")) {
            if (searchField.getText() != null) {
                String search = searchField.getText().toLowerCase();
                MovieTableView.setItems(movieModel.searchedMovie(search));
            }
            searchButton.setText("Clear");
        } else if (searchButton.getText().equals("Clear")) {
            searchField.clear();
            MovieTableView.setItems(movieModel.getObservableMovies());
            searchButton.setText("Search");
        }
    }

    //Makes a popup alert window that asks for confirmation that you want to delete the category.
    public void confirmationAlertCategory() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You are about to delete a Category");
        alert.setContentText("Are you sure you want to delete?");
        Optional<ButtonType> result = alert.showAndWait();
        //If you press the OK button it takes the selected item from the category list and removes it.
        if (result.get() == ButtonType.OK) {
            Categories deleteCategory = categoriesTableView.getSelectionModel().getSelectedItem();
            categoriesModel.deletedCategories(deleteCategory);
        }
        //If you're doing anything else than pressing the OK button it doesn't do anything.
        else {

        }
    }
}
