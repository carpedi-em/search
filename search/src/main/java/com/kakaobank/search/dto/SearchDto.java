package com.kakaobank.search.dto;

import com.kakaobank.search.enumeration.SearchSort;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class SearchDto {
    @NotEmpty
    private String query;
    private SearchSort sort;
    private Integer page = 1;
    private Integer size = 10;
}
