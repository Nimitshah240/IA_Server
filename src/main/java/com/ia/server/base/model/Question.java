package com.ia.server.base.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private Long id;

    @Column
    private String studentId;

    @Column
    private Long examId;

    @Column
    private String questionType;

    @Column
    private Integer total;

    @Column
    private Integer correct;

    @Column
    private Integer incorrect;

    @Column
    private Integer miss;

    @Column
    private Integer section;

    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
}
