package MovieProject.BLL;

import MovieProject.BE.Movie;
import MovieProject.DAL.IMovieDAO;
import MovieProject.DAL.db.CatMovieDAO_DB;
import MovieProject.DAL.db.MovieDAO_DB;

import java.sql.SQLException;
import java.util.List;

public class CatMovieManager {

    CatMovieDAO_DB catMovieDAODb;

    public CatMovieManager() throws SQLException {
         catMovieDAODb = new CatMovieDAO_DB();

    }

    public List<Movie> getAllMoviesFromCategory(int categorieId) throws Exception {
        return catMovieDAODb.getAllMoviesFromCategory(categorieId);
    }



}
