package repositories;

import models.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShowRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ShowRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveShowToDatabase(Show show) {
        String sql = "INSERT INTO shows (title, year, episodes) VALUSE (?, ?, ?)";
        jdbcTemplate.update(sql, show.getTitle(), show.getYear(), show.getEpisodes());
    }
}
