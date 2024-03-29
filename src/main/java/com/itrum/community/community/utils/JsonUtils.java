package com.itrum.community.community.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itrum.community.community.dto.GithubUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: HuYi.Zhang
 * @create: 2018-04-24 17:20
 **/
public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static String serialize(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("json序列化出错：" + obj, e);
            return null;
        }
    }

    public static <T> T parse(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    public static <E> List<E> parseList(String json, Class<E> eClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    public static <K, V> Map<K, V> parseMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    public static <T> T nativeRead(String json, TypeReference<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    @AllArgsConstructor
    static class User {
        String name;
        Integer age;
    }

    public static void main(String[] args) {
//        String json="[{\"name\": \"Jack\",\"age\":\"22\"},{\"name\": \"Rum\",\"age\":\"18\"}]";
//
//        List<Map<String, String>> maps = nativeRead(json, new TypeReference<List<Map<String, String>>>() {
//        });
//        for (Map<String, String> map : maps) {
//            System.out.println("map = " + map);
//        }
//        String json="{\"name\": \"Jack\",\"age\":\"22\"}";
       String json="{\"name\": \"rum\",\"id\":\"40796600\",\"bio\":\"null\",\"login\":\"Rum001\"}";
        GithubUserDTO parse = parse(json, GithubUserDTO.class);
        System.out.println(parse);
//        Map<String, String> map = parseMap(json, String.class, String.class);
//        System.out.println(map);
//        String json ="[20,10,21,22]";
//        List<Integer> strings = parseList(json, Integer.class);
//        System.out.println(strings);
//        User user = new User("zs", 22);
//        String json = serialize(user);
//        System.out.println(json);
//        User parse = parse(json, User.class);
//        System.out.println(parse);
    }

}
