package com.ia.server.master.controller;

import com.ia.server.master.service.MasterStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    private MasterStudentService masterstudentService;

    @GetMapping("/allStudentData")
    @Transactional(readOnly = true, timeout = 3)
    public ResponseEntity<?> getAllStudentData(@RequestParam String username, @RequestParam String password) {
        return masterstudentService.getAllStudentData(username, password);
    }


}
