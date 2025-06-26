package com.ia.server.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class BaseAllStudentDto {

    private String id;

    private String name;

    private String email;

    private Long number;

    private String location;

    private String ip;

    private Date loginDate;

    private String institutionId;

    private Boolean subscribed;

    private String picture;

    private String type;

    private String lastName;

    private Long listening_count;

    private Long reading_count;

}