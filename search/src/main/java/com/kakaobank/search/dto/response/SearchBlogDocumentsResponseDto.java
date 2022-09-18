package com.kakaobank.search.dto.response;

import com.kakaobank.search.dto.response.naver.SearchBlogNaverItemResponseDto;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchBlogDocumentsResponseDto {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private String datetime;

    public static SearchBlogDocumentsResponseDto of(SearchBlogNaverItemResponseDto searchBlogNaverItemResponseDto) {
        return SearchBlogDocumentsResponseDto.builder()
                .title(searchBlogNaverItemResponseDto.getTitle())
                .contents(searchBlogNaverItemResponseDto.getDescription())
                .url(searchBlogNaverItemResponseDto.getLink())
                .blogname(searchBlogNaverItemResponseDto.getBloggername())
                .thumbnail("")
                .datetime(searchBlogNaverItemResponseDto.getPostdate())
                .build();
    }
}
