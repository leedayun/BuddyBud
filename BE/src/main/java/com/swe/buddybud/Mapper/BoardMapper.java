package com.swe.buddybud.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    List<Map<String, String>> getBoardsList(String boardType, Integer userId);

    Map<String, String> getBoard(String boardType, Integer userId, Integer boardId);

    void insertBoard(Map<String, String> fields);

    void updateBoard(Map<String, String> fields);

    void deleteBoard(Integer boardId);

    List<Map<String, String>> getComment(Integer boardId);

    void insertComment(Map<String, String> fields);
}