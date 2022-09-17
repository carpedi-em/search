package com.kakaobank.search.dto.response;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchBlogResponseDto {
    private SearchBlogMetaResponseDto meta;
    private List<SearchBlogDocumentsResponseDto> documents;
}
