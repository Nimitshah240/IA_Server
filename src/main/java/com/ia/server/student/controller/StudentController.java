package com.ia.server.student.controller;

import com.ia.server.base.model.Student;
import com.ia.server.student.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping
    @Transactional
    public ResponseEntity<?> getStudent(@RequestParam String user_id) {
        Optional<Student> optStudent = studentService.findById(user_id);
        if (optStudent.isPresent()) {
            return new ResponseEntity<>(List.of(optStudent.get()), HttpStatus.OK); // 200
        } else {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT); //204
        }
    }
    
    @PostMapping
    @Transactional
    public void updateStudentInfo(HttpServletRequest request, @RequestBody Student student) {
        studentService.saveOrUpdateStudent(request, student);
    }

}
