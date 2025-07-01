package com.ia.server.base.service;

import com.ia.server.base.model.Student;
import com.ia.server.base.repository.BaseStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BaseStudentService {
    @Autowired
    private BaseStudentRepository baseStudentRepository;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public Optional<Student> findById(String id) {
        try {
            return baseStudentRepository.findById(id);
        } catch (Exception e) {
            System.out.println("findOneById : " + e);
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> getAllStudentData(String username, String password) {
        try {
            return ResponseEntity.ok(baseStudentRepository.getAllStudentData());
        } catch (Exception e) {
            System.out.println("getAllStudentData : " + e);
            throw new RuntimeException(e);
        }

    }
}
