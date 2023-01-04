package MovieProject.GUI.Controller;

import MovieProject.BE.Categories;
import MovieProject.BE.Movie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
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
    public Button movieAdd;
    public Button movieDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoriesTableColumn.setCellValueFactory(new PropertyValueFactory<Categories, String>("Categories"));

        //categoriesTableView.getItems(categoriesToBeViewed);

        NameTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("Name"));
        RatingTableColumn.setCellValueFactory(new PropertyValueFactory<Movie, Double>("Rating"));

        //MovieTableView.getItems(moviesToBeViewed);
    }
}
