package com.aws.saa_c03.dto;

import com.aws.saa_c03.controller.dto.ResponseViewDto;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionDtoWithMemo {
    private int id;
    private String korean;
    private String english;
    private String memo;
    private List<ResponseViewDto> views;
}
