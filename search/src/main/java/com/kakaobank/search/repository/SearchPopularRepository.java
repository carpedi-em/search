package com.kakaobank.search.repository;

import com.kakaobank.search.entity.SearchPopular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchPopularRepository extends JpaRepository<SearchPopular, String> {
    List<SearchPopular> findTop10ByOrderByHitDesc();
}
