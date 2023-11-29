package com.swe.buddybud.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WillowMapper {
    List<Map<String, Object>> getAllChat(Integer sender_no, Integer receiver_no);

    List<Map<String, Object>> getMyWillows(Integer user_no);

    void insertChat(Map<String, String> fields);

    void addChatRoom(Map<String, String> fields);

    void acceptWillow(Map<String, String> fields);

    int deleteWillowRequest(Map<String, String> fields);

}
