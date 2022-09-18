package com.kakaobank.search.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class URLUtilTest {

    @Test
    void encodeUTF8() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            URLUtil.encodeUTF8(null);
        });

        assertEquals(URLUtil.encodeUTF8("카카오뱅크"), "%EC%B9%B4%EC%B9%B4%EC%98%A4%EB%B1%85%ED%81%AC");
    }

    @Test
    void urlSettingParameter() {
        String url = "https://dapi.kakao.com/v2/search/blog?query=kakaobank";
        assertEquals(URLUtil.urlSettingParameter(url , null, 2), url);
        assertEquals(URLUtil.urlSettingParameter(url , "page", 2), url + "&page=2");
    }
}