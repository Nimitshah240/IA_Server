package com.ia.server.base.service;

import com.ia.server.base.dto.BaseExamQuestionDto;
import com.ia.server.base.model.Exam;
import com.ia.server.base.repository.BaseExamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseExamService {

    @Autowired
    private BaseExamRepository baseExamRepository;

    public List<BaseExamQuestionDto> getExamData(String studentId, String module) {
        try {
            List<String> moduleList = new ArrayList<String>();
            if (module == null) {
                moduleList.add("Reading");
                moduleList.add("Listening");
            } else {
                moduleList.add(module);
            }
            return baseExamRepository.getExamData(studentId, moduleList);
        } catch (Exception e) {
            System.out.println("getExamData : " + e);
            throw new RuntimeException(e);
        }

    }

    public void deleteById(Long exam_id) {
        try {
            baseExamRepository.deleteById(exam_id);
        } catch (Exception e) {
            System.out.println("deleteExam : " + e);
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public Exam insertOrUpdateExam(BaseExamQuestionDto examQuestionDto) {
        try {
            Exam e = new Exam();
            e.setId(examQuestionDto.getExamId());
            e.setExamDate(examQuestionDto.getExamDate());
            e.setExamName(examQuestionDto.getExamName());
            e.setBand(examQuestionDto.getBand());
            e.setModule(examQuestionDto.getModule());
            e.setCreatedDate(examQuestionDto.getExamCreatedDate());
            e.setUpdatedDate(examQuestionDto.getExamUpdatedDate());
            e.setStudentId(examQuestionDto.getStudentId());
            return baseExamRepository.save(e);
        } catch (Exception e) {
            System.out.println("insertOrUpdateExam : " + e);
            throw new RuntimeException(e);
        }
    }

}
