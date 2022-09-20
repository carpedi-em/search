package com.kakaobank.search.dto.request;

import com.kakaobank.search.enumeration.SearchSort;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class SearchRequestDto {
    @NotEmpty
    private String query;
    private SearchSort sort;
    @Min(1)
    private Integer page = 1;
    @Min(1)
    private Integer size = 10;
}
