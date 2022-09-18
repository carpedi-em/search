package com.kakaobank.search.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SearchApiTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("카카오 블로그 기본 파라미터 조회")
    void searchBlog() throws Exception {
        MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
        parameter.add("query", "kakaobank");
        parameter.add("sort", "recency");
        parameter.add("page", "2");
        parameter.add("size", "5");

        mvc.perform(MockMvcRequestBuilders
                        .get("/v2/search/blog")
                        .accept(MediaType.APPLICATION_JSON)
                        .params(parameter))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("카카오 블로그 기본 정확도순 조회")
    void search_정확도순_정렬() throws Exception {
        MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
        parameter.add("query", "kakaobank");
        parameter.add("sort", "recency");
        parameter.add("page", "3");
        parameter.add("size", "2");

        mvc.perform(MockMvcRequestBuilders
                        .get("/v2/search/blog")
                        .accept(MediaType.APPLICATION_JSON)
                        .params(parameter))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("카카오 블로그 기본 최신순 조회")
    void search_최신순_정렬() throws Exception {
        MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
        parameter.add("query", "kakaobank");
        parameter.add("sort", "recency");
        parameter.add("page", "4");
        parameter.add("size", "7");

        mvc.perform(MockMvcRequestBuilders
                        .get("/v2/search/blog")
                        .accept(MediaType.APPLICATION_JSON)
                        .params(parameter))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("카카오 블로그 정렬 값 에러")
    void search_정렬_잘못된값() throws Exception {
        MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
        parameter.add("query", "kakaobank");
        parameter.add("sort", "abc");
        parameter.add("page", "4");
        parameter.add("size", "7");

        mvc.perform(MockMvcRequestBuilders
                        .get("/v2/search/blog")
                        .accept(MediaType.APPLICATION_JSON)
                        .params(parameter))
                .andExpect(status().is5xxServerError())
                .andDo(print());
    }

    @Test
    @DisplayName("인기 검색어")
    void search_인기_검색어() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/v2/search/popular")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}