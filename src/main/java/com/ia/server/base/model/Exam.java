package com.ia.server.base.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String studentId;

    @Column
    private String examName;

    @Column
    private OffsetDateTime examDate;

    @Column
    private String module;

    @Column
    private Double band;

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
