package com.kakaobank.search.service;

import com.kakaobank.search.dto.request.SearchRequestDto;
import com.kakaobank.search.dto.response.naver.SearchBlogNaverResponseDto;
import com.kakaobank.search.util.URLUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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

    public SearchBlogNaverResponseDto searchBlog(SearchRequestDto searchRequestDto) {
        String searchBlogURL = getUrl(searchRequestDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("X-Naver-Client-Id", CLIENT_ID);
        httpHeaders.add("X-Naver-Client-Secret", CLIENT_SECRET);

        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<SearchBlogNaverResponseDto> searchBlogResponseDtoResponseEntity =
                restTemplate.exchange(searchBlogURL, HttpMethod.GET, requestEntity, SearchBlogNaverResponseDto.class);
        if (isNotSuccess(searchBlogResponseDtoResponseEntity.getStatusCode())) {
            throw new RuntimeException("naver api error");
        }

        SearchBlogNaverResponseDto searchBlogResponseDtoBody = searchBlogResponseDtoResponseEntity.getBody();

        searchPopularService.searchHit(searchRequestDto.getQuery());

        return searchBlogResponseDtoBody;
    }

    private boolean isNotSuccess(HttpStatus statusCode) {
        return !statusCode.is2xxSuccessful();
    }

    private String getUrl(SearchRequestDto searchRequestDto) {
        final String BLOG_PATH = "blog.json";

        String searchBlogURL = url + BLOG_PATH + "?query=" + URLUtil.encodeUTF8(searchRequestDto.getQuery());

        URLUtil.urlSettingParameter(url, "sort", searchRequestDto.getSort());
        URLUtil.urlSettingParameter(url, "start", searchRequestDto.getPage());
        URLUtil.urlSettingParameter(url, "display", searchRequestDto.getSize());

        return searchBlogURL;
    }
}
