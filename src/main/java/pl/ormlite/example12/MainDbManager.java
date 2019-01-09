package pl.ormlite.example12;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import pl.ormlite.example12.Model.Author12;
import pl.ormlite.example12.Utils.DataCreator;
import pl.ormlite.example12.Utils.DbManager;

import java.sql.SQLException;

public class MainDbManager {
// klasa testowa

    public static void main(String[] args) throws SQLException {
        DbManager.initDatabase();

        Author12 author = DataCreator.author();
        Dao<Author12, Integer> daoAuthor = DaoManager.createDao(DbManager.getConnectionSource(), Author12.class);
        daoAuthor.createOrUpdate(author);
        DbManager.closeConnectionSource();

    }
}
