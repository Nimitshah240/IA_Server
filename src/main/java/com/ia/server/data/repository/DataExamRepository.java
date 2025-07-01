package com.ia.server.data.repository;

import com.ia.server.base.dto.BaseExamQuestionDto;
import com.ia.server.base.repository.BaseExamRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DataExamRepository extends BaseExamRepository {

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
    void insertOrUpdateExam(BaseExamQuestionDto dto);
}
