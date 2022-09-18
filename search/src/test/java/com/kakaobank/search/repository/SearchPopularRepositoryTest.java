package com.kakaobank.search.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class SearchPopularRepositoryTest {
    @Autowired
    private SearchPopularRepository searchPopularRepository;

    @Test
    void findTop10ByOrderByHitDesc() {
    }
}