package com.example.restdbapp.services;

import com.example.restdbapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restdbapp.repositories.PersonRepository;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void registerPerson(Person person) {
//        TODO data validation & password encryption
        personRepository.savePersonInDatabase(person);
    }

    public void deletePerson(String id) {
        personRepository.deletePersonFromDatabase(id);
    }
}
