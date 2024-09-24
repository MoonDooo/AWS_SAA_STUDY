package com.aws.saa_c03.dto.chatgpt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GptFunction {
    private final String name;
    private final String description;
    private final GPTParameter parameters;
}
