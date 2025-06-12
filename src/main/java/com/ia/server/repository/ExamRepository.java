package com.ia.server.repository;

import com.ia.server.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query(value = """
            SELECT 
                exam.id AS exam_id,
                exam.user_id,
                exam.exam_name,
                exam.exam_date,
                exam.created_date AS exam_created_date,
                exam.updated_date AS exam_updated_date,
                exam.module AS exam_module,
            
                question.id AS question_id,
                question.student_id,
                question.exam_id AS question_exam_id,
                question.question_type,
                question.total,
                question.correct,
                question.incorrect,
                question.miss,
                question.section,
                question.created_date AS question_created_date,
                question.updated_date AS question_updated_date
            
            FROM exam
            LEFT JOIN question ON question.exam_id = exam.id
            WHERE exam.user_id = :userId
              AND exam.module IN (:modules)
            ORDER BY exam.exam_date ASC
            """, nativeQuery = true)
    public List<Exam> getExamData(@Param("userId") Long userId,
                                  @Param("modules") List<String> modules);
}
