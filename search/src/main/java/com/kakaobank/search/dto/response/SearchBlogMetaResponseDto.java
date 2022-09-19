package com.kakaobank.search.dto.response;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchBlogMetaResponseDto {
    private int totalCount;
    private int pageableCount;
    private Boolean isEnd;
}
