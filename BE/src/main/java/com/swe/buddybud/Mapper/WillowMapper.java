package com.swe.buddybud.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WillowMapper {

    void sendWillow(Map<String, String> fields);

    List<Map<String, String>> getSentWillowList(Integer userId);

    List<Map<String, String>> getReceivedWillowList(Integer userId);

    void acceptWillow(Map<String, String> fields);

    void deleteWillow(Map<String, String> fields);
}
