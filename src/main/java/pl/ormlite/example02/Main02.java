package pl.ormlite.example02;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;

public class Main02 {

    public static void main(String[] args) throws SQLException, IOException {

        //adres do bazy danych sqlite
        String databaseUrlSqlite = "jdbc:sqlite:bazaDanych02";

        // adres do bazy danych h2
        String databaseUrlh2 = "jdbc:h2:./database";

        // do połaczenia z bazą danych ormLite korzysta  z obiektu JdbcConnectionSource
        // w konstruktorze mozan przekazac dodatkowe parametry - username i password
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrlSqlite);
        Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);

        TableUtils.createTable(connectionSource, Account.class);

        Account account = new Account();
        account.setName("Janina");
        account.setPassword("haslo");
        accountDao.create(account);  // dodawnia do bazy danych
        // zamykanie polaczenia z baza
        connectionSource.close();





    }
}
