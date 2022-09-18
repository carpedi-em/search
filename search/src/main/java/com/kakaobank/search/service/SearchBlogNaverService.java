package com.kakaobank.search.service;

import com.kakaobank.search.dto.SearchDto;
import com.kakaobank.search.dto.response.naver.SearchBlogNaverResponseDto;
import com.kakaobank.search.enumeration.SearchNaverSort;
import com.kakaobank.search.util.URLEncoderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchBlogNaverService {
    @Value("${api.naver.url}")
    String url;

    @Value("${api.naver.client-id}")
    String CLIENT_ID;

    @Value("${api.naver.client-secret}")
    String CLIENT_SECRET;

    private final RestTemplate restTemplate;
    private final SearchPopularService searchPopularService;

    public SearchBlogNaverResponseDto searchBlog(SearchDto searchDto) {
        final String BLOG_PATH = "blog.json";

        String searchBlogURL = url + BLOG_PATH + "?query=" + URLEncoderUtil.encodeUTF8(searchDto.getQuery());
        if(Objects.nonNull(searchDto.getSort())) {
            String sort = SearchNaverSort.findSort(searchDto.getSort());
            searchBlogURL = searchBlogURL + "&sort=" + sort;
        }
        if(Objects.nonNull(searchDto.getPage())) {
            searchBlogURL = searchBlogURL + "&start=" + searchDto.getPage();
        }
        if(Objects.nonNull(searchDto.getSize())) {
            searchBlogURL = searchBlogURL + "&display=" + searchDto.getSize();
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("X-Naver-Client-Id", CLIENT_ID);
        httpHeaders.add("X-Naver-Client-Secret", CLIENT_SECRET);

        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<SearchBlogNaverResponseDto> searchBlogResponseDtoResponseEntity =
                restTemplate.exchange(searchBlogURL, HttpMethod.GET, requestEntity, SearchBlogNaverResponseDto.class);

        SearchBlogNaverResponseDto searchBlogResponseDtoBody = searchBlogResponseDtoResponseEntity.getBody();

        searchPopularService.searchHit(searchDto.getQuery());

        return searchBlogResponseDtoBody;
    }
}
