package com.aws.saa_c03.service;

import com.aws.saa_c03.controller.dto.QuestionDto;
import com.aws.saa_c03.controller.dto.ResponsePage;
import com.aws.saa_c03.controller.dto.TranslatedQuestionQuestionDto;
import com.aws.saa_c03.dto.QuestionDtoWithMemo;
import com.aws.saa_c03.dto.SaveQuestionDto;
import com.aws.saa_c03.dto.ViewDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {
    int save(SaveQuestionDto dto);

    QuestionDtoWithMemo findById(Integer id);

    ResponsePage<QuestionDto> findAll(Pageable pageable);

    int update(SaveQuestionDto question, int id);

    Integer saveMemo(int id, String memo);
}
