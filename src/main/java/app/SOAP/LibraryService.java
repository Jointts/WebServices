package app.SOAP;

import app.Models.Author;
import app.Models.Book;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.Set;

/**
 * Created by joonas on 23.01.17.
 */

@WebService
public interface LibraryService {
    Author addAuthor(@XmlElement(required = true, name = "token") String token,
                     @XmlElement(name = "author", required = true) Author author);

    Author getAuthor(@XmlElement(required = true, name = "token") String token,
                     @XmlElement(required = true, name = "id") long id);

    List<Author> getAllAuthors(@XmlElement(required = true, name = "token") String token);

    Book addBook(@XmlElement(required = true, name = "token") String token,
                 @XmlElement(required = true, name = "book") Book book);

    Book getAllBooks(@XmlElement(required = true, name = "token") String token,
                     @XmlElement(name = "title", required = false) String title,
                     @XmlElement(name = "author") long author,
                     @XmlElement(name = "publisher") String publisher);

    Book getBook(@XmlElement(required = true, name = "token") String token,
                 @XmlElement(required = true, name = "id") Long id);

    Set<Book> getBooksWithSpecificAuthor(@XmlElement(required = true, name = "token") String token,
                                         @XmlElement(required = true, name = "author") String author);
}
