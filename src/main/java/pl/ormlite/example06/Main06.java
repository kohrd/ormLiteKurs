package pl.ormlite.example06;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.ormlite.example06.Book06;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main06 {

    public static void main(String[] args) throws SQLException, IOException, ParseException {

        String databaseUrlSqlite = "jdbc:sqlite:bazaDanych06";
        String databaseUrlh2 = "jdbc:h2:./database";

        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrlSqlite);
        TableUtils.dropTable(connectionSource, Book06.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Book06.class);


        // book1
        Book06 book1 = new Book06();
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
        Book06 book2 = new Book06();
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
        Book06 book3 = new Book06();
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

        Dao<Book06, Integer> dao = DaoManager.createDao(connectionSource, Book06.class);
        dao.create(book1);
        dao.create(book2);
        dao.create(book3);


        // rawQueries 1
        GenericRawResults<String[]> rawResultsSelectAll = dao.queryRaw("SELECT * FROM books");
        List<String[]> result = rawResultsSelectAll.getResults(); // lista stringów
        result.forEach(e -> {
            for (String s : e) {
                System.out.println("wynik rawResultsSelectAll: " + s);

            }
        });

        // rawQueries 2

        GenericRawResults<String[]> selectWhere = dao.queryRaw("SELECT * FROM books WHERE title = 'dzieci z bulerbyn'");
        List<String[]> resultsSelectWhere = selectWhere.getResults();
        resultsSelectWhere.forEach(e -> {
            for (String s : e) {
                System.out.println("resultsSelectWhere: " + s);
            }
        });


        // rawQueries 3

        GenericRawResults<String[]> selectMinMax = dao.queryRaw("SELECT MIN(price), MAX(price) FROM books");
        List<String[]> resultsSelectMinMax = selectMinMax.getResults();
        resultsSelectMinMax.forEach(e -> {
            for (String s : e) {
                System.out.println("resultsForMinMax: " + s);
            }
        });

        // rawQueries 4

        GenericRawResults<String[]> selectWhereBorrowed = dao.queryRaw("SELECT COUNT(*) FROM books WHERE BORROWED = 1");
        List<String[]> resultsSelectWhereBorrowed = selectWhereBorrowed.getResults();
        resultsSelectWhereBorrowed.forEach(e -> {
            for (String s : e) {
                System.out.println("resultsSelectWhereBorrowed: " + s);
            }
        });



        // zapytania z query Row Value
        // to zapytanie zwraca jeden wynik
        double maxUnits = dao.queryRawValue("SELECT AVG(price) from books");
        System.out.println("queryRawValue result: "+maxUnits);


        connectionSource.close();
    }
}