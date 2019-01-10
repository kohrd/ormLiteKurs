package pl.ormlite.example12.Main;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.ormlite.example12.Model.Book12;
import pl.ormlite.example12.Utils.DataCreator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainQuery {


    public static void main(String[] args) throws SQLException, IOException {

        String databaseUrl = "jdbc.sqlite:database12.db";

        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
        TableUtils.createTableIfNotExists(connectionSource, Book12.class);

        TableUtils.dropTable(connectionSource, Book12.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Book12.class);

        //Pierwsza
        Book12 book1 = DataCreator.firstBook();

        //Druga
        Book12 book2 = DataCreator.secondBook();

        //trzecia
        Book12 book3 = DataCreator.thirdBook();

        Dao<Book12, Integer> dao = DaoManager.createDao(connectionSource, Book12.class);

        dao.create(book1);
        dao.create(book2);
        dao.create(book3);



        QueryBuilder<Book12, Integer> queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq("TITLE", "nasza szkapa");
        PreparedQuery<Book12> queryBuilderPrepare = queryBuilder.prepare(); // przygotowuje zapytanie ale go nie wykonuje
        List<Book12> queryBuilderResultList = dao.query(queryBuilderPrepare); // wykonuje zapytanie
        queryBuilderResultList.forEach(e ->{
            System.out.println("zapytanie tytułu z queryBuilder: "+e.getTitle());
            System.out.println("zapytanie całego obiektu zwracanego z queryBuilder: "+e);
        });



        List<Book12> simpleUseQueryBuilder1 = dao.query(dao.queryBuilder()
                .where()
                .eq("TITLE", "janko muzykant")
                .prepare());
        simpleUseQueryBuilder1.forEach(e ->{
            System.out.println("simpleUseQueryBuilder:" +e);
        });


        List<Book12> simpleUseQueryBuilder2 = dao.query(dao.queryBuilder().where()
                .eq("TITLE", "janko muzykant")
                .and()
                .eq("PRICE", 33.99 )
                .prepare());
        simpleUseQueryBuilder2.forEach(e ->{
            System.out.println("simpleUseQueryBuilder2: "+e);
        });




        UpdateBuilder<Book12, Integer> updateuilder = dao.updateBuilder();
        updateuilder.updateColumnValue("DESCRIPTION", "opis musi zawierać conajmnije 10 znaków nasza szkapa");
        updateuilder.where().isNull("DESCRIPTION"); //updateBuilder gdzie wpis jest null
        int booksUpdate = updateuilder.update(); // rezultatem update jest int. jesli sie powiodło jest 1
        System.out.println(booksUpdate);










        connectionSource.close();
    }
}
