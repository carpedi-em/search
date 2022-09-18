package com.kakaobank.search.service;

import com.kakaobank.search.dto.response.SearchPopularResponseDto;
import com.kakaobank.search.entity.SearchPopular;
import com.kakaobank.search.repository.SearchPopularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class SearchPopularService {
    private final SearchPopularRepository searchPopularRepository;

    public List<SearchPopularResponseDto> searchPopular() {
        return searchPopularRepository.findTop10ByOrderByHitDesc()
                .stream()
                .map(SearchPopularResponseDto::new)
                .collect(toList());
    }

    public void searchHit(String query) {
        SearchPopular searchPopular = searchPopularRepository.findById(query).orElse(SearchPopular.EMPTY);
        if (searchPopular.isEmpty()) {
            searchPopularRepository.save(SearchPopular.builder().id(query).build());
            return;
        }
        searchPopularRepository.save(searchPopular.updateHit());
    }
}
