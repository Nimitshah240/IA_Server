package com.ia.server.student.service;

import com.ia.server.base.model.Student;
import com.ia.server.base.repository.BaseStudentRepository;
import com.ia.server.base.service.BaseStudentService;
import com.ia.server.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;


@Service
public class StudentService extends BaseStudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


    @Transactional
    public Student saveOrUpdateStudent(HttpServletRequest request, Student student) {
        try {
            String ipAddress = request.getHeader("X-Forwarded-For");

            ipAddress = (ipAddress != null && !ipAddress.isEmpty() ? ipAddress.split(",")[0] : request.getRemoteAddr());

            student.setIp(ipAddress);
            RestTemplate restTemplate = restTemplateBuilder.build();
            String url = "https://ipinfo.io/" + ipAddress + "/json/";
            Map<String, Object> mapOfIP = restTemplate.getForObject(url, Map.class);
            student.setLocation(mapOfIP.get("city") + "," + mapOfIP.get("region"));

            return studentRepository.save(student);
        } catch (Exception e) {
            System.out.println("saveOrUpdateStudent : " + e);
            throw new RuntimeException(e);
        }
    }
}

