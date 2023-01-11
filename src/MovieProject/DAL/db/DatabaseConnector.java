package MovieProject.DAL.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;

public class DatabaseConnector {


    private static final String PROP_FILE = "TMAOMovieProject/Data/database.settings";




    private SQLServerDataSource dataSource;

    public DatabaseConnector() throws IOException {

        Properties databaseProperties = new Properties();
        databaseProperties.load(new FileInputStream(new File(PROP_FILE)));

            String server = databaseProperties.getProperty("Server");
        String database = databaseProperties.getProperty("Database");
        String user = databaseProperties.getProperty("User");
        String password = databaseProperties.getProperty("Password");

        dataSource = new SQLServerDataSource();
        dataSource.setServerName(server);
        dataSource.setDatabaseName(database);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setTrustServerCertificate(true);

    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }


    public static void main(String[] args) throws Exception {


        MovieDAO_DB movieDAODb=new MovieDAO_DB();
        movieDAODb.addMovie("Prøve2",2.3 , 3.4, "ddddd");

        }
    }

