package com.ia.server.repository;

import com.ia.server.DTO.InsertExamQuestionDTO;
import com.ia.server.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {

    void deleteByExamId(String examId);

    @Query(value = """
                INSERT INTO question (id, student_id, exam_id, question_type, correct, incorrect, miss, total, section, created_date, updated_date)
                VALUES (:#{#dto.questionId}, :#{#dto.studentId}, :#{#dto.examId}, :#{#dto.questionType},
                        :#{#dto.correct}, :#{#dto.incorrect}, :#{#dto.miss}, :#{#dto.total}, :#{#dto.section}, :#{#dto.createdDate}, :#{#dto.updatedDate})
                ON DUPLICATE KEY UPDATE
                    student_id = VALUES(student_id),
                    exam_id = VALUES(exam_id),
                    question_type = VALUES(question_type),
                    correct = VALUES(correct),
                    incorrect = VALUES(incorrect),
                    miss = VALUES(miss),
                    total = VALUES(total),
                    section = VALUES(section)
                    updated_date = VALUES(updated_date)
            """, nativeQuery = true)
    void insertOrUpdateQuestion(InsertExamQuestionDTO dto);
}
