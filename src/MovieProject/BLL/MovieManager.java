package MovieProject.BLL;

import MovieProject.BE.Movie;
import MovieProject.DAL.IMovieDataAccess;
import MovieProject.DAL.db.MovieDAO_DB;

import java.io.IOException;
import java.util.List;

public class MovieManager {

    private IMovieDataAccess movieDAO;

    public MovieManager() throws IOException {
        movieDAO = new MovieDAO_DB();
    }

    public List<Movie> getMovies() throws Exception {
        return movieDAO.getAllMovies();
    }

    public Movie addMovie(String name, double rating, String fileLink, String lastview) throws Exception {
        return movieDAO.addMovie( name, rating,  fileLink,  lastview);
    }

    public void deleteMovie(Movie movie) throws Exception {
        movieDAO.deleteMovie(movie);
    }
}