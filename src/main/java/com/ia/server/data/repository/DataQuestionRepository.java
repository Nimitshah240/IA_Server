package com.ia.server.data.repository;

import com.ia.server.base.dto.BaseExamQuestionDto;
import com.ia.server.base.repository.BaseQuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DataQuestionRepository extends BaseQuestionRepository {

    @Modifying
    @Transactional
    @Query(value = """
                INSERT INTO question (id, student_id, exam_id, question_type, correct, incorrect, miss, total, section, created_date, updated_date)
                VALUES (:#{#dto.id}, :#{#dto.studentId}, :#{#dto.examId}, :#{#dto.questionType},
                        :#{#dto.correct}, :#{#dto.incorrect}, :#{#dto.miss}, :#{#dto.total}, :#{#dto.section}, :#{#dto.questionCreatedDate}, :#{#dto.questionUpdatedDate})
                ON DUPLICATE KEY UPDATE
                    student_id = VALUES(student_id),
                    exam_id = VALUES(exam_id),
                    question_type = VALUES(question_type),
                    correct = VALUES(correct),
                    incorrect = VALUES(incorrect),
                    miss = VALUES(miss),
                    total = VALUES(total),
                    section = VALUES(section),
                    updated_date = VALUES(updated_date)
            """, nativeQuery = true)
    void insertOrUpdateQuestion(BaseExamQuestionDto dto);
}
