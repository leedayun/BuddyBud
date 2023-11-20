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
    public void insertNotice(Map<String, String> fields) {
        boardMapper.insertNotice(fields);
    }

    @Override
    public List<Map<String, String>> getNoticesList() {
        return boardMapper.getNoticesList();
    }

    @Override
    public Map<String, String> getNotice(Integer noticeId) {
        return boardMapper.getNotice(noticeId);
    }

    @Override
    public void updateNotice(Integer noticeId, Map<String, String> fields) {
        boardMapper.updateNotice(noticeId, fields);
    }

    @Override
    public boolean deleteNotice(Integer noticeId) {
        int rowsAffected = boardMapper.deleteNotice(noticeId);

        return rowsAffected > 0;
    }
}
