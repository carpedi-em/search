package com.kakaobank.search.service;

import com.kakaobank.search.dto.request.SearchRequestDto;
import com.kakaobank.search.dto.response.SearchBlogDocumentsResponseDto;
import com.kakaobank.search.dto.response.SearchBlogResponseDto;
import com.kakaobank.search.dto.response.naver.SearchBlogNaverResponseDto;
import com.kakaobank.search.util.URLUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchBlogKaKaoService {
    @Value("${api.kakaobank.url}")
    String url;

    @Value("${api.kakaobank.api-key}")
    String API_KEY;
    private final RestTemplate restTemplate;
    private final SearchPopularService searchPopularService;
    private final SearchBlogNaverService searchBlogNaverService;

    public Page<SearchBlogDocumentsResponseDto> searchBlog(SearchRequestDto searchRequestDto) {

        try {
            String searchBlogURL = getUrl(searchRequestDto);
            HttpEntity<Void> requestEntity = getHeader();

            ResponseEntity<SearchBlogResponseDto> searchBlogResponseDtoResponseEntity =
                    restTemplate.exchange(searchBlogURL, HttpMethod.GET, requestEntity, SearchBlogResponseDto.class);
            SearchBlogResponseDto searchBlogResponseDto = searchBlogResponseDtoResponseEntity.getBody();

            Pageable pageable = PageRequest.of(searchRequestDto.getPage(), searchRequestDto.getSize());

            Page<SearchBlogDocumentsResponseDto> responseSearchBlog =
                    new PageImpl<>(searchBlogResponseDto.getDocuments(),
                            pageable, searchBlogResponseDto.getMeta().getTotalCount());

            searchPopularService.searchHit(searchRequestDto.getQuery());

            return responseSearchBlog;
        } catch (Exception e) {
            SearchBlogNaverResponseDto searchBlogNaverResponseDto = searchBlogNaverService.searchBlog(searchRequestDto);

            Pageable pageable = PageRequest.of(searchRequestDto.getPage(), searchRequestDto.getSize());

            List<SearchBlogDocumentsResponseDto> searchBlogDocumentsResponseDto = searchBlogNaverResponseDto
                    .getItems()
                    .stream()
                    .map(SearchBlogDocumentsResponseDto::of)
                    .collect(toList());

            Page<SearchBlogDocumentsResponseDto> responseSearchBlog = new PageImpl<>(searchBlogDocumentsResponseDto, pageable, searchBlogNaverResponseDto.getTotal());

            return responseSearchBlog;
        }
    }

    private HttpEntity<Void> getHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "KakaoAK " + API_KEY);

        return new HttpEntity<>(httpHeaders);
    }

    private String getUrl(SearchRequestDto searchRequestDto) {
        final String BLOG_PATH = "blog";
        String searchBlogURL = url + BLOG_PATH + "?query=" + URLUtil.encodeUTF8(searchRequestDto.getQuery());

        URLUtil.urlSettingParameter(url, "sort", searchRequestDto.getSort());
        URLUtil.urlSettingParameter(url, "page", searchRequestDto.getPage());
        URLUtil.urlSettingParameter(url, "size", searchRequestDto.getSize());
        return searchBlogURL;
    }
}
