package com.ia.server.base.repository;

import com.ia.server.base.dto.BaseExamQuestionDto;
import com.ia.server.base.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseExamRepository extends JpaRepository<Exam, Long> {

    @Query(value = """
            SELECT 
                e.exam_name,
                e.exam_date,
                e.module,
                e.band,
                e.created_date AS exam_created_date,
                e.updated_date AS exam_updated_date,
            
                q.id,
                q.exam_id,
                q.student_id,
                q.question_type,
                q.total,
                q.correct,
                q.incorrect,
                q.miss,
                q.section,
                q.created_date AS question_created_date,
                q.updated_date AS question_updated_date
            
            FROM exam e
            LEFT JOIN question q ON q.exam_id = e.id
            WHERE e.student_id = :studentId
            AND e.module IN (:modules)
            ORDER BY e.exam_date ASC
            """, nativeQuery = true)
    List<BaseExamQuestionDto> getExamData(@Param("studentId") String studentId, @Param("modules") List<String> modules);
}
