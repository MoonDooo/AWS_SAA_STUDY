package com.aws.saa_c03.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GptQuestionDto {
    private String question;
    private List<GptViewDto> views;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    static class GptViewDto {
        private Integer id;
        private String view;
    }
}

