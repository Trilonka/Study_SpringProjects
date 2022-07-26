package org.example.dao;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> show() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getById(int id) {
        return jdbcTemplate.query("select * from person where person_id=?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findAny().orElse(null);
    }

    public Optional<Person> getByFullName(String fullName) {
        return jdbcTemplate.query("select * from person where fullname=?", new BeanPropertyRowMapper<>(Person.class), fullName)
                .stream().findAny();
    }

    public void deleteById(int id) {
        jdbcTemplate.update("delete from person where person_id=?", id);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(fullName, year) values(?, ?)",
                person.getFullName(),
                person.getYear());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("update person set fullName=?, year=? where person_id=?",
                person.getFullName(),
                person.getYear(),
                id);
    }
}
