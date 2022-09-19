package com.kakaobank.search.controller.v2;

import com.kakaobank.search.dto.request.SearchRequestDto;
import com.kakaobank.search.dto.response.SearchBlogDocumentsResponseDto;
import com.kakaobank.search.service.SearchBlogKaKaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchBlogControllerTest {
    @InjectMocks
    private SearchBlogController searchBlogController;
    @Mock
    private SearchBlogKaKaoService searchBlogKaKaoService;

    @Test
    @DisplayName("블로그 검색 Controller Test")
    void searchBlog() {
        SearchRequestDto searchRequestDto = SearchRequestDto.builder().query("카카오뱅크").build();
        Pageable pageable = PageRequest.of(searchRequestDto.getPage(), searchRequestDto.getSize());
        Page<SearchBlogDocumentsResponseDto> responseSearchBlog = new PageImpl<>(asList(), pageable, 1);

        when(searchBlogKaKaoService.searchBlog(searchRequestDto)).thenReturn(responseSearchBlog);

        Page<SearchBlogDocumentsResponseDto> searchBlogDocumentsResponseDto = searchBlogController.searchBlog(searchRequestDto);

        assertEquals(searchBlogDocumentsResponseDto, responseSearchBlog);
        verify(searchBlogKaKaoService).searchBlog(searchRequestDto);
    }
}