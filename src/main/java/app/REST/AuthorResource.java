package app.REST;

import app.Models.Author;
import app.SOAP.LibraryServiceImpl;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.List;

import static app.SOAP.LibraryServiceImpl.isAuthorized;
import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by joonas on 21.12.16.
 */

@Api(value = "authors : Every operation associated with authors")
@Path("authors")
public class AuthorResource {

    private LibraryServiceImpl libraryServiceImpl;

    // The constructor is required at all times since it needs to have Exception handling on instantiation
    public AuthorResource() throws ParseException {
        libraryServiceImpl = new LibraryServiceImpl();
    }

    @ApiOperation(value = "Get all Authors")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Token is invalid"),
            @ApiResponse(code = 200, message = "OK", response = Author.class, responseContainer = "List")
    })
    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllAuthors(@ApiParam(value = "Authorization token", required = true) @QueryParam("token") String token) {
        if (isAuthorized(token)) {
            // The Reason why lists are wrapped with GenericEntity is that jax-rs cannot convert pure List<Type> to json
            GenericEntity<List<Author>> authorList = new GenericEntity<List<Author>>(libraryServiceImpl.getAllAuthors(token)) {
            };
            return Response.status(Response.Status.OK).entity(authorList).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(format("Token is invalid: %s", token)).build();
    }

    @ApiOperation(value = "Get a specific Author")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Token is invalid"),
            @ApiResponse(code = 200, message = "OK", response = Author.class)
    })
    @GET
    @Path("{id}")
    @Produces(APPLICATION_JSON)
    public Response getAuthor(@ApiParam(value = "Authorization token", required = true) @QueryParam("token") String token,
                              @ApiParam(value = "The author id to filter", required = true) @PathParam("id") long id) {
        if (isAuthorized(token)) {
            return Response.status(Response.Status.OK).entity(libraryServiceImpl.getAuthor(token, id)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(format("Token is invalid: %s", token)).build();
    }

    @ApiOperation(value = "Add a new Author")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Token is invalid"),
            @ApiResponse(code = 201, message = "Created", response = Author.class)
    })
    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addAuthor(@ApiParam(value = "Authorization token", required = true) @QueryParam("token") String token,
                              @ApiParam(value = "The Author object to create", required = true) Author author) {
        if (isAuthorized(token)) {
            return Response.status(Response.Status.CREATED).entity(libraryServiceImpl.addAuthor(token, author)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity(format("Token is invalid: %s", token)).build();
    }
}
