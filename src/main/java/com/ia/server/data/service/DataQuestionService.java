package com.ia.server.data.service;

import com.ia.server.base.dto.BaseExamQuestionDto;
import com.ia.server.base.model.Question;
import com.ia.server.base.service.BaseQuestionService;
import com.ia.server.data.repository.DataQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataQuestionService extends BaseQuestionService {

    @Autowired
    public DataQuestionRepository dataQuestionRepository;

    public void deleteByExamId(Long exam_id) {
        try {
            dataQuestionRepository.deleteByExamId(exam_id);
        } catch (Exception e) {
            System.out.println("deleteByExamId : " + e);
            throw new RuntimeException(e);
        }
    }

    public void insertOrUpdateQuestion(BaseExamQuestionDto examQuestionDto) {
        try {
            Question q = dataQuestionRepository.findById(examQuestionDto.getId()).orElse(new Question());
            q.setStudentId(examQuestionDto.getStudentId());
            q.setExamId(examQuestionDto.getExamId());
            q.setQuestionType(examQuestionDto.getQuestionType());
            q.setTotal(examQuestionDto.getTotal());
            q.setCorrect(examQuestionDto.getCorrect());
            q.setIncorrect(examQuestionDto.getIncorrect());
            q.setMiss(examQuestionDto.getMiss());
            q.setSection(examQuestionDto.getSection());
            q.setCreatedDate(examQuestionDto.getQuestionCreatedDate());
            q.setUpdatedDate(examQuestionDto.getQuestionUpdatedDate());

            dataQuestionRepository.save(q);
        } catch (Exception e) {
            System.out.println("insertOrUpdateQuestion : " + e);
            throw new RuntimeException(e);
        }

    }
}
