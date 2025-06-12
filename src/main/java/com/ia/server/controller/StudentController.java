package com.ia.server.controller;

import com.ia.server.model.Exam;
import com.ia.server.model.Student;
import com.ia.server.service.ExamService;
import com.ia.server.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studentApi")
public class StudentController {

    @Autowired
    private StudentService studentRepoImpl;

    @Autowired
    private ExamService examService;

    @GetMapping("/student")
    public Optional<Student> getStudent(@RequestParam Long user_id) {
        return studentRepoImpl.findOneById(user_id);
    }

    @PostMapping("/updateStudent")
    public void updateStudentInfo(HttpServletRequest request, @RequestBody Student student) {
        studentRepoImpl.saveOrUpdateStudent(request, student);
    }

    @GetMapping("/examData")
    public List<Exam> getExamData(@RequestParam Long user_id, @RequestParam(required = false) String module) {
        return examService.getExamData(user_id, module);
    }

}
