package com.ia.server.service;

import com.ia.server.DTO.ExamQuestionDto;
import com.ia.server.model.Question;
import com.ia.server.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    public QuestionRepository questionRepository;

    public void deleteQuestion(Long question_id) {
        try {
            questionRepository.deleteById(question_id);
        } catch (Exception e) {
            System.out.println("deleteQuestion : " + e);
            throw new RuntimeException(e);
        }
    }

    public void deleteByExamId(Long exam_id) {
        try {
            questionRepository.deleteByExamId(exam_id);
        } catch (Exception e) {
            System.out.println("deleteByExamId : " + e);
            throw new RuntimeException(e);
        }
    }

    public void insertOrUpdateQuestion(ExamQuestionDto examQuestionDto) {
        try {
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
        } catch (Exception e) {
            System.out.println("insertOrUpdateQuestion : " + e);
            throw new RuntimeException(e);
        }

    }
}
