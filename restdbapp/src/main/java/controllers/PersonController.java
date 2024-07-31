package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import services.PersonService;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
}
