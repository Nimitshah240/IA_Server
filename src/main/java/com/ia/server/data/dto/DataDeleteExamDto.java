package com.ia.server.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataDeleteExamDto {
    String studentId;
    Long examId;
    String module;
}

