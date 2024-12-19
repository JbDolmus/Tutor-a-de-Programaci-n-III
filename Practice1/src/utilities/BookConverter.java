package utilities;

import domain.Book;
import javafx.util.StringConverter;

public class BookConverter extends StringConverter<Book> {

    @Override
    public String toString(Book book) {
        return book == null ? null : book.getTitle();
    }

    @Override
    public Book fromString(String bookString) {
        return null;
    }

}
