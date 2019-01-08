package pl.ormlite.example03;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "books")
public class Book {

    Book(){

    }


    @DatabaseField (generatedId = true)
    private int id;

    @DatabaseField(columnName = "TITLE") // tytuł kolumny opcjonalny inaczej orm posłuzy sie nazwa pola
    private String title;

    @DatabaseField(columnName = "PRICE")
    private double price;


}
