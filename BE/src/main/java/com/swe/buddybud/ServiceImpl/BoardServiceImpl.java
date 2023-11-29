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
    public List<Map<String, String>> getBoardsList(Map<String, String> fields) {
        return boardMapper.getBoardsList(fields);
    }

    @Override
    public Map<String, String> getBoard(Map<String, String> fields) {
        return boardMapper.getBoard(fields);
    }

    @Override
    public void insertBoard(Map<String, String> fields) {
        boardMapper.insertBoard(fields);
    }

    @Override
    public void updateBoard(Map<String, String> fields) {
        boardMapper.updateBoard(fields);
    }

    @Override
    public void deleteBoard(Integer boardId) {
        boardMapper.deleteBoard(boardId);
    }

    @Override
    public List<Map<String, String>> getComment(Integer boardId) {
        return boardMapper.getComment(boardId);
    }

    @Override
    public void insertComment(Map<String, String> fields) {
        boardMapper.insertComment(fields);
    }
}
