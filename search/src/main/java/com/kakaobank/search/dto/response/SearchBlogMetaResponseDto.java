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

    /*public static SearchBlogMetaResponseDto of(int total, int start, int display) {
        boolean isEndPage = isEndPage(total, start, display);

        return SearchBlogMetaResponseDto.builder()
                .totalCount(total)
                .pageableCount(total)
                .isEnd(isEndPage)
                .build();
    }

    private static boolean isEndPage(int total, int start, int display) {
        int endPage = total / display;
        if((total % display) > 0) {
            endPage++;
        }

        return endPage == start;
    }*/
}
