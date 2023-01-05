package MovieProject.BLL;

import MovieProject.BE.Movie;
import MovieProject.DAL.IMovieDAO;
import MovieProject.DAL.db.MovieDAO_DB;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MovieManager {
    private IMovieDAO movieDAO;
    public MovieManager() throws IOException {
        movieDAO = new MovieDAO_DB();
    }
    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }
    public Movie addMovie(String name, double rating, String fileLink ) throws Exception {

        return movieDAO.addMovie(name, rating,fileLink);
    }
    public void deletedMovie(Movie deletedMovie) throws Exception {
        movieDAO.deletedMovie(deletedMovie);
    }

        public void updateMovie(Movie updateMovie) throws Exception
        {
            movieDAO.updateMovie(updateMovie);
        }


    }
