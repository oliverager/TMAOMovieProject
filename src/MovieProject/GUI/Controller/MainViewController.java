package MovieProject.GUI.Controller;

import MovieProject.BE.Categories;
import MovieProject.BE.Movie;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController extends BaseController implements Initializable {
    @FXML
    private TableView<Categories> categoriesTableView;
    @FXML
    private TableColumn<Categories, String> categoriesTableColumn;
    @FXML
    private TableView<Movie> MovieTableView;
    @FXML
    private TableColumn<Movie, String> NameTableColumn;
    @FXML
    private TableColumn<Movie, Double> RatingTableColumn;
    public TextArea movieTextArea;
    public Button categoriesAdd;
    public Button categoriesDelete;

    @FXML
    public Button movieAdd;


    public Button movieDelete;

    private MovieModel movieModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoriesTableColumn.setCellValueFactory(new PropertyValueFactory<Categories, String>("Categories"));

        //categoriesTableView.getItems(categoriesToBeViewed);

        NameTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("Name"));
        RatingTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, Double>("Rating"));

        //MovieTableView.getItems(moviesToBeViewed);
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
}
