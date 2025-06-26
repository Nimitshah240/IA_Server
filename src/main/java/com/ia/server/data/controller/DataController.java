package com.ia.server.data.controller;

import com.ia.server.base.dto.BaseExamQuestionDto;
import com.ia.server.data.service.DataExamService;
import com.ia.server.data.service.DataQuestionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")  // or specific domain like "http://localhost:3000"
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataQuestionService dataQuestionService;

    @Autowired
    private DataExamService dataExamService;
    
    @GetMapping
    @Transactional
    public List<BaseExamQuestionDto> getExamData(@RequestParam String user_id, @RequestParam(required = false) String module) {
        return dataExamService.getExamData(user_id, module);
    }

    @DeleteMapping("/deleteQuestion")
    @Transactional
    public Long deleteQuestion(@RequestParam Long questionId) {
        dataQuestionService.deleteById(questionId);
        return questionId;
    }

    @DeleteMapping("/deleteExam")
    @Transactional
    public Long deleteExam(@RequestParam Long examId) {
        dataExamService.deleteById(examId);
        dataQuestionService.deleteByExamId(examId);
        return examId;
    }

    @PostMapping
    public void insertExam(@RequestBody List<BaseExamQuestionDto> examQuestionData) {
        try {
            Long examId = dataExamService.insertOrUpdateExam(examQuestionData.getFirst()).getId();
            for (BaseExamQuestionDto dto : examQuestionData) {
                dto.setExamId(examId);
                dataQuestionService.insertOrUpdateQuestion(dto);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public void updateExam(@RequestBody List<BaseExamQuestionDto> examQuestionData) {
        try {
            Long examId = dataExamService.insertOrUpdateExam(examQuestionData.getFirst()).getId();
            for (BaseExamQuestionDto dto : examQuestionData) {
                dto.setExamId(examId);
                dataQuestionService.insertOrUpdateQuestion(dto);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}
