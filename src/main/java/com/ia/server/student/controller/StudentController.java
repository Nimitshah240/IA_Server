package com.ia.server.student.controller;

import com.ia.server.base.model.Student;
import com.ia.server.student.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<?> getStudent(@RequestParam String user_id) {
        Optional<Student> optStudent = studentService.findById(user_id);
        if (optStudent.isPresent()) {
            return new ResponseEntity<>(List.of(optStudent.get()), HttpStatus.OK); // 200
        } else {
            return ResponseEntity.ok(new ArrayList<Student>()); //200
        }
    }

    @PostMapping
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<Student> updateStudentInfo(HttpServletRequest request, @RequestBody Student student) {
        try {
            studentService.saveOrUpdateStudent(request, student);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
