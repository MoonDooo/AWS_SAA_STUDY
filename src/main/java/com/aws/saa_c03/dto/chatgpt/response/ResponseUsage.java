package com.aws.saa_c03.dto.chatgpt.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUsage {
    private Integer promptTokens;
    private Integer completionTokens;
    private Integer totalTokens;
}
