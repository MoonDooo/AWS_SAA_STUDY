package com.aws.saa_c03.service.converter;

import com.aws.saa_c03.controller.dto.ResponsePage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.List;


public final class PageConverter {

    public static <T,U> ResponsePage<U> convert(Page<T> source, Converter<T, U> converter) {
        List<U> convertedContent = source.getContent().stream()
                .map(converter::convert)
                .toList();
        return new ResponsePage<>(source, convertedContent);
    }
}
