package com.ia.server.service;

import com.ia.server.DTO.ExamQuestionDto;
import com.ia.server.model.Exam;
import com.ia.server.repository.ExamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.classfile.Opcode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public List<ExamQuestionDto> getExamData(String studentId, String module) {
        List<String> moduleList = new ArrayList<String>();
        if (module == null) {
            moduleList.add("Reading");
            moduleList.add("Listening");
        } else {
            moduleList.add(module);
        }
        for (String s : moduleList) {
            System.out.println(s);
        }
        return examRepository.getExamData(studentId, moduleList);
    }

    public Long deleteExam(Long exam_id) {
        examRepository.deleteById(exam_id);
        return exam_id;
    }

    @Transactional
    public Exam insertOrUpdateExam(ExamQuestionDto examQuestionDto) {
        Exam e = new Exam();
        e.setId(examQuestionDto.getExamId());
        e.setExamDate(examQuestionDto.getExamDate());
        e.setExamName(examQuestionDto.getExamName());
        e.setBand(examQuestionDto.getBand());
        e.setModule(examQuestionDto.getModule());
        e.setCreatedDate(examQuestionDto.getExamCreatedDate());
        e.setUpdatedDate(examQuestionDto.getExamUpdatedDate());
        e.setStudentId(examQuestionDto.getStudentId());
       return examRepository.save(e);
    }

}
