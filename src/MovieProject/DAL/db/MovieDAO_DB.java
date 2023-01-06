package MovieProject.DAL.db;

import MovieProject.BE.Movie;
import MovieProject.DAL.IMovieDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.security.cert.Extension;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class MovieDAO_DB implements IMovieDAO {
    private DatabaseConnector databaseConnector;

    public MovieDAO_DB() throws IOException {
        databaseConnector = new DatabaseConnector();
    }


    @Override
    public List<Movie> getAllMovies() throws SQLServerException {
        ArrayList<Movie> allMovies = new ArrayList<>();
        boolean toOld;

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM dbo.Movie;";


            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Song object
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                double rating = rs.getDouble("Rating");
                double imdb = rs.getDouble("imdb");
                String filelink = rs.getString("Filelink");
                LocalDate lastview = rs.getDate("Lastview").toLocalDate();


                int days= (int) DAYS.between(lastview, LocalDate.now());

                if (days>=730)
                    toOld=true;
                else
                    toOld=false;
                //  System.out.println(time);



               Movie movie = new Movie(id, name, rating, imdb, filelink, lastview, toOld);


               allMovies.add(movie);
            }

            return allMovies;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Could not get Songs from database", ex);
        }
    }

    @Override
    public Movie addMovie(String name, double rating, double imdb, String fileLink) throws Exception {
        String sql = "INSERT INTO movie (name,rating, imdb, filelink, lastview) VALUES (?,?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1, name);
            stmt.setDouble(2, rating);
            stmt.setDouble(3, imdb);
            stmt.setString(4, fileLink);
            stmt.setDate(5, Date.valueOf(LocalDate.now()));


            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create Song object and send up the layers
            Movie movie = new Movie(id, name, rating, imdb,fileLink, LocalDate.now(),false);
            System.out.println(movie);
            return movie;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not create song", ex);
        }
    }



    @Override
    public void deletedMovie(Movie deletedMovie) throws Exception {
        try (Connection conn = databaseConnector.getConnection()) {

            String sql = "DELETE CatMovie FROM CatMovie inner join Movie on Movie.Id=CatMovie.MOvieId " +
                    "WHERE CatMovie.ID=? DELETE from Movie WHERE Movie.Id=?;";

            PreparedStatement stmt = conn.prepareStatement(sql);


            //Vi sletter fra vores song tabel. Men eftersom vi har foreign keys er vi nød til specificere dem. Derfor skrives inner join.
            //Her beskriver bindingen mellem child og parent tabellerne.
            // Til sidst beskrives hvilket parameter, her movie nummer, vi sletter efter. Nu vil movie og referencer i krydstabellen
            //blive slettet.

            //Bind parameters

            stmt.setInt(1, deletedMovie.getId());
            stmt.setInt(2, deletedMovie.getId());

            stmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not delete song", ex);

        }

    }

    @Override
    public void updateMovie(Movie movie) throws Exception {

        String sql = "UPDATE movie SET  Lastview=? WHERE ID = ?";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
           stmt.setInt(2, movie.getId());


            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update song", ex);
        }
    }




}

