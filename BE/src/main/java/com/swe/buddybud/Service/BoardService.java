package com.swe.buddybud.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface BoardService {
    void insertBoard(Map<String, String> fields);

    List<Map<String, String>> getBoardsList();

    Map<String, String> getBoard(Integer boardId);

    void updateBoard(Integer boardId, Map<String, String> fields);

    boolean deleteBoard(Integer boardId);
}

