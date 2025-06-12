package com.ia.server.service;

import com.ia.server.model.Exam;
import com.ia.server.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.classfile.Opcode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public List<Exam> getExamData(Long user_id, String module) {
        List<String> moduleList = new ArrayList<String>();
        if (module == null) {
            moduleList.add("\'Reading\'");
            moduleList.add("\'Listening\'");
        } else {
            moduleList.add("\'" + module + "\'");
        }
        return examRepository.getExamData(user_id, moduleList);
    }

}
