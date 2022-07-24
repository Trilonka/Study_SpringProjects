package org.example.dao;

import org.example.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBookById(int id) {
        return jdbcTemplate.query("select * from book where id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findAny().orElse(null);
    }

    public void deleteBookById(int id) {
        jdbcTemplate.update("delete from book where id=?", id);
    }

    public void saveBook(Book book) {
        jdbcTemplate.update("insert into book(name, author, year) values(?, ?, ?)",
                book.getName(),
                book.getAuthor(),
                book.getYear());
    }

    public void updateBook(int id, Book book) {
        jdbcTemplate.update("update book set name=?, author=?, year=? where id=?",
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                id);
    }
}
