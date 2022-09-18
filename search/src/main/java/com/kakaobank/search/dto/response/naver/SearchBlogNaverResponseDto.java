package com.kakaobank.search.dto.response.naver;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchBlogNaverResponseDto {
    private int total;
    private int start;
    private int display;
    private List<SearchBlogNaverItemResponseDto> items;
}
