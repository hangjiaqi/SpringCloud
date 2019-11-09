package com.laurus.order_service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * json字符串转JSonNode 对象的方法
     */
    public static JsonNode str2JsonNode(String str) throws IOException {
        return objectMapper.readTree(str);
    }
}
