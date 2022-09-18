package com.kakaobank.search.service;

import com.kakaobank.search.dto.SearchDto;
import com.kakaobank.search.dto.response.SearchBlogDocumentsResponseDto;
import com.kakaobank.search.dto.response.SearchBlogResponseDto;
import com.kakaobank.search.dto.response.naver.SearchBlogNaverResponseDto;
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
import java.util.Objects;

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

            final String BLOG_PATH = "blog";
            String searchBlogURL = url + BLOG_PATH + "?query=" + searchDto.getQuery();
            if (Objects.nonNull(searchDto.getSort())) {
                searchBlogURL = searchBlogURL + "&sort=" + searchDto.getSort();
            }
            if (Objects.nonNull(searchDto.getPage())) {
                searchBlogURL = searchBlogURL + "&page=" + searchDto.getPage();
            }
            if (Objects.nonNull(searchDto.getSize())) {
                searchBlogURL = searchBlogURL + "&size=" + searchDto.getSize();
            }

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.add("Authorization", "KakaoAK " + API_KEY);

            HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

            ResponseEntity<SearchBlogResponseDto> searchBlogResponseDtoResponseEntity =
                    restTemplate.exchange(searchBlogURL, HttpMethod.GET, requestEntity, SearchBlogResponseDto.class);

            SearchBlogResponseDto searchBlogResponseDtoBody = searchBlogResponseDtoResponseEntity.getBody();

            Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());

            Page<SearchBlogDocumentsResponseDto> response =
                    new PageImpl<>(searchBlogResponseDtoBody.getDocuments(),
                            pageable, searchBlogResponseDtoBody.getMeta().getTotalCount());

            searchPopularService.searchHit(searchDto.getQuery());

            return response;
        } catch (Exception e) {
            SearchBlogNaverResponseDto searchBlogNaverResponseDto = searchBlogNaverService.searchBlog(searchDto);

            Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());

            List<SearchBlogDocumentsResponseDto> searchBlogDocumentsResponseDto =
                    searchBlogNaverResponseDto.getItems()
                            .stream()
                            .map(SearchBlogDocumentsResponseDto::of)
                            .collect(toList());

            Page<SearchBlogDocumentsResponseDto> response =
                    new PageImpl<>(searchBlogDocumentsResponseDto,
                            pageable, searchBlogNaverResponseDto.getTotal());
            return response;
        }
    }
}
