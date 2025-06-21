package com.ia.server.service;

import com.ia.server.DTO.AllStudentDTO;
import com.ia.server.model.Student;
import com.ia.server.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public Optional<Student> findOneById(String id) {
        try {
            return studentRepo.findById(id);
        } catch (Exception e) {
            System.out.println("findOneById : " + e);
            throw new RuntimeException(e);
        }
    }

    public Student saveOrUpdateStudent(HttpServletRequest request, Student student) {
        try {
            String ipAddress = request.getHeader("X-Forwarded-For");

            if (ipAddress != null && !ipAddress.isEmpty()) {
                student.setIp(ipAddress.split(",")[0]);
                RestTemplate restTemplate = restTemplateBuilder.build();
                String url = "https://ipinfo.io/" + student.getIp() + "/json/";
                Map<String, Object> mapOfIP = restTemplate.getForObject(url, Map.class);
                student.setLocation(mapOfIP.get("city") + "," + mapOfIP.get("region"));
            }

            return studentRepo.save(student);
        } catch (Exception e) {
            System.out.println("saveOrUpdateStudent : " + e);
            throw new RuntimeException(e);
        }
    }


    public ResponseEntity<?> getAllStudentData(String username, String password) {
        try {
            if (username.equals("Nimit") && password.equals("Shah")) {
                return ResponseEntity.ok(studentRepo.getAllStudentData());
            } else {
                return ResponseEntity.ok("\"Invalid User\"");
            }
        } catch (Exception e) {
            System.out.println("getAllStudentData : " + e);
            throw new RuntimeException(e);
        }

    }
}
