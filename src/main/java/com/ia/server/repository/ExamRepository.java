package com.ia.server.repository;

import com.ia.server.DTO.ExamQuestionDto;
import com.ia.server.model.Exam;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

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
    List<ExamQuestionDto> getExamData(@Param("studentId") String studentId, @Param("modules") List<String> modules);

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO exam (id, student_id, exam_name, exam_date, module, band, created_date, updated_date)
            VALUES (:#{#dto.examId}, :#{#dto.studentId}, :#{#dto.examName}, :#{#dto.examDate},
                    :#{#dto.module}, :#{#dto.band}, :#{#dto.examCreatedDate}, :#{#dto.examUpdatedDate})
                    AS new
            ON DUPLICATE KEY UPDATE
                student_id = new.student_id,
                exam_name = new.exam_name,
                exam_date = new.exam_date,
                module = new.module,
                band = new.band,
                updated_date = new.updated_date
            """, nativeQuery = true)
    void insertOrUpdateExam(ExamQuestionDto dto);
}
