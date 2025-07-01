package com.ia.server.data.controller;

import com.ia.server.base.dto.BaseExamQuestionDto;
import com.ia.server.data.dto.DataDeleteExamDto;
import com.ia.server.data.dto.DataDeleteQuestionDto;
import com.ia.server.data.service.DataExamService;
import com.ia.server.data.service.DataQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    @Cacheable(value = {"data", "dashboard"}, key = "#user_id + '_' + #module")
    public List<BaseExamQuestionDto> getExamData(@RequestParam String user_id, @RequestParam(required = false) String module) {
        return dataExamService.getExamData(user_id, module);
    }

    @DeleteMapping("/deleteQuestion")
    @Transactional()
    @CacheEvict(value = {"data", "dashboard"}, key = "#dataDeleteQuestionDto.studentId + '_' + #dataDeleteQuestionDto.module")
    public Long deleteQuestion(@RequestBody DataDeleteQuestionDto dataDeleteQuestionDto) {
        dataQuestionService.deleteById(dataDeleteQuestionDto.getQuestionId());
        return dataDeleteQuestionDto.getQuestionId();
    }

    @DeleteMapping("/deleteExam")
    @Transactional()
    @CacheEvict(value = {"data", "dashboard"}, key = "#dataDeleteExamDto.studentId + '_' + #dataDeleteExamDto.module")
    public Long deleteExam(@RequestBody DataDeleteExamDto dataDeleteExamDto) {
        dataExamService.deleteById(dataDeleteExamDto.getExamId());
        dataQuestionService.deleteByExamId(dataDeleteExamDto.getExamId());
        return dataDeleteExamDto.getExamId();
    }

    @PostMapping
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @CacheEvict(value = {"data", "dashboard"}, key = "#examQuestionData[0].studentId + '_' + #examQuestionData[0].module")
    public List<BaseExamQuestionDto> insertExam(@RequestBody List<BaseExamQuestionDto> examQuestionData) {
        try {
            Long examId = dataExamService.insertOrUpdateExam(examQuestionData.getFirst()).getId();
            for (BaseExamQuestionDto dto : examQuestionData) {
                dto.setExamId(examId);
                dataQuestionService.insertOrUpdateQuestion(dto);
            }
            return examQuestionData;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    @Transactional()
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
