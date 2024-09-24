package com.aws.saa_c03.controller.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionDto {
    private int id;
    private String korean;
    private String english;
    private List<ResponseViewDto> views;
}
