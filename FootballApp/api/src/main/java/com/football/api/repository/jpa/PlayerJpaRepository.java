package com.football.api.repository.jpa;

import com.football.api.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerJpaRepository extends JpaRepository<Player, Long> {
    @Override
    List<Player> findAll();
}
