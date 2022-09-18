package com.kakaobank.search.service;

import com.kakaobank.search.dto.SearchDto;
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

    public SearchBlogNaverResponseDto searchBlog(SearchDto searchDto) {
        String searchBlogURL = getUrl(searchDto);

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

    private String getUrl(SearchDto searchDto) {
        final String BLOG_PATH = "blog.json";

        String searchBlogURL = url + BLOG_PATH + "?query=" + URLUtil.encodeUTF8(searchDto.getQuery());

        URLUtil.urlSettingParameter(url, "sort", searchDto.getSort());
        URLUtil.urlSettingParameter(url, "start", searchDto.getPage());
        URLUtil.urlSettingParameter(url, "display", searchDto.getSize());

        return searchBlogURL;
    }
}
