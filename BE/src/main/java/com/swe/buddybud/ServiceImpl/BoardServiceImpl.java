package com.swe.buddybud.ServiceImpl;

import com.swe.buddybud.Mapper.BoardMapper;
import com.swe.buddybud.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardMapper boardMapper;


}
