package com.swe.buddybud.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface HomeService {
    void insertHome(Map<String, String> fields);

    List<Map<String, String>> getHomesList();

    Map<String, String> getHome(Integer homeId);

    void updateHome(Integer homeId, Map<String, String> fields);

    boolean deleteHome(Integer homeId);
}
