package com.ia.server.controller;

import com.ia.server.model.Student;
import com.ia.server.service.StudentRepoImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/studentApi")
public class StudentController {

    @Autowired
    private StudentRepoImpl studentRepoImpl;

    @GetMapping("/student")
    public Optional<Student> getStudent(@RequestParam Long user_id) {
        return studentRepoImpl.findOneById(user_id);
    }

    @PostMapping("/updateStudent")
    public void updateStudentInfo(HttpServletRequest request, @RequestBody Student student) {
        studentRepoImpl.saveOrUpdateUser(request, student);
    }

}
