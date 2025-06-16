package com.ia.server.service;

import com.ia.server.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    public QuestionRepository questionRepository;

    public void deleteQuestion(String question_id) {
        questionRepository.deleteById(question_id);
    }

    public void deleteByExamId(String exam_id) {
        questionRepository.deleteByExamId(exam_id);
    }
}
