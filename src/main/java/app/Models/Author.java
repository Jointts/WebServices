package app.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static java.lang.String.format;

/**
 * Created by joonas on 16.12.16.
 */

@ApiModel(value = "Author")
public class Author {

    @ApiModelProperty(example = "1")
    private long id;

    @ApiModelProperty(example = "Jüri", required = true)
    private String firstName;

    @ApiModelProperty(example = "Türi", required = true)
    private String lastName;

    public Author() {
    }

    public Author(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return format("ID: %d\nFirst name: %s\nLast name: %s", getId(), getFirstName(), getLastName());
    }
}
