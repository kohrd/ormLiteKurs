package pl.ormlite.example12;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import pl.ormlite.example12.Dao.AuthorDao;
import pl.ormlite.example12.Dao.BookDao;
import pl.ormlite.example12.Model.Author12;
import pl.ormlite.example12.Model.Book12;
import pl.ormlite.example12.Utils.DataCreator;
import pl.ormlite.example12.Utils.DbManager;

import java.sql.SQLException;
import java.util.List;

public class MainDbManager {
// klasa testowa

    public static void main(String[] args) throws SQLException {
        DbManager.initDatabase();

        Author12 author = DataCreator.author();

        AuthorDao authorDao = new AuthorDao(DbManager.getConnectionSource());
        authorDao.createOrUpdate(author);
        List<Author12> lista = authorDao.queryForAll(Author12.class);
        lista.forEach(e -> {
            System.out.println(e);

        });

        Book12 book = DataCreator.firstBook();
        BookDao bookDao = new BookDao(DbManager.getConnectionSource());
        bookDao.createOrUpdate(book);
        bookDao.queryWhere("TITLE", "Władca pierścieni");


        DbManager.closeConnectionSource();

    }
}
