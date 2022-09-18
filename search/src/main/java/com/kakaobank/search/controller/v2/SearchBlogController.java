package com.kakaobank.search.controller.v2;

import com.kakaobank.search.dto.request.SearchRequestDto;
import com.kakaobank.search.dto.response.SearchBlogDocumentsResponseDto;
import com.kakaobank.search.service.SearchBlogKaKaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SearchBlogController extends SearchV2Controller {
    private final SearchBlogKaKaoService searchBlogKaKaoService;

    @GetMapping("/blog")
    public Page<SearchBlogDocumentsResponseDto> searchBlog(@Valid SearchRequestDto searchRequestDto) {
        return searchBlogKaKaoService.searchBlog(searchRequestDto);
    }
}
