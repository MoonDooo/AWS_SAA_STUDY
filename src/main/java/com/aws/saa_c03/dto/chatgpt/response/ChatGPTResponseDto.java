package com.aws.saa_c03.dto.chatgpt.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ChatGPTResponseDto {
    private final String id;
    private final String object;
    private final Long created;
    private final String mode;
    private final List<ChoiceDto> choices;
    private final UsageDto usageDto;
}
