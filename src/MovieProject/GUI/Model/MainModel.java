package MovieProject.GUI.Model;

// Java imports
import javafx.collections.ObservableList;

// Project imports
import MovieProject.BE.Movie;

public class MainModel {
    private ObservableList<Movie> movies;

    public MainModel() throws Exception {

    }

    public ObservableList<Movie> getObservableMovies(){
        return movies;
    }

    public void addMovie() {

    }
}