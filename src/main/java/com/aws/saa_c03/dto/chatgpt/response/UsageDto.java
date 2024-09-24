package com.aws.saa_c03.dto.chatgpt.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UsageDto {
    private final int prompt_tokens;
    private final int completion_tokens;
    private final int total_tokens;
}
