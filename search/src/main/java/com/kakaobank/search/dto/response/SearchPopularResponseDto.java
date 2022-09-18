package com.kakaobank.search.dto.response;

import com.kakaobank.search.dto.SearchPopularDto;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchPopularResponseDto {
    private List<SearchPopularDto> popular;

    public static SearchPopularResponseDto of(List<SearchPopularDto> searchPopularDto) {
        return SearchPopularResponseDto.builder().popular(searchPopularDto).build();
    }
}
