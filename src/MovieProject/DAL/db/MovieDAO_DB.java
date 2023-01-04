package MovieProject.DAL.db;

import MovieProject.BE.Movie;
import MovieProject.DAL.IMovieDataAccess;
import MovieProject.DAL.db.DatabaseConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO_DB implements IMovieDataAccess {
    private DatabaseConnector databaseConnector;



    public MovieDAO_DB() throws IOException {
        databaseConnector = new DatabaseConnector();
    }


    @Override
    public List<Movie> getAllMovies() throws SQLServerException {
        ArrayList<Movie> allMovies = new ArrayList<>();


        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
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


                Movie movie = new Movie(id, name,rating,filelink, lastview);



                allMovies.add(movie);
            }

            System.out.println(allMovies);
            return allMovies;

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new RuntimeException("Could not get Songs from database", ex);
        }
    }

    @Override
    public Movie addMovie() throws Exception {
        return null;
    }

    @Override
    public void deleteMovie() throws Exception {

    }


}
