package com.aws.saa_c03.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveQuestionDto {
    private String question;
    private String translatedQuestion;
    private List<ViewDto> viewList;
}
