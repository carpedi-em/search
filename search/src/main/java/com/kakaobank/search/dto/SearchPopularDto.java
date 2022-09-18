package com.kakaobank.search.dto;

import com.kakaobank.search.entity.SearchPopular;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchPopularDto {
    private String id;
    private int hit;

    public SearchPopularDto(SearchPopular searchPopular) {
        this.id = searchPopular.getId();
        this.hit = searchPopular.getHit();
    }
}
