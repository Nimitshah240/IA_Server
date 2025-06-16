package com.ia.server.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ExamQuestionDto {

    // Exam table fields
    private String examName;
    private Date examDate;
    private String module;
    private Double band;
    private Date examCreatedDate;
    private Date examUpdatedDate;

    // Question table fields
    private String id;
    private String studentId;
    private String examId;
    private String questionType;
    private int total;
    private int correct;
    private int incorrect;
    private int miss;
    private int section;
    private Date questionCreatedDate;
    private Date questionUpdatedDate;

}