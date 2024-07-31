package controllers;

import models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.PersonService;

@RestController
@RequestMapping("/people")
public class PersonController {
    @Autowired
    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/register")
    public String register(@RequestBody Person person) {
//        TODO validate if the needed data is provided
        try {
            personService.registerPerson(person);
        } catch (Exception e) {
            System.out.println("Ran into an error while registering person.");
            throw new RuntimeException(e);
        }
        return "Success";
    }
}
