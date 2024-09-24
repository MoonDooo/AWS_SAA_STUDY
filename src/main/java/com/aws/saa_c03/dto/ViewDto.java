package com.aws.saa_c03.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ViewDto {
    private Integer id;
    private String english;
    private String korean;
    private Boolean collect;
}
