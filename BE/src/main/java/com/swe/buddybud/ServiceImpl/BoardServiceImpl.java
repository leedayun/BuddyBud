package com.swe.buddybud.ServiceImpl;

import com.swe.buddybud.Mapper.BoardMapper;
import com.swe.buddybud.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardMapper boardMapper;

    @Override
    public void insertBoard(Map<String, String> fields) {
        boardMapper.insertBoard(fields);
    }

    @Override
    public List<Map<String, String>> getBoardsList() {
        return boardMapper.getBoardsList();
    }

    @Override
    public Map<String, String> getBoard(Integer boardId) {
        return boardMapper.getBoard(boardId);
    }

    @Override
    public void updateBoard(Integer boardId, Map<String, String> fields) {
        boardMapper.updateBoard(boardId, fields);
    }

    @Override
    public boolean deleteBoard(Integer boardId) {
        int rowsAffected = boardMapper.deleteBoard(boardId);

        return rowsAffected > 0;
    }
}
