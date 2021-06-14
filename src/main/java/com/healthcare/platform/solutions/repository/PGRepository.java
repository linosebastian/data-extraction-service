package com.healthcare.platform.solutions.repository;

import com.healthcare.platform.solutions.model.PGEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PGRepository extends JpaRepository<PGEntity,String> {
    List<PGEntity> findAll();
}
