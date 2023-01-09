package MovieProject.DAL;

import MovieProject.BE.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;


import java.time.LocalDate;
import java.util.List;


public interface IMovieDAO {
    public List<Movie> getAllMovies() throws Exception;

    public Movie addMovie(String name, double rating, double imdb, String fileLink) throws Exception;

    public void deletedMovie(Movie deletedMovie) throws Exception;

    public void updateMovieLastview(Movie movie) throws Exception;

    public void updateMovieRating(Movie movie, double rating) throws Exception;

    public int getMovieNumber(String movieTitle) throws Exception;

}

