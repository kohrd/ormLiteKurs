package pl.ormlite.example07;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main07 {


    public static void main(String[] args) throws SQLException, IOException, ParseException {

        String databaseUrlSqlite = "jdbc:sqlite:bazaDanych06";
        String databaseUrlh2 = "jdbc:h2:./database";

        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrlSqlite);
        TableUtils.dropTable(connectionSource, Book07.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Book07.class);


        // book1
        Book07 book1 = new Book07();
        book1.setTitle("janko muzykant");
        book1.setIsbn("1111111111111");
        book1.setAddedDate(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/DD");
        String dateString = "2012/02/02";
        Date date = simpleDateFormat.parse(dateString);
        book1.setDescription("opis musi zawierać conajmnije 10 znaków janko muzykant");
        book1.setDateRelease(date);
        book1.setRating("2");
        book1.setBorrowed(true);
        book1.setPrice(33.99);

        // book2
        Book07 book2 = new Book07();
        book2.setTitle("nasza szkapa");
        book2.setIsbn("2222222222222222");
        book2.setAddedDate(new Date());
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/DD");
        String dateString2 = "2012/02/02";
        Date date2 = simpleDateFormat2.parse(dateString2);
        book2.setDescription("opis musi zawierać conajmnije 10 znaków nasza szkapa");
        book2.setDateRelease(date2);
        book2.setRating("1");
        book2.setBorrowed(true);
        book2.setPrice(99.09);

        // book3
        Book07 book3 = new Book07();
        book3.setTitle("dzieci z bulerbyn");
        book3.setIsbn("3333333333333333333");
        book3.setAddedDate(new Date());
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/DD");
        String dateString3 = "2012/02/02";
        Date date3 = simpleDateFormat3.parse(dateString3);
        book3.setDescription("opis musi zawierać conajmnije 10 znaków dzieci z bulerbyn");
        book3.setDateRelease(date2);
        book3.setRating("7");
        book3.setBorrowed(true);
        book2.setPrice(00.01);

        Dao<Book07, Integer> dao = DaoManager.createDao(connectionSource, Book07.class);
        dao.create(book1);
        dao.create(book2);
        dao.create(book3);

//        /queryBuilder na dao
        QueryBuilder<Book07, Integer> queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq("TITLE", "nasza szkapa");
        PreparedQuery<Book07> queryBuilderPrepare = queryBuilder.prepare(); // przygotowuje zapytanie ale go nie wykonuje
        List<Book07> queryBuilderResultList = dao.query(queryBuilderPrepare); // wykonuje zapytanie
        queryBuilderResultList.forEach(e ->{
            System.out.println("zapytanie tytułu z queryBuilder: "+e.getTitle());
            System.out.println("zapytanie całego obiektu zwracanego z queryBuilder: "+e);
        });


        // nieco prostsza forma queryBuilder zapytanie z powyżej napisane w jednej linii
        List<Book07> simpleUseQueryBuilder1 = dao.query(dao.queryBuilder().where().eq("TITLE", "janko muzykant").prepare());
        simpleUseQueryBuilder1.forEach(e ->{
            System.out.println("simpleUseQueryBuilder:" +e);
        });



        //zapytanie przy pomocy queryBuilder nieco bardzij złożone
        List<Book07> simpleUseQueryBuilder2 = dao.query(dao.queryBuilder().where()
                                                                        .eq("TITLE", "janko muzykant")
                                                                        .and()
                                                                        .eq("PRICE", 33.99 )
                                                                        .prepare());
        simpleUseQueryBuilder2.forEach(e ->{
            System.out.println("simpleUseQueryBuilder2: "+e);
        });


        //updateBulder update obiektu w bazie
        UpdateBuilder<Book07, Integer> updateuilder = dao.updateBuilder();
        updateuilder.updateColumnValue("DESCRIPTION", "opis musi zawierać conajmnije 10 znaków nasza szkapa");
        updateuilder.where().isNull("DESCRIPTION"); //updateBuilder gdzie wpis jest null
        int booksUpdate = updateuilder.update(); // rezultatem update jest int. jesli sie powiodło jest 1
        System.out.println(booksUpdate);










        connectionSource.close();

    }
}
