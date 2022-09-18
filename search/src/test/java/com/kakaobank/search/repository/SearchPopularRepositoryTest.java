package com.kakaobank.search.repository;

import com.kakaobank.search.entity.SearchPopular;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class SearchPopularRepositoryTest {
    @Autowired
    private SearchPopularRepository searchPopularRepository;

    @Test
    void findTop10ByOrderByHitDesc() {
        searchPopularRepository.saveAll(getEntity());
        List<SearchPopular> SearchPopularList = searchPopularRepository.findTop10ByOrderByHitDesc();
        assertEquals(SearchPopularList.size(), 10);
        assertEquals(SearchPopularList.get(0).getId(), "여름");
        assertEquals(SearchPopularList.get(2).getId(), "개발자");
        assertEquals(SearchPopularList.get(5).getId(), "RED");
        assertEquals(SearchPopularList.get(9).getId(), "카카오뱅크");
    }

    private List<SearchPopular> getEntity() {
        List<SearchPopular> searchPopularList = asList(
                SearchPopular.builder().id("카카오뱅크").hit(2).build(),
                SearchPopular.builder().id("카카오").hit(20).build(),
                SearchPopular.builder().id("뱅크").hit(5).build(),
                SearchPopular.builder().id("RED").hit(16).build(),
                SearchPopular.builder().id("개발").hit(8).build(),
                SearchPopular.builder().id("개발자").hit(45).build(),
                SearchPopular.builder().id("테스트").hit(33).build(),
                SearchPopular.builder().id("GIT").hit(1).build(),
                SearchPopular.builder().id("여름").hit(92).build(),
                SearchPopular.builder().id("겨울").hit(77).build(),
                SearchPopular.builder().id("봄").hit(13).build()
        );
        return searchPopularList;
    }
}