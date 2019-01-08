package pl.ormlite.example03;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;

public class Main03 {

    public static void main(String[] args) throws SQLException, IOException {

        //adres do bazy danych sqlite
        String databaseUrlSqlite = "jdbc:sqlite:bazaDanych03";

        // adres do bazy danych h2
        String databaseUrlh2 = "jdbc:h2:./database";


        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrlSqlite);
//        Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);

        // operacje na tabeli tworzym za pomoca table utils - drop i create

        //drop table
        TableUtils.dropTable(connectionSource, Book.class, true);

        TableUtils.createTableIfNotExists(connectionSource, Book.class);
        // przezucanie tabeli do bazy dancyh
        TableUtils.createTable(connectionSource, Book.class);


        connectionSource.close();
    }
}
