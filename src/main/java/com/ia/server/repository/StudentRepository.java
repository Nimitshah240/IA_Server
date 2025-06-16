package com.ia.server.repository;

import com.ia.server.DTO.AllStudentDTO;
import com.ia.server.DTO.ExamQuestionDto;
import com.ia.server.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {


    @Override
    Optional<Student> findById(String id);


    @Query(value = """
            SELECT student.id,
            student.name,
            student.email,
            student.number,
            student.location,
            student.ip,
            student.login_date,
            student.institution_id,
            student.subscribed,
            student.picture,
            student.type,
            student.last_name,
            COUNT(CASE WHEN exam.module = 'listening' THEN 1 END) AS listening_count,
            COUNT(CASE WHEN exam.module = 'reading' THEN 1 END) AS reading_count FROM student
            LEFT JOIN exam ON student.id = exam.user_id GROUP BY student.id
            """, nativeQuery = true)
    List<AllStudentDTO> getAllStudentData();
}
