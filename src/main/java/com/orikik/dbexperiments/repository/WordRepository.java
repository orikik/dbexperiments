package com.orikik.dbexperiments.repository;

import com.orikik.dbexperiments.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Long> {
}
