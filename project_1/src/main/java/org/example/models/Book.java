package org.example.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Book {

    private int id;

    @NotNull(message = "Name of the book should not be empty")
    private String name;

    @NotNull(message = "Author field should not be empty")
    private String author;

    @Min(value = 0, message = "Year should be greater than 0")
    private int year;

    public Book() {
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
