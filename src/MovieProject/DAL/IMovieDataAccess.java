package MovieProject.DAL;

import MovieProject.BE.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;


import java.util.List;

public interface IMovieDataAccess {
    List<Movie> getAllMovies() throws SQLServerException;

    Movie addMovie(String name, double rating, String fileLink, String lastview) throws Exception;

    void deleteMovie(Movie movie) throws Exception;
}