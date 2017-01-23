package app;


import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

import javax.ws.rs.core.Application;

/**
 * Created by joonas on 16.12.16.
 */

@SwaggerDefinition(
        info = @Info(
                description = "Book/Author API for testing purposes",
                version = "V1.2.3",
                title = "Library",
                contact = @Contact(name = "Joonas Lume", email = "joonaslume@gmail.com")
        ),
        basePath = "/WebService/REST"
)
@javax.ws.rs.ApplicationPath("REST")
public class ApplicationLauncher extends Application {

}
