package pl.ormlite.example12.Model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Author12 implements BaseModel{

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;

    @ForeignCollectionField
    private ForeignCollection<Book12> books;

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

    public ForeignCollection<Book12> getBooks() {
        return books;
    }

    public void setBooks(ForeignCollection<Book12> books) {
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


