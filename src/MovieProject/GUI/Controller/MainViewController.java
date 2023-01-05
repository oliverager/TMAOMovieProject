package MovieProject.GUI.Controller;

import MovieProject.BE.Categories;
import MovieProject.BE.Movie;
import MovieProject.GUI.Model.CategoriesModel;
import MovieProject.GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController extends BaseController implements Initializable {
    @FXML
    private TableColumn ToOldTableColumn,RatingTableColumn,NameTableColumn,categoriesTableColumn;
        @FXML
    private TableView<Categories> categoriesTableView;
    @FXML
    private TableView<Movie> MovieTableView;

    public TextArea movieTextArea;
    public Button categoriesAdd;
    public Button categoriesDelete;

    @FXML
    public Button movieAdd;


    public Button movieDelete;


    MovieModel movieModel;

    CategoriesModel categoriesModel;

    public MainViewController() throws Exception {

        movieModel = new MovieModel();
        categoriesModel=new CategoriesModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setColoumsForMovies();
        setColoumsForCategories();
        keyPressListenerMovie();
        mouseListenerMovie(); //Jeg har ikke lagt ind for kategorier endnu, men nu kan se hvordan de virker.
    }

    public void setColoumsForMovies()
    {
       NameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        RatingTableColumn.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        ToOldTableColumn.setCellValueFactory(new PropertyValueFactory<>("toOld"));
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


    public void mouseListenerMovie()
    {
        MovieTableView.setOnMouseClicked(event -> {
            System.out.println("Hej igen");
        });
    }





    public void handleNewMovie(ActionEvent actionEvent) throws IOException {

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
        stage.show();
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
}
