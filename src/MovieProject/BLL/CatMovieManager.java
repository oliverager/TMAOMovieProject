package MovieProject.BLL;

import MovieProject.BE.Categories;
import MovieProject.BE.Movie;
import MovieProject.DAL.IMovieDAO;
import MovieProject.DAL.db.CatMovieDAO_DB;
import MovieProject.DAL.db.MovieDAO_DB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CatMovieManager {

    CatMovieDAO_DB catMovieDAODb;

    public CatMovieManager() throws SQLException, IOException {
         catMovieDAODb = new CatMovieDAO_DB();

    }

    public List<Movie> getAllMoviesFromCategory(int categorieId) throws Exception {
        return catMovieDAODb.getAllMoviesFromCategory(categorieId);
    }

    public void addMovieToCategory(int  movieNumber, Categories selectedCategory) throws SQLException
    {
        catMovieDAODb.addMovieToCategory(movieNumber, selectedCategory);
    }



}
