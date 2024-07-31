package controllers;

import models.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repositories.ShowRepository;

@RestController
@RequestMapping("/shows")
public class ShowController {
    @Autowired
    ShowRepository showRepository;

    public ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody Show show) {
//        TODO validate if needed data is provided
        try {
            showRepository.saveShowToDatabase(show);
        } catch (RuntimeException e) {
            System.out.println("Ran into an error while registering show.");
            throw  new RuntimeException(e);
        }
        return "Successfully registered the show";
    }
}
