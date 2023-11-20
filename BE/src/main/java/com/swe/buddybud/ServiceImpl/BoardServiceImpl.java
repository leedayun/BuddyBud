package com.swe.buddybud.ServiceImpl;

import com.swe.buddybud.Mapper.BoardMapper;
import com.swe.buddybud.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardMapper boardMapper;


    @Override
    public void createPost(Map<String, String> fields) {
        boardMapper.createPost(fields);
    }
}
