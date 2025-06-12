package com.ia.server.repository;

import com.ia.server.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    @Override
    Optional<Student> findById(Long id);
}
