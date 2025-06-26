package com.ia.server.base.repository;

import com.ia.server.base.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseQuestionRepository extends JpaRepository<Question, Long> {
    void deleteByExamId(Long examId);
}
