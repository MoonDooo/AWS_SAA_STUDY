package com.aws.saa_c03.dto.chatgpt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GPTMessage {
    private String role;
    private String content;
}
