package com.ia.server.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class InsertExamQuestionDTO {

    private String examId;

    private String userId;

    private String examName;

    private Date examDate;

    private String examModule;

    private Double band;

    @Column(updatable = false)
    private Date createdDate;

    private Date updatedDate;

    private String questionId;

    private String studentId;

    private String questionType;

    private Integer correct;

    private Integer incorrect;

    private Integer miss;

    private Integer total;

    private Integer section;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }
}
