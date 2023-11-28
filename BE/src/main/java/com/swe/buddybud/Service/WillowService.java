package com.swe.buddybud.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface WillowService {

    void sendWillow(Map<String, String> fields);

    List<Map<String, String>> getSentWillowList(Integer userId);

    List<Map<String, String>> getReceivedWillowList(Integer userId);

    void acceptWillow(Integer userId);
}
