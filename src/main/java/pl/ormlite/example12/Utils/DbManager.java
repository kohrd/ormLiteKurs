package pl.ormlite.example12.Utils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.ormlite.example12.Model.Author12;
import pl.ormlite.example12.Model.Book12;

import java.io.IOException;
import java.sql.SQLException;

public class DbManager {
    // tworzenie loggera
    public static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_SQLITE = "jdbc:sqlite:database12";
    private static ConnectionSource connectionSource;


    public static void initDatabase() {
        createConnectionSource();
        dropTable();
        createtable();
        closeConnectionSource();

    }

    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_SQLITE);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }

    }

    public static ConnectionSource getConnectionSource() {
        if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource() {
        if (connectionSource != null) {

            try {
                connectionSource.close(); // ta metoda zamyka wszytskie połacznia
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }

        }

    }

    private static void createtable() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Author12.class);
            TableUtils.createTableIfNotExists(connectionSource, Book12.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }


    private static void dropTable() {
        try {
            TableUtils.dropTable(connectionSource, Book12.class, true);
            TableUtils.dropTable(connectionSource, Author12.class, true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }

    }
}


