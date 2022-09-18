package com.kakaobank.search.util;

import lombok.experimental.UtilityClass;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

@UtilityClass
public class URLUtil {
    public static String encodeUTF8(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encode error",e);
        }
    }

    public static String urlSettingParameter(String url, String key , Object value) {
        if (Objects.isNull(key) || Objects.isNull(value)) {
            return url;
        }

        return String.format("%s&%s=%s", url, key, value);
    }

}
