package com.aws.saa_c03.dto.chatgpt;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatGPTRequestDto {
    private final String model;
    private final List<GPTMessage> messages;
    private final List<GptFunction> functions;
    private final GptFunctionCall function_call;

    @Builder
    public ChatGPTRequestDto(List<GPTMessage> messages, List<GptFunction> functions, String function_call){
        this.model = "gpt-4o";
        this.messages = messages;
        this.functions = functions;
        this.function_call = new GptFunctionCall(function_call);
    }

}
