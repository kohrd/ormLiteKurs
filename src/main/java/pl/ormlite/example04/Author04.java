package pl.ormlite.example04;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Author04 {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @ForeignCollectionField
    private ForeignCollection<Book04> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<Book04> getBooks() {
        return books;
    }

    public void setBooks(ForeignCollection<Book04> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "\nid=" + id +
                "\n name='" + name + '\'' +
                "\n books=" + books +
                '}';
    }
}