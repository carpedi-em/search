package com.kakaobank.search.controller;

import com.kakaobank.search.dto.SearchDto;
import com.kakaobank.search.dto.response.SearchBlogDocumentsResponseDto;
import com.kakaobank.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v2/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/blog")
    public Page<SearchBlogDocumentsResponseDto> searchBlog(@Valid SearchDto searchDto) {
        return searchService.searchBlog(searchDto);
    }
}
