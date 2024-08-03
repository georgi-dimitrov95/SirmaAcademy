package com.example.restdbapp.repositories;


import com.example.restdbapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void savePersonInDatabase(Person person) {
        String sql = "INSERT INTO people (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, person.getName(), person.getAge());
    }

    public void deletePersonFromDatabase(String id) {
        String sql = "DELETE FROM people WHERE id=?";
        jdbcTemplate.update(sql, Integer.parseInt(id));
    }
}
