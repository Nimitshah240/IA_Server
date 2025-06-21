package com.ia.server.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
//@AllArgsConstructor
public class ExamQuestionDto {

    private Long examId;
    private String examName;
    private OffsetDateTime examDate;
    private String module;
    private Double band;
    private LocalDateTime examCreatedDate;
    private LocalDateTime examUpdatedDate;

    // Question table fields
    private Long id;
    private String studentId;
    private String questionType;
    private int total;
    private int correct;
    private int incorrect;
    private int miss;
    private int section;
    private LocalDateTime questionCreatedDate;
    private LocalDateTime questionUpdatedDate;


    public ExamQuestionDto(
            Long examId,
            String examName,
            Timestamp examDate,
            String module,
            Double band,
            Timestamp examCreatedDate,
            Timestamp examUpdatedDate,
            Long id,
            String studentId,
            String questionType,
            int total,
            int correct,
            int incorrect,
            int miss,
            int section,
            Timestamp questionCreatedDate,
            Timestamp questionUpdatedDate
    ) {
        this.examId = examId;
        this.examName = examName;
        this.examDate = examDate != null ? examDate.toInstant().atOffset(ZoneOffset.UTC) : null;
        this.module = module;
        this.band = band;
        this.examCreatedDate = examCreatedDate != null ? examCreatedDate.toLocalDateTime() : null;
        this.examUpdatedDate = examUpdatedDate != null ? examUpdatedDate.toLocalDateTime() : null;
        this.id = id;
        this.studentId = studentId;
        this.questionType = questionType;
        this.total = total;
        this.correct = correct;
        this.incorrect = incorrect;
        this.miss = miss;
        this.section = section;
        this.questionCreatedDate = questionCreatedDate != null ? questionCreatedDate.toLocalDateTime() : null;
        this.questionUpdatedDate = questionUpdatedDate != null ? questionUpdatedDate.toLocalDateTime() : null;
    }


}