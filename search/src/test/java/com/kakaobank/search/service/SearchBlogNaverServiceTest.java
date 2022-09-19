package com.kakaobank.search.service;

import com.kakaobank.search.dto.request.SearchRequestDto;
import com.kakaobank.search.dto.response.naver.SearchBlogNaverItemResponseDto;
import com.kakaobank.search.dto.response.naver.SearchBlogNaverResponseDto;
import com.kakaobank.search.entity.SearchPopular;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchBlogNaverServiceTest {
    @InjectMocks
    SearchBlogNaverService searchBlogNaverService;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private SearchPopularService searchPopularService;

    @Test
    @DisplayName("Naver API 성공")
    void searchBlog_NAVER_API_성공() {
        final String QUERY = "카카오뱅크";
        SearchRequestDto searchRequestDto = new SearchRequestDto();
        searchRequestDto.setQuery(QUERY);

        SearchBlogNaverResponseDto searchBlogNaverResponseDtoBody =
                SearchBlogNaverResponseDto.builder().items(asList(SearchBlogNaverItemResponseDto.builder().bloggername("카카오뱅크 블로그").build())).build();
        ResponseEntity<SearchBlogNaverResponseDto> responseSearchBlogResponseDto = new ResponseEntity<>(searchBlogNaverResponseDtoBody, HttpStatus.OK);

        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                ArgumentMatchers.<Class<SearchBlogNaverResponseDto>>any())
        ).thenReturn(responseSearchBlogResponseDto);
        when(searchPopularService.searchHit(searchRequestDto.getQuery())).thenReturn(SearchPopular.builder().id(QUERY).build());

        SearchBlogNaverResponseDto searchBlogNaverResponseDto = searchBlogNaverService.searchBlog(searchRequestDto);

        assertEquals(searchBlogNaverResponseDto.getItems().size(), 1);
        assertEquals(searchBlogNaverResponseDto, searchBlogNaverResponseDtoBody);
        verify(searchPopularService, times(1)).searchHit(QUERY);
    }
}