package MovieProject.DAL.db;

import MovieProject.BE.Movie;
import MovieProject.DAL.IMovieDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO_DB implements IMovieDAO {
    private DatabaseConnector databaseConnector;
    public MovieDAO_DB() throws IOException {
        databaseConnector = new DatabaseConnector();
    }


    @Override
    public List<Movie> getAllMovies() throws SQLServerException {
        ArrayList<Movie> allMovies = new ArrayList<>();


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
                String filelink = rs.getString("Filelink");
                String lastview = rs.getString("Lastview");


                //  System.out.println(time);


                Movie movie = new Movie(id, name, rating, filelink, lastview);


                allMovies.add(movie);
            }

            System.out.println(allMovies);
            return allMovies;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Could not get Songs from database", ex);
        }
    }
    @Override

    public Movie addMovie(String name, double rating, String fileLink, String lastview) throws Exception {

        String sql = "INSERT INTO movie (name,rating, filelink, lastview) VALUES (?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1, name);
            stmt.setDouble(2,rating);
            stmt.setString(3, fileLink);
            stmt.setString(4, lastview);



            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if ( rs.next()) {
                id = rs.getInt(1);
            }

            // Create Song object and send up the layers
            Movie movie = new Movie(id, name, rating, fileLink, lastview);
            System.out.println(movie);
            return movie;
        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not create song", ex);
        }



    public Movie addMovie(String name, double rating, String fileLink) throws Exception {
        return null;

    }

    @Override

    public void deleteMovie(Movie movie) throws Exception {
        try(Connection conn = databaseConnector.getConnection()) {


            String sql= "DELETE CatMovie FROM CatMovie inner join Movie on Movie.Id=CatMovie.MOvieId " +
                        "WHERE CatMovie.ID=? DELETE from Movie WHERE Movie.Id=?;";

            PreparedStatement stmt = conn.prepareStatement(sql);


            //Vi sletter fra vores song tabel. Men eftersom vi har foreign keys er vi nød til specificere dem. Derfor skrives inner join.
            //Her beskriver bindingen mellem child og parent tabellerne.
            // Til sidst beskrives hvilket parameter, her movie nummer, vi sletter efter. Nu vil movie og referencer i krydstabellen
            //blive slettet.

            //Bind parameters

            stmt.setInt(1, movie.getId());
            stmt.setInt(2, movie.getId());

            stmt.executeUpdate();


        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not delete song", ex);

        }

    public void deletedMovie(Movie movie) throws Exception {

    }
}
