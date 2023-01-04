package MovieProject.DAL;

import MovieProject.BE.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;


import java.util.List;


public interface IMovieDAO {
    public List<Movie> getAllMovies() throws Exception;

    public Movie addMovie(String name, double rating, String fileLink,String lastview) throws Exception;



    void deleteMovie(Movie movie) throws Exception;



}