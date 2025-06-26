package com.ia.server.master.controller;

import com.ia.server.master.service.MasterStudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    private MasterStudentService masterstudentService;

    @GetMapping("/allStudentData")
    @Transactional
    public ResponseEntity<?> getAllStudentData(@RequestParam String username, @RequestParam String password) {

        if (username.equals("Nimit") && password.equals("Shah")) {
            return masterstudentService.getAllStudentData(username, password);
        } else {
            return ResponseEntity.ok("\"Invalid User\"");
        }
    }


}
