package com.aws.saa_c03.service;

import com.aws.saa_c03.dto.CustomException;
import com.aws.saa_c03.dto.GptQuestionDto;
import com.aws.saa_c03.dto.StatusCode;
import com.aws.saa_c03.dto.chatgpt.*;
import com.aws.saa_c03.dto.chatgpt.response.ResponseGPTDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Service
public class GPTServiceImpl implements GPTService{

    @Value("${api.chat_gpt.url.base_url}")
    private String chatGPTBaseUrl;
    @Value("${api.chat_gpt.url.chat_completions}")
    private String chatCompletionUrl;

    @Value("${api.chat_gpt.secure_key}")
    private String secureKey;

    @Value("${api.chat_gpt.aws.command}")
    private String command;

    @Value("${api.chat_gpt.aws.function}")
    private String function;

    private final RestTemplate restTemplate;


    @Override
    public GptQuestionDto translate(GptQuestionDto search){
        String uri = chatGPTBaseUrl + chatCompletionUrl;
        HttpHeaders httpHeaders = getHttpHeaders();
        ChatGPTRequestDto gpt = getHttpBody(search);
        HttpEntity<?> httpEntity = new HttpEntity<>(gpt, httpHeaders);
        return getKeywords(uri,httpEntity);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + secureKey);
        return httpHeaders;
    }
    private ChatGPTRequestDto getHttpBody(GptQuestionDto search) {
        List<GPTMessage> messages = getGptMessages(search, command);
        GPTCommendDto gptCommendDto = getGptCommendDto();
        List<GptFunction> gptFunctions = getGptFunctions(gptCommendDto);
        return ChatGPTRequestDto.builder()
                .messages(messages)
                .functions(gptFunctions)
                .function_call("desired_search_keywords")
                .build();
    }
    private static List<GPTMessage> getGptMessages(GptQuestionDto search, String systemMessage) {
        List<GPTMessage> messages = new ArrayList<>();
        messages.add(new GPTMessage("system", systemMessage));
        try {
            messages.add(new GPTMessage("user", new ObjectMapper().writeValueAsString(search)));
        } catch (JsonProcessingException e) {
            throw new CustomException(StatusCode.Internal_Server_Error);
        }
        return messages;
    }
    private static GPTCommendDto getGptCommendDto() {
        GptItem gptItem = new GptItem("string");
        GptKeyword gptKeyword = new GptKeyword("array", gptItem);
        return new GPTCommendDto(gptKeyword);
    }
    private List<GptFunction> getGptFunctions(GPTCommendDto gptCommendDto) {
        List<GptFunction> gptFunctions = new ArrayList<>();
        gptFunctions.add(new GptFunction("desired_search_keywords", function, new GPTParameter("object", gptCommendDto)));
        return gptFunctions;
    }

    private GptQuestionDto getKeywords(String uri, HttpEntity<?> httpEntity) {
        ResponseGPTDto resultDto = restTemplate.postForObject(uri, httpEntity, ResponseGPTDto.class);

        List<String> keywords = new ArrayList<>();
        assert resultDto != null;
        AtomicReference<GptQuestionDto> gptQuestionDto = new AtomicReference<>();
        resultDto.getChoices().forEach(
                choice->
                {
                    try {
                        gptQuestionDto.set(new ObjectMapper().readValue(choice.getMessage().getFunction_call().getArguments(), GptQuestionDto.class));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return gptQuestionDto.get();
    }
}
