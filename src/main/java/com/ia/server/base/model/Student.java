package com.ia.server.base.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "student")
public class Student {

    @Id
    @Column(unique = true)
    private String id;

    @Column
    @Nonnull
    private String name;

    @Column
    @Nonnull
    private String email;

    @Column
    private Long number;

    @Column
    private String location;

    @Column
    private String ip;

    @Column(updatable = false)
    private LocalDateTime loginDate;

    @Column
    private String institutionId;

    @Column
    private Boolean subscribed;

    @Column
    private String picture;

    @Column
    private String type;

    @Column
    private String lastName;

    @Transient
    private Boolean newStudent;

    @Column
    private Boolean privacy;


    @PrePersist
    protected void onCreate() {
        this.loginDate = LocalDateTime.now();
    }

}
