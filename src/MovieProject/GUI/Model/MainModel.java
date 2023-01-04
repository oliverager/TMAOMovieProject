package MovieProject.GUI.Model;

// Java imports
import MovieProject.BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Project imports
import MovieProject.BE.Movie;

public class MainModel {
    private ObservableList<Movie> movies;
    private MovieManager movieManager;

    public MainModel() throws Exception {
        movieManager = new MovieManager();
        movies = FXCollections.observableArrayList();
        movies.addAll(movieManager.getMovies());
    }

    public ObservableList<Movie> getObservableMovies(){
        return movies;
    }

    public void addMovie(String name, double rating, String fileLink, String lastview) throws Exception {
        Movie m = movieManager.addMovie( name,  rating,  fileLink,  lastview);
        movies.add(m);
    }

    public void deleteMovie(Movie movie) throws Exception {
        movieManager.deletedMovie(movie);
    }
}