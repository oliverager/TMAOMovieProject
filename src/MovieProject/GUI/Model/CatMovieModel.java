package MovieProject.GUI.Model;

import MovieProject.BE.Movie;
import MovieProject.BLL.CatMovieManager;
import MovieProject.BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CatMovieModel {

    private ObservableList<Movie> moviesToBeViewed;
    private CatMovieManager catMovieManager;

    public CatMovieModel(ObservableList<Movie> moviesToBeViewed, CatMovieManager catMovieManager) throws Exception {

        catMovieManager=new CatMovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(catMovieManager.getAllMoviesFromCategory(1));
    }

    public ObservableList<Movie> getObservableMovies()
    {
        return moviesToBeViewed;
    }



}
