package pl.ormlite.example05;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.ormlite.example05.Book;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main05 {

    public static void main(String[] args) throws SQLException, IOException, ParseException {

        //adres do bazy danych sqlite
        String databaseUrlSqlite = "jdbc:sqlite:bazaDanych05";

        // adres do bazy danych h2
        String databaseUrlh2 = "jdbc:h2:./database";

        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrlSqlite);

        //drop table
        TableUtils.dropTable(connectionSource, Book.class, true);

        TableUtils.createTableIfNotExists(connectionSource, Book.class);

        // tworzenie obiektu i ustawianie setterami
        Book book = new Book();
        book.setTitle("włada pierścieni");
        book.setIsbn("9980989923410293949");
        book.setAddedDate(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/DD");
        String dateString = "2012/02/02";
        Date date = simpleDateFormat.parse(dateString);
        book.setDescription("opis musi zawierać conajmnije 10 znaków");
        book.setDateRelease(date);
        book.setRating("1");
        book.setBorrowed(true);
        book.setPrice(33.99);

        //dodawnie do bazy
        //  daomanager posiada połaczenie do bazy danych i nazwe klasy dla której powstał
        // Daomanager ma dwa parametry parametr klasy i parametr id
        Dao<Book, Integer> dao = DaoManager.createDao(connectionSource, Book.class);
        dao.create(book);
        System.out.println(book);

        // na dao mozna wywołac bardzo duzo metod
        dao.refresh(book); // odswierza dane w bazie

        // dao.update zmienia wartosci w bazie po zmianie wartosci pola
        book.setTitle("Quo Vadis");
        dao.update(book);
        System.out.println("book po update ma tytuł: "+book.getTitle());

        // obiekt mozna tez usunąć
        dao.delete(book); // wbazie danych go juz nie ma ale nadal istnieje w pamieci
        System.out.println("book po delete ma tytuł: "+book.getTitle());

        //wyszukiwanie w bazie
        book = dao.queryForId(book.getId()); // zwraca obiekt ???
        System.out.println("book po wyszukanie po id: "+book);


        connectionSource.close();
    }
}