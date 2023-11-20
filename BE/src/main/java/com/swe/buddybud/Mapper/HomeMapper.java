package com.swe.buddybud.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HomeMapper {
    void insertHome(Map<String, String> fields);

    List<Map<String, String>> getHomesList();

    Map<String, String> getHome(Integer homeId);

    void updateHome(Integer homeId, Map<String, String> fields);

    int deleteHome(Integer homeId);
}
