package com.football.api.repository.jpa;

import com.football.api.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordJpaRepository extends JpaRepository<Record, Long> {
    List<Record> findByPlayerId(Long id);
    List<Record> findByMatchId(Long id);
}
