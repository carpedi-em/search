package com.kakaobank.search.service;

import com.kakaobank.search.dto.request.SearchRequestDto;
import com.kakaobank.search.dto.response.SearchBlogDocumentsResponseDto;
import com.kakaobank.search.dto.response.SearchBlogMetaResponseDto;
import com.kakaobank.search.dto.response.SearchBlogResponseDto;
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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchBlogKaKaoServiceTest {
    @InjectMocks
    SearchBlogKaKaoService searchBlogKaKaoService;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private SearchPopularService searchPopularService;
    @Mock
    private SearchBlogNaverService searchBlogNaverService;

    @Test
    @DisplayName("KaKao API 성공")
    void searchBlog_KAKAO_API_성공() {
        final String QUERY = "카카오뱅크";
        SearchRequestDto searchRequestDto = new SearchRequestDto();
        searchRequestDto.setQuery(QUERY);

        List<SearchBlogDocumentsResponseDto> searchBlogDocumentsResponseDtoList = asList(SearchBlogDocumentsResponseDto.builder().blogname("카카오뱅크 블로그").build());
        SearchBlogResponseDto searchBlogResponseDto = SearchBlogResponseDto.builder()
                .documents(searchBlogDocumentsResponseDtoList)
                .meta(new SearchBlogMetaResponseDto()).build();
        ResponseEntity<SearchBlogResponseDto> responseSearchBlogResponseDto = new ResponseEntity<>(searchBlogResponseDto, HttpStatus.OK);

        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                ArgumentMatchers.<Class<SearchBlogResponseDto>>any())
        ).thenReturn(responseSearchBlogResponseDto);
        when(searchPopularService.searchHit(searchRequestDto.getQuery())).thenReturn(SearchPopular.builder().id(QUERY).build());

        Page<SearchBlogDocumentsResponseDto> searchBlogDocumentsResponseDto = searchBlogKaKaoService.searchBlog(searchRequestDto);

        assertEquals(searchBlogDocumentsResponseDto.getContent().size(), 1);
        assertEquals(searchBlogDocumentsResponseDto.getContent(), searchBlogDocumentsResponseDtoList);
        verify(searchPopularService, times(1)).searchHit(QUERY);
    }

    @Test
    @DisplayName("KaKao Service KaKao API 실패")
    void searchBlog_KAKAO_API_실패() {
        final String QUERY = "카카오뱅크";
        SearchRequestDto searchRequestDto = new SearchRequestDto();
        searchRequestDto.setQuery(QUERY);

        List<SearchBlogDocumentsResponseDto> searchBlogDocumentsResponseDtoList = asList(SearchBlogDocumentsResponseDto.builder().blogname("카카오뱅크 블로그").build());

        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                ArgumentMatchers.<Class<SearchBlogResponseDto>>any())
        ).thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        when(searchBlogNaverService.searchBlog(searchRequestDto))
                .thenReturn(SearchBlogNaverResponseDto.builder().total(1).items(asList(SearchBlogNaverItemResponseDto.builder().bloggername("카카오뱅크 블로그").build())).build());

        Page<SearchBlogDocumentsResponseDto> searchBlogDocumentsResponseDto = searchBlogKaKaoService.searchBlog(searchRequestDto);

        assertEquals(searchBlogDocumentsResponseDto.getContent().size(), 1);
        assertEquals(searchBlogDocumentsResponseDto.getContent().get(0).getBlogname(), searchBlogDocumentsResponseDtoList.get(0).getBlogname());
        verify(searchBlogNaverService).searchBlog(searchRequestDto);
        verify(searchPopularService, times(0)).searchHit(QUERY);
    }
}