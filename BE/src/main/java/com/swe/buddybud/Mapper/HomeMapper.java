package com.swe.buddybud.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HomeMapper {
    void insertSNS(Map<String, String> fields);

    List<Map<String, String>> getSNSsList();

    Map<String, String> getSNS(Integer snsId);

    void updateSNS(Integer snsId, Map<String, String> fields);

    int deleteSNS(Integer snsId);
}
