package app.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import static java.lang.String.format;

/**
 * Created by joonas on 16.12.16.
 */

@ApiModel(value = "Book")
public class Book {

    @ApiModelProperty(example = "1")
    private long id;

    @ApiModelProperty(example = "OpenGL Cookbook", required = true)
    private String title;

    @ApiModelProperty(required = true)
    private List<Author> author;

    @ApiModelProperty(required = true)
    private Date releaseDate;

    @ApiModelProperty(example = "Stanford publishing", required = true)
    private String publisher;

    public Book() {
    }

    public Book(long id, String title, List<Author> author, Date releaseDate, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return format("ID: %d\nTitle: %s\nRelease Date: %s\nPublisher: %s", getId(), getTitle(), getReleaseDate(), getPublisher());
    }
}
