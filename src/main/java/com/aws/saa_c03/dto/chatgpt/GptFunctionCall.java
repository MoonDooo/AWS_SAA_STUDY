package com.aws.saa_c03.dto.chatgpt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GptFunctionCall {
    private final String name;
}
