package com.ia.server.repository;

import com.ia.server.DTO.ExamQuestionDto;
import com.ia.server.DTO.InsertExamQuestionDTO;
import com.ia.server.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, String> {

    @Query(value = """
            SELECT 
                e.id AS exam_id,
                e.exam_name,
                e.exam_date,
                e.module,
                e.band,
                e.created_date AS exam_created_date,
                e.updated_date AS exam_updated_date,
            
                q.id,
                q.student_id,
                q.exam_id,
                q.question_type,
                q.total,
                q.correct,
                q.incorrect,
                q.miss,
                q.section,
                q.created_date AS question_created_date,
                q.updated_date AS question_updated_date
            
            FROM exam e
            LEFT JOIN question q ON q.exam_id = exam_id
            WHERE e.user_id = :userId
            AND e.module IN (:modules)
            ORDER BY e.exam_date ASC
            """, nativeQuery = true)
    List<ExamQuestionDto> getExamData(@Param("userId") String userId, @Param("modules") List<String> modules);


    @Query(value = """
            INSERT INTO exam (id, user_id, exam_name, exam_date, module, band, created_date, updated_date)
            VALUES (:#{#dto.examId}, :#{#dto.userId}, :#{#dto.examName}, :#{#dto.examDate},
                    :#{#dto.module}, :#{#dto.band}, :#{#dto.createdDate}, :#{#dto.updatedDate})
            ON DUPLICATE KEY UPDATE
                user_id = VALUES(user_id),
                exam_name = VALUES(exam_name),
                exam_date = VALUES(exam_date),
                module = VALUES(module),
                band = VALUES(band),
                updated_date = VALUES(updated_date)
            """, nativeQuery = true)
    void insertOrUpdateExam(InsertExamQuestionDTO dto);
}
