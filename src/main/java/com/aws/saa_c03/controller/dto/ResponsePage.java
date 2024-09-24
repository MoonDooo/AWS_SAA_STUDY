package com.aws.saa_c03.controller.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class ResponsePage<T> {
    Integer page;
    Integer perPage;
    long total;
    Integer totalPage;
    List<T> data;


    public ResponsePage(Page<?> page, List<T> data) {
        this.page = page.getNumber();
        this.perPage = page.getSize();
        this.total = page.getTotalElements();
        this.totalPage = page.getTotalPages();
        this.data = data;
    }
}
