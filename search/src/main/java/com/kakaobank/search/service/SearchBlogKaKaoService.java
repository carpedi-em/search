package com.kakaobank.search.service;

import com.kakaobank.search.dto.SearchDto;
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

    public Page<SearchBlogDocumentsResponseDto> searchBlog(SearchDto searchDto) {

        try {
            String searchBlogURL = getUrl(searchDto);
            HttpEntity<Void> requestEntity = getHeader();

            ResponseEntity<SearchBlogResponseDto> searchBlogResponseDtoResponseEntity =
                    restTemplate.exchange(searchBlogURL, HttpMethod.GET, requestEntity, SearchBlogResponseDto.class);
            SearchBlogResponseDto searchBlogResponseDto = searchBlogResponseDtoResponseEntity.getBody();

            Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());

            Page<SearchBlogDocumentsResponseDto> responseSearchBlog =
                    new PageImpl<>(searchBlogResponseDto.getDocuments(),
                            pageable, searchBlogResponseDto.getMeta().getTotalCount());

            searchPopularService.searchHit(searchDto.getQuery());

            return responseSearchBlog;
        } catch (Exception e) {
            SearchBlogNaverResponseDto searchBlogNaverResponseDto = searchBlogNaverService.searchBlog(searchDto);

            Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());

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

    private String getUrl(SearchDto searchDto) {
        final String BLOG_PATH = "blog";
        String searchBlogURL = url + BLOG_PATH + "?query=" + searchDto.getQuery();

        URLUtil.urlSettingParameter(url, "sort", searchDto.getSort());
        URLUtil.urlSettingParameter(url, "page", searchDto.getPage());
        URLUtil.urlSettingParameter(url, "size", searchDto.getSize());
        return searchBlogURL;
    }
}
