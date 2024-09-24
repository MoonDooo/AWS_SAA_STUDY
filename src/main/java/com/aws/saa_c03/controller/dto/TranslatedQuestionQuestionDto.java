package com.aws.saa_c03.controller.dto;



import lombok.*;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TranslatedQuestionQuestionDto {
    private int id;
    private String question;
}
