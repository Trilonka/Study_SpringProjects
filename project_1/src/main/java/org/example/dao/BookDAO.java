package org.example.dao;

import org.example.models.Book;
import org.example.util.BookRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> show() {
        return jdbcTemplate.query("select * from book", new BookRowMapper());
    }

    public List<Book> showByPersonId(int id) {
        return jdbcTemplate.query("select * from book where person_id=?", new BookRowMapper(), id);
    }

    public Optional<Book> findByTitleAndAuthorAndYear(String title, String author, int year) {
        return jdbcTemplate.query("select * from book where title=? and author=? and year=?", new BookRowMapper(),
                title,
                author,
                year).stream().findAny();
    }

    public Book getById(int id) {
        return jdbcTemplate.query("select * from book where book_id=?", new BookRowMapper(), id)
                .stream().findAny().orElse(null);
    }

    public void deleteById(int id) {
        jdbcTemplate.update("delete from book where book_id=?", id);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book(title, author, year) values(?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("update book set title=?, author=?, year=? where book_id=?",
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                id);
    }

    public void updatePerson_id(int book_id, int person_id) {
        Integer new_person_id = person_id == 0 ? null : person_id;
        jdbcTemplate.update("update book set person_id=? where book_id=?", new_person_id, book_id);
    }
}
