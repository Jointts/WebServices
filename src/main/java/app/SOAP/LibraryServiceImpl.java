package app.SOAP;

import app.Models.Author;
import app.Models.Book;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.String.format;

/**
 * Created by joonas on 16.12.16.
 */

@WebService(endpointInterface = "app.SOAP.LibraryService", serviceName = "LibraryService")
public class LibraryServiceImpl implements LibraryService {

    private List<Author> authorList = new ArrayList<>();
    private List<Book> bookList = new ArrayList<>();
    public static final String API_TOKEN = "12345";

    // Constructor for populating the endpoints with sample data
    public LibraryServiceImpl() throws ParseException {
        try {
            generateSampleData();
        } catch (Exception e) {
            System.out.println("Could not generate sample data");
        }
    }

    @Override
    public Author addAuthor(@XmlElement(required = true, name = "token") String token,
                            @XmlElement(name = "author", required = true) Author author) {
        if (isAuthorized(token)) {
            authorList.add(author);
            return author;
        }
        return null;
    }

    @Override
    public Author getAuthor(@XmlElement(required = true, name = "token") String token,
                            @XmlElement(required = true, name = "id") long id) {
        if (isAuthorized(token)) {
            for (Author author : authorList) {
                if (author.getId() == id) return author;
            }
        }
        return null;
    }

    @Override
    public List<Author> getAllAuthors(@XmlElement(required = true, name = "token") String token) {
        if (isAuthorized(token))
            return authorList;
        return null;
    }

    @Override
    public Book addBook(@XmlElement(required = true, name = "token") String token,
                        @XmlElement(required = true, name = "book") Book book) {
        if (isAuthorized(token)) {
            bookList.add(book);
        }
        return book;
    }

    @Override
    public Book getAllBooks(@XmlElement(required = true, name = "token") String token,
                            @XmlElement(name = "title", required = false) String title,
                            @XmlElement(name = "author") long author,
                            @XmlElement(name = "publisher") String publisher) {
        if (isAuthorized(token)) {
            for (Book book : bookList) {
                if (Objects.equals(book.getTitle(), title) && Objects.equals(book.getPublisher(), publisher)) {
                    for (Author authorItem : book.getAuthor()) {
                        if (authorItem.getId() == author) {
                            return book;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Book getBook(@XmlElement(required = true, name = "token") String token,
                        @XmlElement(required = true, name = "id") Long id) {
        if (isAuthorized(token)) {
            for (Book book :
                    bookList) {
                if (book.getId() == id)
                    return book;
            }
        }
        return null;
    }

    @Override
    public Set<Book> getBooksWithSpecificAuthor(@XmlElement(required = true, name = "token") String token,
                                                @XmlElement(required = true, name = "author") String author) {
        Set<Book> output = new HashSet<>();
        if (isAuthorized(token)) {
            for (Book book : bookList) {
                for (Author author1 : book.getAuthor()) {
                    if (format("%s %s", author1.getFirstName(), author1.getLastName()).equals(author)) {
                        output.add(book);
                    }
                }
            }
        }
        return output;
    }

    private void generateSampleData() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Author author1 = new Author(1L, "Mati", "Maasikas");
        Author author2 = new Author(2L, "Mari", "Mustikas");
        Author author3 = new Author(3L, "Mart", "Mardikas");
        Author author4 = new Author(4L, "Mihkel", "Mahukas");
        authorList.add(author1);
        authorList.add(author2);
        authorList.add(author3);
        authorList.add(author4);

        List<Author> bookAuthors1 = new ArrayList<>();
        bookAuthors1.add(author1);
        Date bookDate1 = sdf.parse("1818-11-11");
        Book book1 = new Book(1L, "Programmeerimine keeles Scratch", bookAuthors1, bookDate1, "Koolibri");
        bookList.add(book1);
    }

    public static boolean isAuthorized(String token) {
        return Objects.equals(token, API_TOKEN);
    }
}
