package com.ia.server.service;

import com.ia.server.DTO.ExamQuestionDto;
import com.ia.server.model.Question;
import com.ia.server.repository.QuestionRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QuestionService {

    @Autowired
    public QuestionRepository questionRepository;

    public void deleteQuestion(Long question_id) {
        questionRepository.deleteById(question_id);
    }

    public void deleteByExamId(Long exam_id) {
        questionRepository.deleteByExamId(exam_id);
    }

    public void insertOrUpdateQuestion(ExamQuestionDto examQuestionDto) {

        Question q = questionRepository.findById(examQuestionDto.getId()).orElse(new Question());
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

        questionRepository.save(q);
    }
}
