package MovieProject.DAL.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabaseConnector {

    //Class will easv.mrs.be included when we start working on DATABASES
    private SQLServerDataSource dataSource;

    public DatabaseConnector()
    {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("MovieSystemTMAO");
        dataSource.setUser("CSe22A_37");
        dataSource.setPassword("CSe22A_37");
        dataSource.setTrustServerCertificate(true);
        //dataSource.setPortNumber(1433);
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }


    public static void main(String[] args) throws SQLException {


        }
    }

