package com.kakaobank.search.dto.response;

import com.kakaobank.search.entity.SearchPopular;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchPopularResponseDto {
    private String id;
    private int hit;

    public SearchPopularResponseDto(SearchPopular searchPopular) {
        this.id = searchPopular.getId();
        this.hit = searchPopular.getHit();
    }
}
