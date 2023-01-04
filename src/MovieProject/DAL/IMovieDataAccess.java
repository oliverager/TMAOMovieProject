package MovieProject.DAL;

import MovieProject.BE.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;


import java.util.List;

public interface IMovieDataAccess {
    List<Movie> getAllMovies() throws SQLServerException;

    Movie addMovie() throws Exception;

    void deleteMovie() throws Exception;
}