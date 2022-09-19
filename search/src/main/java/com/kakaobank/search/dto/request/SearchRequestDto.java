package com.kakaobank.search.dto.request;

import com.kakaobank.search.enumeration.SearchSort;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@Builder
public class SearchRequestDto {
    @NotEmpty
    private String query;
    private SearchSort sort;
    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size = 10;
}
