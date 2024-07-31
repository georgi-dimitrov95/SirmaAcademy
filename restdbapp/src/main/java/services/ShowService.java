package services;

import models.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ShowRepository;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public void registerShow(Show show) {
//        TODO data validation
        showRepository.saveShowToDatabase(show);
    }
}
