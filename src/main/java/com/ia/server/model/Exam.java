package com.ia.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    @Column
    private String studentId;

    @Column
    private String examName;

    @Column
    private Date examDate;

    @Column
    private String module;

    @Column
    private Double band;

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
