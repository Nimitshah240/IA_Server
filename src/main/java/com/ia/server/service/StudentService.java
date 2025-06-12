package com.ia.server.service;

import com.ia.server.model.Student;
import com.ia.server.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public Optional<Student> findOneById(Long id) {
        return studentRepo.findById(id);
    }

    public Student saveOrUpdateStudent(HttpServletRequest request, Student student) {
        String ipAddress = request.getHeader("X-Forwarded-For");

        if (ipAddress != null && !ipAddress.isEmpty()) {
            student.setIp(ipAddress.split(",")[0]);
            RestTemplate restTemplate = restTemplateBuilder.build();
            String url = "https://ipinfo.io/" + student.getIp() + "/json/";
            Map<String, Object> mapOfIP = restTemplate.getForObject(url, Map.class);
            student.setLocation(mapOfIP.get("city") + "," + mapOfIP.get("region"));
        }

        return studentRepo.save(student);
    }
}
