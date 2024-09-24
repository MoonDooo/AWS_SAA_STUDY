package com.aws.saa_c03.controller.dto;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class ResponseViewDto {
    private int id;
    private String english;
    private String korean;
    private boolean isCollect;
}
