package com.kakaobank.search.controller.v2;

import com.kakaobank.search.dto.SearchPopularDto;
import com.kakaobank.search.dto.response.SearchPopularResponseDto;
import com.kakaobank.search.service.SearchPopularService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchPopularControllerTest {
    @InjectMocks
    private SearchPopularController searchPopularController;
    @Mock
    private SearchPopularService searchPopularService;

    @Test
    @DisplayName("인기 검색어 Controller Test")
    void searchPopular() {
        when(searchPopularService.searchPopular()).thenReturn(getSearchPopularDtoList());

        SearchPopularResponseDto searchPopularResponseDto = searchPopularController.searchPopular();

        assertEquals(searchPopularResponseDto.getPopular().toString(), getSearchPopularDtoList().toString());
        verify(searchPopularService).searchPopular();
    }

    private List<SearchPopularDto> getSearchPopularDtoList() {
        List<SearchPopularDto> searchPopularDtoList = asList(
                SearchPopularDto.builder().id("카카오뱅크").hit(2).build(),
                SearchPopularDto.builder().id("카카오").hit(20).build(),
                SearchPopularDto.builder().id("뱅크").hit(5).build(),
                SearchPopularDto.builder().id("RED").hit(16).build(),
                SearchPopularDto.builder().id("개발").hit(8).build(),
                SearchPopularDto.builder().id("개발자").hit(45).build(),
                SearchPopularDto.builder().id("테스트").hit(33).build(),
                SearchPopularDto.builder().id("GIT").hit(1).build(),
                SearchPopularDto.builder().id("여름").hit(92).build(),
                SearchPopularDto.builder().id("겨울").hit(77).build()
        );
        return searchPopularDtoList;
    }
}