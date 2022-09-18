package com.kakaobank.search.controller.v2;

import com.kakaobank.search.dto.response.SearchPopularResponseDto;
import com.kakaobank.search.service.SearchPopularService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchPopularController extends SearchV2Controller {
    private final SearchPopularService searchPopularService;

    @GetMapping("/popular")
    public SearchPopularResponseDto searchPopular() {
        return SearchPopularResponseDto.of(searchPopularService.searchPopular());
    }
}
