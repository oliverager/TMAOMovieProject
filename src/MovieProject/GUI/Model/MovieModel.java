package MovieProject.GUI.Model;

// Java imports
import MovieProject.BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Project imports
import MovieProject.BE.Movie;

public class MovieModel {
    private ObservableList<Movie> moviesToBeViewed;
    private MovieManager movieManager;

    public MovieModel() throws Exception {
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(movieManager.getMovies());
    }
    public ObservableList<Movie> getObservableMovies(){
        return moviesToBeViewed;
    }

    public void addMovie(String name, double rating, String fileLink) throws Exception {
        movieManager.addMovie(name, rating, fileLink);
    }
    public void deletedMovie(Movie deletedMovie) throws Exception {
        movieManager.deletedMovie(deletedMovie);
    }
}