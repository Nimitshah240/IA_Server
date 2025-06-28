package com.ia.server.student.service;

import com.ia.server.base.model.Student;
import com.ia.server.base.service.BaseStudentService;
import com.ia.server.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class StudentService extends BaseStudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


    @Transactional
    public void saveOrUpdateStudent(HttpServletRequest request, Student student) {
        try {
            String ipAddress = request.getHeader("X-Forwarded-For");

//            ipAddress = (ipAddress != null && !ipAddress.isEmpty() ? ipAddress.split(",")[0] : request.getRemoteAddr());

            if (ipAddress != (null)) {
                RestTemplate restTemplate = restTemplateBuilder.build();
                String url = "https://ipinfo.io/" + ipAddress + "/json/";
                Map<String, Object> mapOfIP = (Map<String, Object>) restTemplate.getForObject(url, Map.class).putIfAbsent("city", null);
                student.setIp(ipAddress);
                student.setLocation(mapOfIP.get("city") + "," + mapOfIP.get("region"));
            }
            studentRepository.save(student);
        } catch (Exception e) {
            System.out.println("saveOrUpdateStudent : " + e);
            throw new RuntimeException(e);
        }
    }
}

