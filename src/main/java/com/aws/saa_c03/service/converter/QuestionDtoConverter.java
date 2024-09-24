package com.aws.saa_c03.service.converter;

import com.aws.saa_c03.controller.dto.QuestionDto;
import com.aws.saa_c03.controller.dto.ResponseViewDto;
import com.aws.saa_c03.domain.Question;
import com.aws.saa_c03.dto.ViewDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionDtoConverter implements Converter<Question, QuestionDto> {
    @Override
    public QuestionDto convert(Question source) {
        List<ResponseViewDto> views = source.getView().stream()
                .map(v->new ResponseViewDto(v.getId(), v.getEnglish(), v.getKorean(), v.isCollect())).toList();
        return QuestionDto.builder()
                .id(source.getId())
                .english(source.getEnglish())
                .korean(source.getKorean())
                .views(views)
                .build();
    }
}
