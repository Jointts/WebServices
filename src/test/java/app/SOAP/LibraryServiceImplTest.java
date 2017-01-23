package app.SOAP;

import app.Models.Author;
import org.junit.Before;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.text.SimpleDateFormat;

import static app.SOAP.LibraryServiceImpl.API_TOKEN;
import static org.junit.Assert.*;

/**
 * Created by joonas on 16.12.16.
 */
public class LibraryServiceImplTest {

    // NB: These tests can only be run when the Glassfish server is running

    private LibraryService libraryServiceImpl;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void setUp() throws Exception {
        URL url = new URL("http://localhost:8080/WebService/LibraryService?wsdl");
        QName qName = new QName("http://SOAP.app/", "LibraryService");
        Service service = Service.create(url, qName);
        libraryServiceImpl = service.getPort(LibraryService.class);
    }

    @Test
    public void addAuthor() throws Exception {
        Author author = new Author(5L, "Test", "User");
        libraryServiceImpl.addAuthor(API_TOKEN, author);
        assertNotNull(libraryServiceImpl.getAuthor(API_TOKEN, 5L));
    }

    @Test
    public void getAuthor() throws Exception {
        assertNotNull(libraryServiceImpl.getAuthor(API_TOKEN, 1L));
    }

    @Test
    public void getAllAuthors() throws Exception {
        assertTrue(libraryServiceImpl.getAllAuthors(API_TOKEN).size() > 0);
    }


}