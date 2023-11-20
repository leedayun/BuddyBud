package com.swe.buddybud.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    void insertBoard(Map<String, String> fields);

    List<Map<String, String>> getBoardsList();

    Map<String, String> getBoard(Integer boardId);

    void updateBoard(Integer boardId, Map<String, String> fields);

    int deleteBoard(Integer boardId);
}
