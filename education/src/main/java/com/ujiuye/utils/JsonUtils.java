package com.ujiuye.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;

public class JsonUtils {

    static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=utf-8");
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
