package MovieProject.DAL;

import MovieProject.BE.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;


import java.util.List;

public interface IMovieDataAccess {
    public List<Movie> getAllMovies() throws SQLServerException;

    public Movie addMovie() throws Exception;

    public void deleteMovie() throws Exception;
}
