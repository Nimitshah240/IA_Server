package com.ia.server.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

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

}