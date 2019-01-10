package pl.ormlite.example12.Dao;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import pl.ormlite.example12.Model.Book12;

import java.sql.SQLException;
import java.util.List;

public class BookDao extends CommonDao {
    // klasa bookDao słuzy do rpzechowywanie różnych zapytań
    public static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);


    public BookDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public List<String[]> queryRaw(String query){
        // ogólnie zapytanie select all ale tresc zapytania przekazywana jako parametr metody
        try {
            GenericRawResults<String[]> where = getDao(Book12.class).queryRaw(query);
            return where.getResults();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }return null;
    }

    public List<Book12> queryWhere(String columnName, String value) throws SQLException {

            QueryBuilder<Book12, Integer> queryBuilder = getQueryBuilder(Book12.class);
            queryBuilder.where().eq(columnName, value);
            PreparedQuery<Book12> prepare = queryBuilder.prepare();
            return getDao(Book12.class).query(prepare);

        }


    }

