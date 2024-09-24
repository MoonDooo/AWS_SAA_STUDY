package com.aws.saa_c03.dto.chatgpt.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChoiceDto {
    private final int index;
    private final GPTMessage message;
    private final String finish_reason;
}
