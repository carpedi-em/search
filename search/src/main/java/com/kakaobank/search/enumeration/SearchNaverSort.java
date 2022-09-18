package com.kakaobank.search.enumeration;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SearchNaverSort {
    sim(SearchSort.accuracy), date(SearchSort.recency);

    private SearchSort searchSort;

    SearchNaverSort(SearchSort searchsort) {
        this.searchSort = searchsort;
    }

    public static String findSort(SearchSort searchSort) {
        return Arrays.stream(values())
                .filter(v -> v.equals(searchSort))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("not find sort: " + searchSort))
                .name();
    }
}
