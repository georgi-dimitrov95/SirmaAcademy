package services;

import models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PersonRepository;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void registerPerson(Person person) {
//        TODO data validation & password encryption
        personRepository.saveUserInDatabase(person);
    }
}
