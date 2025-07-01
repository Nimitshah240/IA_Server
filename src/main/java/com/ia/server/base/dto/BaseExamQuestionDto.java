package com.ia.server.base.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
//@AllArgsConstructor
public class BaseExamQuestionDto {

    private String examName;
    private OffsetDateTime examDate;
    private String module;
    private Double band;
    private LocalDateTime examCreatedDate;
    private LocalDateTime examUpdatedDate;

    // Question table fields
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;
    private String studentId;
    private String questionType;
    private int total;
    private int correct;
    private int incorrect;
    private int miss;
    private int section;
    private LocalDateTime questionCreatedDate;
    private LocalDateTime questionUpdatedDate;


    public BaseExamQuestionDto(
            String examName,
            Timestamp examDate,
            String module,
            Double band,
            Timestamp examCreatedDate,
            Timestamp examUpdatedDate,
            Long id,
            Long examId,
            String studentId,
            String questionType,
            Integer total,
            Integer correct,
            Integer incorrect,
            Integer miss,
            Integer section,
            Timestamp questionCreatedDate,
            Timestamp questionUpdatedDate
    ) {
        this.examName = examName;
        this.examDate = examDate != null ? examDate.toInstant().atOffset(ZoneOffset.UTC) : null;
        this.module = module;
        this.band = band == null ? 0d : band;
        this.examCreatedDate = examCreatedDate != null ? examCreatedDate.toLocalDateTime() : null;
        this.examUpdatedDate = examUpdatedDate != null ? examUpdatedDate.toLocalDateTime() : null;
        this.id = id;
        this.examId = examId;
        this.studentId = studentId;
        this.questionType = questionType;
        this.total = total == null ? 0 : total;
        this.correct = correct == null ? 0 : correct;
        this.incorrect = incorrect == null ? 0 : incorrect;
        this.miss = miss == null ? 0 : miss;
        this.section = section == null ? 0 : section;
        this.questionCreatedDate = questionCreatedDate != null ? questionCreatedDate.toLocalDateTime() : null;
        this.questionUpdatedDate = questionUpdatedDate != null ? questionUpdatedDate.toLocalDateTime() : null;
    }


}