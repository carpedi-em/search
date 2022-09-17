package com.kakaobank.search.api;

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
    void searchBlog() throws Exception {
        MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
        parameter.add("query", "kakaobank");
        parameter.add("sort", "recency");

        mvc.perform(MockMvcRequestBuilders
                        .get("/v2/search/blog")
                        .accept(MediaType.APPLICATION_JSON)
                        .params(parameter))
                .andExpect(status().isOk())
                .andDo(print());
    }
}