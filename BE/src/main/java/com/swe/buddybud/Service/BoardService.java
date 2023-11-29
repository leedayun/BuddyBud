package com.swe.buddybud.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface BoardService {

    List<Map<String, String>> getBoardsList(Map<String, String> fields);

    Map<String, String> getBoard(Map<String, String> fields);

    void insertBoard(Map<String, String> fields);

    void updateBoard(Map<String, String> fields);

    void deleteBoard(Integer boardId);

    List<Map<String, String>> getComment(Integer boardId);

    void insertComment(Map<String, String> fields);
}

