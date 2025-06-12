package com.ia.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    private Long studentId;

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
    private Date createdDate;

    @Column
    private Date updatedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }
}
