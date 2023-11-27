package com.swe.buddybud.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    List<Map<String, String>> getBoardsList();

    Map<String, String> getBoard(Integer boardId);

    void insertBoard(Map<String, String> fields);

    void updateBoard(Map<String, String> fields);

    void deleteBoard(Integer boardId);
}
