package com.ia.server.dashboard.controller;

import com.ia.server.base.dto.BaseExamQuestionDto;
import com.ia.server.dashboard.service.DashboardExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")  // or specific domain like "http://localhost:3000"
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardExamService dashboardExamService;

    @GetMapping
    @Transactional(readOnly = true, timeout = 3)
    @Cacheable(value = "dashboard", key = "#user_id + '_' + #module")
    public List<BaseExamQuestionDto> getExamData(@RequestParam String user_id, @RequestParam(required = false) String module) {
        try {
            return dashboardExamService.getExamData(user_id, module);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
