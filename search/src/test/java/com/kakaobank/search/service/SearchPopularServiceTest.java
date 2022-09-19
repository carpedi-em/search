package com.kakaobank.search.service;

import com.kakaobank.search.dto.SearchPopularDto;
import com.kakaobank.search.entity.SearchPopular;
import com.kakaobank.search.repository.SearchPopularRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchPopularServiceTest {
    @InjectMocks
    private SearchPopularService searchPopularService;
    @Mock
    private SearchPopularRepository searchPopularRepository;

    @Test
    @DisplayName("인기 검색어 조회")
    void searchPopular() {
        List<SearchPopular> searchPopularList = getSearchPopularList();
        when(searchPopularRepository.findTop10ByOrderByHitDesc()).thenReturn(searchPopularList);

        List<SearchPopularDto> searchPopularDtoList = searchPopularService.searchPopular();

        assertEquals(searchPopularDtoList.size(), 2);
        assertEquals(searchPopularDtoList.get(0).getId(), "카카오");
        assertEquals(searchPopularDtoList.get(0).getHit(), 20);
        assertEquals(searchPopularDtoList.get(1).getId(), "카카오뱅크");
        assertEquals(searchPopularDtoList.get(1).getHit(), 2);
    }

    @Test
    @DisplayName("처음 검색하는 단어")
    void searchHit() {
        final String QUERY = "카카오뱅크";

        when(searchPopularRepository.findById(QUERY)).thenReturn(Optional.of(SearchPopular.EMPTY));
        when(searchPopularRepository.save(any())).thenReturn(SearchPopular.builder().id(QUERY).hit(1).build());

        SearchPopular searchPopular = searchPopularService.searchHit(QUERY);

        assertEquals(searchPopular.getId(), QUERY);
        assertEquals(searchPopular.getHit(), 1);
    }

    @Test
    @DisplayName("사용중인 검색하는 단어")
    void searchHit_사용중() {
        final String QUERY = "카카오뱅크";

        when(searchPopularRepository.findById(QUERY)).thenReturn(Optional.of(SearchPopular.builder().id(QUERY).hit(1).build()));
        when(searchPopularRepository.save(any())).thenReturn(SearchPopular.builder().id(QUERY).hit(2).build());

        SearchPopular searchPopular = searchPopularService.searchHit(QUERY);

        assertEquals(searchPopular.getId(), QUERY);
        assertEquals(searchPopular.getHit(), 2);
    }

    private List<SearchPopular> getSearchPopularList() {
        List<SearchPopular> searchPopularList = asList(
                SearchPopular.builder().id("카카오").hit(20).build(),
                SearchPopular.builder().id("카카오뱅크").hit(2).build());
        return searchPopularList;
    }
}