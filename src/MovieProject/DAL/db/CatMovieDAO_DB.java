package MovieProject.DAL.db;

import MovieProject.BE.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class CatMovieDAO_DB {

    private DatabaseConnector databaseConnector;


    public CatMovieDAO_DB() throws SQLException {
        databaseConnector = new DatabaseConnector();
    }


    public List<Movie> getAllMoviesFromCategory(int categorieId) throws Exception {
        ArrayList<Movie> allMovies = new ArrayList<>();
        boolean toOld;

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM Movie M,CatMovie CM, Category C  WHERE M.Id = CM.MOvieId and C.Id = CM.CategoryId and CM.CategoryId=" + categorieId + ";";

            //sql koden henter fra vores tre tabeller, hvor movie id i krydstabellen og movie skal være ens
            // og kategorie nummer skal være ens i kategorie og i krydstabellen.


            ResultSet rs = stmt.executeQuery(sql);  //Her vises resultat settet

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Song object
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int rating = rs.getInt("rating");
                int imdb = rs.getInt("imdb");
                String filelink = rs.getString("filelink");
                LocalDate lastview = rs.getDate("lastview").toLocalDate();

                int days = (int) DAYS.between(lastview, LocalDate.now());

                if (days >= 730 && rating < 6)
                    toOld = true;
                else
                    toOld = false;


                Movie movie = new Movie(id, name, rating, imdb, filelink, lastview, toOld); //De hentede data gemmes i sang objekter

                allMovies.add(movie); //Sang objekterne tilføjes en arrayList
            }

            return allMovies; //Arraylisten sendes hele vejen op og vises  til sidst i vores gui.

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not get Songs from database", ex);
        }

        }

    }
