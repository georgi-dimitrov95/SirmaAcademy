package com.football.api.repository.jpa;

import com.football.api.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordJpaRepository extends JpaRepository<Record, Long> {
}
