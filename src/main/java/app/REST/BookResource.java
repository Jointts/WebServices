package app.REST;

import app.Models.Book;
import app.SOAP.LibraryService;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.List;

import static app.SOAP.LibraryService.isAuthorized;
import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by joonas on 16.12.16.
 */

@Api(value = "books : Every operation associated with books")
@Path("books")
@Produces(APPLICATION_JSON)
public class BookResource {

    private LibraryService libraryService;

    // The constructor is required at all times since it needs to have Exception handling on instantiation
    public BookResource() throws ParseException {
        libraryService = new LibraryService();
    }

    @ApiOperation(value = "Get all Books")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Token is invalid"),
            @ApiResponse(code = 200, message = "OK", response = Book.class, responseContainer = "List")
    })
    @GET
    @Path("{id}")
    @Produces(APPLICATION_JSON)
    public Response getBook(@ApiParam(value = "Authorization token", required = true) @QueryParam("token") String token,
                            @ApiParam(value = "Book id filter") @PathParam("id") Long id) {
        if (isAuthorized(token)) {
            return Response.status(Response.Status.OK).entity(libraryService.getBook(token, id)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(format("Token is invalid: %s", token)).build();
    }

    @ApiOperation(value = "Get all books (optional filter)")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Token is invalid"),
            @ApiResponse(code = 200, message = "OK", response = Book.class, responseContainer = "List")
    })
    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllBooks(@ApiParam(value = "Authorization token", required = true) @QueryParam("token") String token,
                                @ApiParam(value = "Book title filter") @QueryParam("title") String title,
                                @ApiParam(value = "Book author(id) filter") @QueryParam("author") long author,
                                @ApiParam(value = "Book publisher filter") @QueryParam("publisher") String publisher) {
        if (isAuthorized(token)) {
            return Response.status(Response.Status.OK).entity(libraryService.getAllBooks(token, title, author, publisher)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(format("Token is invalid: %s", token)).build();
    }

    @ApiOperation(value = "Add a new Book")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Token is invalid"),
            @ApiResponse(code = 201, message = "CREATED", response = Book.class)
    })
    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addBook(@ApiParam(value = "Authorization token", required = true) @QueryParam("token") String token,
                            @ApiParam(value = "Book object to create", required = true) Book book) {
        if (isAuthorized(token)) {
            return Response.status(Response.Status.CREATED).entity(libraryService.addBook(token, book)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(format("Token is invalid: %s", token)).build();
    }

    @ApiOperation(value = "Filter books by specific author")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Token is invalid"),
            @ApiResponse(code = 200, message = "OK", response = Book.class, responseContainer = "List")
    })
    @GET
    @Path("/author")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response getBooksWithSpecificAuthor(@ApiParam(value = "Authorization token", required = true) @QueryParam("token") String token,
                                               @ApiParam(value = "Authors name(first and last) to filter", required = true) @QueryParam("author") String author) {
        if (isAuthorized(token)) {
            return Response.status(Response.Status.CREATED).entity(libraryService.getBooksWithSpecificAuthor(token, author)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(format("Token is invalid: %s", token)).build();
    }
}
