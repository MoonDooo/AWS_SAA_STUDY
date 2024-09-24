package com.aws.saa_c03.dto.chatgpt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GPTParameter {
    private String type;
    private Object properties;
}
