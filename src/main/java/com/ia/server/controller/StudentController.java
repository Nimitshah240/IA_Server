package com.ia.server.controller;

import com.ia.server.DTO.AllStudentDTO;
import com.ia.server.DTO.ExamQuestionDto;
import com.ia.server.DTO.InsertExamQuestionDTO;
import com.ia.server.model.Exam;
import com.ia.server.model.Student;
import com.ia.server.repository.QuestionRepository;
import com.ia.server.service.ExamService;
import com.ia.server.service.QuestionService;
import com.ia.server.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
        optStudent.get();
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
    public String deleteQuestion(@RequestParam String question_id) {
        questionService.deleteQuestion(question_id);
        return question_id;
    }

    @DeleteMapping("/deleteExam")
    @Transactional
    public String deleteExam(@RequestParam String exam_id) {
        examService.deleteExam(exam_id);
        questionService.deleteByExamId(exam_id);
        return exam_id;
    }

    @PostMapping("/insertExam")
    @Transactional
    public void insertExam(@RequestParam String exam_id, @RequestBody List<InsertExamQuestionDTO> examQuestionData) {
        for (InsertExamQuestionDTO e : examQuestionData) {
            System.out.println(e.getExamModule());
        }
    }

}
