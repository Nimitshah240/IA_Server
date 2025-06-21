package com.ia.server.controller;

import com.ia.server.DTO.ExamQuestionDto;
import com.ia.server.model.Student;
import com.ia.server.service.ExamService;
import com.ia.server.service.QuestionService;
import com.ia.server.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")  // or specific domain like "http://localhost:3000"
@RestController
@RequestMapping("/studentApi")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamService examService;

    @GetMapping("/student")
    @Transactional
    public List<Student> getStudent(@RequestParam String user_id) {
        Optional<Student> optStudent = studentService.findOneById(user_id);
        return List.of(optStudent.get());
    }

    @PostMapping("/updateStudent")
    @Transactional
    public void updateStudentInfo(HttpServletRequest request, @RequestBody Student student) {
        studentService.saveOrUpdateStudent(request, student);
    }

    @GetMapping("/examData")
    @Transactional
    public List<ExamQuestionDto> getExamData(@RequestParam String user_id, @RequestParam(required = false) String module) {
        return examService.getExamData(user_id, module);
    }

    @GetMapping("/allStudentData")
    @Transactional
    public ResponseEntity<?> getAllStudentData(@RequestParam String username, @RequestParam String password) {
        return studentService.getAllStudentData(username, password);
    }

    @DeleteMapping("/deleteQuestion")
    @Transactional
    public Long deleteQuestion(@RequestParam Long questionId) {
        questionService.deleteQuestion(questionId);
        return questionId;
    }

    @DeleteMapping("/deleteExam")
    @Transactional
    public Long deleteExam(@RequestParam Long examId) {
        examService.deleteExam(examId);
        questionService.deleteByExamId(examId);
        return examId;
    }

    @PostMapping("/insertExam")
    @Modifying
    public void insertExam(@RequestBody List<ExamQuestionDto> examQuestionData) {
        try {
            Long examId = examService.insertOrUpdateExam(examQuestionData.getFirst()).getId();
            for (ExamQuestionDto dto : examQuestionData) {
                dto.setExamId(examId);
                questionService.insertOrUpdateQuestion(dto);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}
