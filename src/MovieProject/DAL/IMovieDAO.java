package MovieProject.DAL;

import MovieProject.BE.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;


import java.util.List;

public interface IMovieDAO {
    public List<Movie> getAllMovies() throws SQLServerException;

    public Movie addMovie(String name, double rating, String fileLink) throws Exception;

    public void deletedMovie(Movie movie) throws Exception;
}