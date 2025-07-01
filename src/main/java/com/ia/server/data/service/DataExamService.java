package com.ia.server.data.service;

import com.ia.server.base.dto.BaseExamQuestionDto;
import com.ia.server.base.model.Exam;
import com.ia.server.base.service.BaseExamService;
import com.ia.server.data.repository.DataExamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataExamService extends BaseExamService {

    @Autowired
    private DataExamRepository dataExamRepository;


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
            return dataExamRepository.save(e);
        } catch (Exception e) {
            System.out.println("insertOrUpdateExam : " + e);
            throw new RuntimeException(e);
        }
    }

}
