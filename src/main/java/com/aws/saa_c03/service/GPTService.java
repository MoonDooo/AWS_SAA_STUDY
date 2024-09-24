package com.aws.saa_c03.service;

import com.aws.saa_c03.dto.GptQuestionDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GPTService {

    GptQuestionDto translate(GptQuestionDto search);
}
