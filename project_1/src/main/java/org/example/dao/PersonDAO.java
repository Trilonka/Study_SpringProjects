package org.example.dao;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonById(int id) {
        return jdbcTemplate.query("select * from person where id=?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findAny().orElse(null);
    }

    public void deletePersonById(int id) {
        jdbcTemplate.update("delete from person where id=?", id);
    }

    public void savePerson(Person person) {
        jdbcTemplate.update("insert into person(fullName, age) values(?, ?)",
                person.getFullName(),
                person.getAge());
    }

    public void updatePerson(int id, Person person) {
        jdbcTemplate.update("update person set fullName=?, age=? where id=?",
                person.getFullName(),
                person.getAge(),
                id);
    }
}
