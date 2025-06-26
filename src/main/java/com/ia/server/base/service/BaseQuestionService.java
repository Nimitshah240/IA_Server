package com.ia.server.base.service;

import com.ia.server.base.dto.BaseExamQuestionDto;
import com.ia.server.base.model.Question;
import com.ia.server.base.repository.BaseQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseQuestionService {

    @Autowired
    public BaseQuestionRepository baseQuestionRepository;

    public void deleteById(Long question_id) {
        try {
            baseQuestionRepository.deleteById(question_id);
        } catch (Exception e) {
            System.out.println("deleteQuestion : " + e);
            throw new RuntimeException(e);
        }
    }

    public void deleteByExamId(Long exam_id) {
        try {
            baseQuestionRepository.deleteByExamId(exam_id);
        } catch (Exception e) {
            System.out.println("deleteByExamId : " + e);
            throw new RuntimeException(e);
        }
    }

    public void insertOrUpdateQuestion(BaseExamQuestionDto examQuestionDto) {
        try {
            Question q = baseQuestionRepository.findById(examQuestionDto.getId()).orElse(new Question());
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

            baseQuestionRepository.save(q);
        } catch (Exception e) {
            System.out.println("insertOrUpdateQuestion : " + e);
            throw new RuntimeException(e);
        }

    }
}
