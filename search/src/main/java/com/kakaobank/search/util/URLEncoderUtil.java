package com.kakaobank.search.util;

import lombok.experimental.UtilityClass;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@UtilityClass
public class URLEncoderUtil {
    public static String encodeUTF8(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encode error",e);
        }
    }

}
