package pl.ormlite.example12.Dao;

import com.j256.ormlite.support.ConnectionSource;

public class AuthorDao extends CommonDao {
    public AuthorDao(ConnectionSource connectionSource) {
        // przy tworzeniu dao wymagane będzie podanie connnectionSource
        super(connectionSource);
    }
}
