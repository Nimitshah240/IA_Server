package com.ia.server.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataDeleteQuestionDto {
    String studentId;
    Long questionId;
    String module;
}
